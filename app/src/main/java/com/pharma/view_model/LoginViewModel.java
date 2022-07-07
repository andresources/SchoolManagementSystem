package com.pharma.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pharma.repository.student.LoginRepository;
import com.pharma.response.LoginResponse;

public class LoginViewModel extends AndroidViewModel {
    LoginRepository loginRep;
    private LiveData<LoginResponse> loginResponseLiveData;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRep = new LoginRepository(application);
        //tap45671 , tap43326
    }
    public LiveData<LoginResponse> getLoginResponseLiveData(String uname,String pwd) {
        this.loginResponseLiveData = loginRep.getLogin(uname,"E+czW7ji9b7x850gaqGyYw==");
        return loginResponseLiveData;
    }
}
