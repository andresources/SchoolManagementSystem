package com.pharma.repository.student;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.pharma.model.parent.TransactionStatusModel;
import com.pharma.response.LoginResponse;
import com.pharma.retrofit.ApiRequest;
import com.pharma.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateTransactionStatusRepositoy {
    private ApiRequest apiRequest;
    public UpdateTransactionStatusRepositoy(@NonNull Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<TransactionStatusModel> getTransStatus(String tstatus, String tid, String tamount, String tdate) {
        final MutableLiveData<TransactionStatusModel> data = new MutableLiveData<>();
        JsonObject obj=new JsonObject();
        obj.addProperty("tstatus",tstatus);
        obj.addProperty("tid",tid);
        obj.addProperty("tamount",tamount);
        obj.addProperty("tdate",tdate);
        apiRequest
                .updateTransStatus(obj)
                .enqueue(new Callback<TransactionStatusModel>() {
                    @Override
                    public void onResponse(Call<TransactionStatusModel> call, Response<TransactionStatusModel> response) {
                        Log.v("Cooode",response.code()+"");
                        if (response.body() != null) {
                            TransactionStatusModel res= response.body();
                            //res.setResTime(response.raw().receivedResponseAtMillis()-response.raw().sentRequestAtMillis());
                            data.setValue(res);
                        }else{
                            TransactionStatusModel res=new TransactionStatusModel();
                            res.setCode(response.code());
                            data.setValue(res);
                        }
                    }
                    @Override
                    public void onFailure(Call<TransactionStatusModel> call, Throwable t) {
                        TransactionStatusModel res=new TransactionStatusModel();
                        res.setCode(100);
                        data.setValue(res);
                    }
                });
        return data;
    }
}