package com.pharma.repository.student;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.pharma.response.LoginResponse;
import com.pharma.response.parent.ArticleResponse;
import com.pharma.retrofit.ApiRequest;
import com.pharma.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private ApiRequest apiRequest;
    public LoginRepository(@NonNull Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<LoginResponse> getLogin(String uname, String pwd) {
        final MutableLiveData<LoginResponse> data = new MutableLiveData<>();
        JsonObject obj=new JsonObject();
        obj.addProperty("userName",uname);
        obj.addProperty("password",pwd);
        apiRequest
                .login(obj)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        Log.v("Cooode",response.code()+"");
                        if (response.body() != null) {
                            LoginResponse res= response.body();
                            res.setResTime(response.raw().receivedResponseAtMillis()-response.raw().sentRequestAtMillis());
                            data.setValue(res);
                        }else{
                            LoginResponse res=new LoginResponse();
                            res.setCode(response.code());
                            data.setValue(res);
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        LoginResponse res=new LoginResponse();
                        res.setCode(100);
                        data.setValue(res);
                    }
                });
        return data;
    }
}
