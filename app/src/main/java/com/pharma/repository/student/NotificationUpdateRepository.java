package com.pharma.repository.student;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pharma.model.parent.NotificationUpdateModel;
import com.pharma.model.parent.TransactionsListModel;
import com.pharma.retrofit.ApiRequest;
import com.pharma.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationUpdateRepository {
    private ApiRequest apiRequest;
    public NotificationUpdateRepository(@NonNull Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<NotificationUpdateModel> getTransactions() {
        final MutableLiveData<NotificationUpdateModel> data = new MutableLiveData<>();
        apiRequest
                .updateNoticesCnt()
                .enqueue(new Callback<NotificationUpdateModel>() {
                    @Override
                    public void onResponse(Call<NotificationUpdateModel> call, Response<NotificationUpdateModel> response) {
                        Log.v("Cooode",response.code()+"");
                        try {
                            if (response.body() != null) {
                                NotificationUpdateModel e = response.body();
                                data.setValue(e);
                            } else {
                                NotificationUpdateModel res = new NotificationUpdateModel();
                                res.setCode(response.code());
                                data.setValue(res);
                            }
                        }catch (Exception e){
                            Log.i("Error",e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(Call<NotificationUpdateModel> call, Throwable t) {
                        NotificationUpdateModel res=new NotificationUpdateModel();
                        res.setCode(100);
                        data.setValue(res);
                    }
                });
        return data;
    }
}
