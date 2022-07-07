package com.pharma.repository.student;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.OnlineClassesListModel;
import com.pharma.retrofit.ApiRequest;
import com.pharma.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnlineClassesRepository {
    private ApiRequest apiRequest;
    public OnlineClassesRepository(@NonNull Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<OnlineClassesListModel> getOnlineClasses() {
        final MutableLiveData<OnlineClassesListModel> data = new MutableLiveData<>();
        apiRequest
                .getOnlineClasses()
                .enqueue(new Callback<OnlineClassesListModel>() {
                    @Override
                    public void onResponse(Call<OnlineClassesListModel> call, Response<OnlineClassesListModel> response) {
                        Log.v("Cooode",response.code()+"");
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }else{
                            OnlineClassesListModel res=new OnlineClassesListModel();
                            res.setCode(response.code());
                            data.setValue(res);
                        }
                    }
                    @Override
                    public void onFailure(Call<OnlineClassesListModel> call, Throwable t) {
                        OnlineClassesListModel res=new OnlineClassesListModel();
                        res.setCode(100);
                        data.setValue(res);
                    }
                });
        return data;
    }
}
