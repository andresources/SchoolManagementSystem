package com.pharma.repository.student;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pharma.model.parent.ElearningNewModel;
import com.pharma.model.parent.TransactionsListModel;
import com.pharma.retrofit.ApiRequest;
import com.pharma.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionsRepository {
    private ApiRequest apiRequest;
    public TransactionsRepository(@NonNull Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<TransactionsListModel> getTransactions() {
        final MutableLiveData<TransactionsListModel> data = new MutableLiveData<>();
        apiRequest
                .getPayTransactions()
                .enqueue(new Callback<TransactionsListModel>() {
                    @Override
                    public void onResponse(Call<TransactionsListModel> call, Response<TransactionsListModel> response) {
                        Log.v("Cooode",response.code()+"");
                        try {
                            if (response.body() != null) {
                                TransactionsListModel e = response.body();
                                data.setValue(e);
                            } else {
                                TransactionsListModel res = new TransactionsListModel();
                                res.setCode(response.code());
                                data.setValue(res);
                            }
                        }catch (Exception e){
                            Log.i("Error",e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(Call<TransactionsListModel> call, Throwable t) {
                        TransactionsListModel res=new TransactionsListModel();
                        res.setCode(100);
                        data.setValue(res);
                    }
                });
        return data;
    }
}