package com.pharma.repository.student;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pharma.model.parent.ExamListModel;
import com.pharma.model.parent.PaymentsListModel;
import com.pharma.retrofit.ApiRequest;
import com.pharma.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentsRepository {
    private ApiRequest apiRequest;
    public PaymentsRepository(@NonNull Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<PaymentsListModel> getPayments() {
        final MutableLiveData<PaymentsListModel> data = new MutableLiveData<>();
        apiRequest
                .getPayments()
                .enqueue(new Callback<PaymentsListModel>() {
                    @Override
                    public void onResponse(Call<PaymentsListModel> call, Response<PaymentsListModel> response) {
                        Log.v("Cooode1",response.body().getSuccess()+"");

                        if (response.body() != null) {
                            Log.i("Harr",response.body().getSuccess());
                            data.setValue(response.body());
                        }else{
                            PaymentsListModel res=new PaymentsListModel();
                            res.setCode(response.code());
                            res.setSuccess("Else");
                            data.setValue(res);
                        }
                    }
                    @Override
                    public void onFailure(Call<PaymentsListModel> call, Throwable t) {
                        PaymentsListModel res=new PaymentsListModel();
                        res.setCode(100);
                        res.setSuccess("Failure");
                        data.setValue(res);
                    }
                });
        return data;
    }
}
