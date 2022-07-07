package com.pharma.repository.student;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.NotificationListModel;
import com.pharma.retrofit.ApiRequest;
import com.pharma.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationRepository {
    private ApiRequest apiRequest;
    public NotificationRepository(@NonNull Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<NotificationListModel> getNotifications() {
        final MutableLiveData<NotificationListModel> data = new MutableLiveData<>();
        apiRequest
                .getNotifications()
                .enqueue(new Callback<NotificationListModel>() {
                    @Override
                    public void onResponse(Call<NotificationListModel> call, Response<NotificationListModel> response) {
                        Log.v("Cooode",response.code()+"");
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }else{
                            NotificationListModel res=new NotificationListModel();
                            res.setCode(response.code());
                            data.setValue(res);
                        }
                    }
                    @Override
                    public void onFailure(Call<NotificationListModel> call, Throwable t) {
                        NotificationListModel res=new NotificationListModel();
                        res.setCode(100);
                        data.setValue(res);
                    }
                });
        return data;
    }
}
