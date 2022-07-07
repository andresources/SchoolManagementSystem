package com.pharma.repository.student;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pharma.model.parent.PaymentHistoryModel;
import com.pharma.model.parent.PaymentsListModel;
import com.pharma.retrofit.ApiRequest;
import com.pharma.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentPaymentsRepository {
    private ApiRequest apiRequest;
    public StudentPaymentsRepository(@NonNull Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<PaymentHistoryModel> getPayments() {
        final MutableLiveData<PaymentHistoryModel> data = new MutableLiveData<>();
        apiRequest
                .getStudentPayments()
                .enqueue(new Callback<PaymentHistoryModel>() {
                    @Override
                    public void onResponse(Call<PaymentHistoryModel> call, Response<PaymentHistoryModel> response) {
                        Log.v("Cooode1",response.code()+"");
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }else{
                            PaymentHistoryModel res=new PaymentHistoryModel();
                            res.setCode(response.code());
                            data.setValue(res);
                        }
                    }
                    @Override
                    public void onFailure(Call<PaymentHistoryModel> call, Throwable t) {
                        PaymentHistoryModel res=new PaymentHistoryModel();
                        res.setCode(300);
                        res.setSuccess(t.getMessage());
                        data.setValue(res);
                    }
                });
        return data;
    }
}

