package com.pharma.repository.student;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.TimeTableListModel;
import com.pharma.retrofit.ApiRequest;
import com.pharma.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeTableRepository {
    private ApiRequest apiRequest;
    public TimeTableRepository(@NonNull Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<TimeTableListModel> getTimeTable() {
        final MutableLiveData<TimeTableListModel> data = new MutableLiveData<>();
        apiRequest
                .getTimeTable()
                .enqueue(new Callback<TimeTableListModel>() {
                    @Override
                    public void onResponse(Call<TimeTableListModel> call, Response<TimeTableListModel> response) {
                        Log.v("Cooode",response.code()+"");
                        if (response.body() != null) {
                            TimeTableListModel e=response.body();
                            data.setValue(e);
                        }else{
                            TimeTableListModel res=new TimeTableListModel();
                            res.setCode(response.code());
                            data.setValue(res);
                        }
                    }
                    @Override
                    public void onFailure(Call<TimeTableListModel> call, Throwable t) {
                        TimeTableListModel res=new TimeTableListModel();
                        res.setCode(100);
                        data.setValue(res);
                    }
                });
        return data;
    }
}
