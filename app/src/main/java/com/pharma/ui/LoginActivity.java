package com.pharma.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.pharma.MyApp;
import com.pharma.R;
import com.pharma.di.MyComponent;
import com.pharma.model.parent.CompusDataModel;
import com.pharma.model.parent.Employee;
import com.pharma.model.parent.StuDetailsModel;
import com.pharma.model.parent.UserDataModel;
import com.pharma.response.LoginResponse;
import com.pharma.ui.parent.StudentDashboardActivity;
import com.pharma.utils.AppKeys;
import com.pharma.utils.CustomProgressDialog;
import com.pharma.utils.MyProgressDialog;
import com.pharma.view_model.LoginViewModel;
import com.pharma.view_model.parent.ArticleViewModel;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity {
    RelativeLayout rlLogin;
    LoginViewModel loginViewModel;
    Dialog pd;
    TextInputEditText etMobileNumerEmail,etPwd;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    Employee emp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        MyApp.app().appComponent().inject(LoginActivity.this);
        rlLogin =(RelativeLayout)findViewById(R.id.rlLogin);
        etMobileNumerEmail=(TextInputEditText)findViewById(R.id.etMobileNumerEmail);
        etPwd=(TextInputEditText)findViewById(R.id.etPwd);
        rlLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etMobileNumerEmail.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "MobileNumer or Email is empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                getLogin();
                //startNewActivityWithFinish(StudentDashboardActivity.class);
            }
        });
    }
    private void getLogin(){
        pd= new MyProgressDialog(LoginActivity.this);
        pd.show();
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getLoginResponseLiveData(etMobileNumerEmail.getText().toString(),etPwd.getText().toString()).observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if(loginResponse!=null) {
                    pd.dismiss();
                    if(loginResponse.getCode()==200) {
                        saveData(loginResponse.getUserData(),loginResponse.getCompData(),loginResponse.getJwt_token(),loginResponse.getStuDetails());
                        startNewActivityWithFinish(StudentDashboardActivity.class);
                        MyApp.jwt_token =loginResponse.getJwt_token();
                        Toast.makeText(getApplicationContext(), "" + loginResponse.getSuccess()+" ResTime : "+(loginResponse.getResTime()/1000)+"Seconds", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Oop! something went worng.Try again. Error code : "+loginResponse.getCode(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    pd.dismiss();
                }
            }
        });
    }
    public void saveData(UserDataModel user, CompusDataModel cpm, String jwt_token, StuDetailsModel std) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        String comp_json = gson.toJson(cpm);
        String std_json = gson.toJson(std);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppKeys.USER_NAME, json);
        editor.putString(AppKeys.IS_ALREADY_LOGIN, "yes");
        editor.putString(AppKeys.IS_DISPLAY_ALERT, "yes");
        editor.putString(AppKeys.JWT_TOKEN, jwt_token);
        editor.putString(AppKeys.CAMP_DATA, comp_json);
        editor.putString(AppKeys.STD_DETAILS, std_json);

        editor.apply();
    }
}