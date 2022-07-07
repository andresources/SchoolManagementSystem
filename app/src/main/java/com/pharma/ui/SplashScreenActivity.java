package com.pharma.ui;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.atom.ots.enc.AtomEncryption;
import com.google.gson.Gson;
import com.pharma.MyApp;
import com.pharma.MyOwnLifeCycleObserver;
import com.pharma.R;
import com.pharma.fragment.patient.StudentHomeFragment;
import com.pharma.model.parent.UserDataModel;
import com.pharma.ui.parent.StudentDashboardActivity;
import com.pharma.utils.AppKeys;
import com.pharma.utils.MyProgressDialog;

import javax.inject.Inject;

public class SplashScreenActivity extends BaseActivity {
    RelativeLayout rlLogin;
    MutableLiveData<String> mm;
    @Inject
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        MyApp.app().appComponent().inject(SplashScreenActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppKeys.IS_DISPLAY_ALERT, "yes");
        editor.apply();
        //MyOwnLifeCycleObserver o=new MyOwnLifeCycleObserver();
        //getLifecycle().addObserver(o);
        /*o.getData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), ""+s, Toast.LENGTH_SHORT).show();
            }
        });*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(getAppStatus().equals("no")) {
                    startNewActivityWithFinish(LoginActivity.class);
                }else{
                    MyApp.jwt_token =sharedPreferences.getString(AppKeys.JWT_TOKEN, "-");
                    startNewActivityWithFinish(StudentDashboardActivity.class);
                }
            }
        }, 2500);
        rlLogin = (RelativeLayout)findViewById(R.id.rlLogin);
        rlLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String str=AtomEncryption.decrypt("C05AED44531A61C12454AC83E2D48EA11FDDEED2E2C7C598B8E741AD247C0255E402C08172B74E731336D7F150927306A39F4F92DC1339134334EA5F71B43E66303A6A3B3DE32D2848B66C1B4D2290873D6801088B9A4C10917A676F046685790E3FCEC643804A0AEDB813FB1156D3AA5C6A81438CEE4A5ED7D96C0821A6BB2DA8FB21E90C086A7C40EBCFDCA71B326CE16D685BFFD8D8CBC80307375934CA5EDC73CF0F8A51F597A039E3671268577BFC67DD64DC8AD09C1E8B8FE99E3FF3E7416E68F7007445137E8E463828E4957BCD49B528931D4C5D0BA27BB63AC939BFB3E1786253AA1CF35A9B509F178EEC7BE27C8ED493BC41ECCFE3A214FD0A39BA6826017E949591D949CC2E14427C22494ECA2BA62D6D987AE70B4CC3072DA426D56123F7539960E5B0BD03390550758A38963F30505269820888FE04BF61B2D0CF372BF2EFB37F026EE5B0D4ACB792C1D35F24D1A0307DFB39DC93A30EA5BB994D5FF807798075586FF932B741C40399DD93E89DB6779A31D608DBB6BCB35D40","75AEF0FA1B94B3C10D4F5B268F757F11");
                //Log.i("estr",str);

            }
        });
    }
    private String getAppStatus() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppKeys.IS_DISPLAY_ALERT, "yes");
        editor.apply();
        return sharedPreferences.getString(AppKeys.IS_ALREADY_LOGIN, "no");
    }
}