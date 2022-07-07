package com.pharma.repository.student;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pharma.model.parent.ExamListModel;
import com.pharma.model.parent.HomeWorkListModel;
import com.pharma.retrofit.ApiRequest;
import com.pharma.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeWorkRepository {
    private ApiRequest apiRequest;
    public HomeWorkRepository(@NonNull Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<HomeWorkListModel> getHomeworks() {
        final MutableLiveData<HomeWorkListModel> data = new MutableLiveData<>();
        apiRequest
                .getHomeWorks()
                .enqueue(new Callback<HomeWorkListModel>() {
                    @Override
                    public void onResponse(Call<HomeWorkListModel> call, Response<HomeWorkListModel> response) {
                        Log.v("Cooode",response.code()+"");
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }else{
                            HomeWorkListModel res=new HomeWorkListModel();
                            res.setCode(response.code());
                            data.setValue(res);
                        }
                    }
                    @Override
                    public void onFailure(Call<HomeWorkListModel> call, Throwable t) {
                        HomeWorkListModel res=new HomeWorkListModel();
                        res.setCode(100);
                        data.setValue(res);
                    }
                });
        return data;
    }
}
