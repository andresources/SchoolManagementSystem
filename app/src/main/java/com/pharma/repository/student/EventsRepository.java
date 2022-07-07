package com.pharma.repository.student;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.EventsModel;
import com.pharma.response.LoginResponse;
import com.pharma.retrofit.ApiRequest;
import com.pharma.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsRepository {
    private ApiRequest apiRequest;
    public EventsRepository(@NonNull Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<EventsListModel> getEvents() {
        final MutableLiveData<EventsListModel> data = new MutableLiveData<>();
        apiRequest
                .getEvents()
                .enqueue(new Callback<EventsListModel>() {
                    @Override
                    public void onResponse(Call<EventsListModel> call, Response<EventsListModel> response) {
                        Log.v("Cooode",response.code()+"");
                        if (response.body() != null) {
                            EventsListModel e=response.body();
                            data.setValue(e);
                        }else{
                            EventsListModel res=new EventsListModel();
                            res.setCode(response.code());
                            data.setValue(res);
                        }
                    }
                    @Override
                    public void onFailure(Call<EventsListModel> call, Throwable t) {
                        EventsListModel res=new EventsListModel();
                        res.setCode(100);
                        data.setValue(res);
                    }
                });
        return data;
    }
}
