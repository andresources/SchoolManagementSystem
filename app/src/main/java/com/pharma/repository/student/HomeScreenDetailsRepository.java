package com.pharma.repository.student;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pharma.model.parent.HomeScreenDataListModel;
import com.pharma.model.parent.HomeWorkListModel;
import com.pharma.retrofit.ApiRequest;
import com.pharma.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreenDetailsRepository {
    private ApiRequest apiRequest;
    public HomeScreenDetailsRepository(@NonNull Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<HomeScreenDataListModel> getHomeScreenData() {
        final MutableLiveData<HomeScreenDataListModel> data = new MutableLiveData<>();
        apiRequest
                .getHomeScreenDetails()
                .enqueue(new Callback<HomeScreenDataListModel>() {
                    @Override
                    public void onResponse(Call<HomeScreenDataListModel> call, Response<HomeScreenDataListModel> response) {
                        Log.v("Cooode",response.code()+"");
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }else{
                            HomeScreenDataListModel res=new HomeScreenDataListModel();
                            res.setCode(response.code());
                            data.setValue(res);
                        }
                    }
                    @Override
                    public void onFailure(Call<HomeScreenDataListModel> call, Throwable t) {
                        HomeScreenDataListModel res=new HomeScreenDataListModel();
                        res.setCode(100);
                        data.setValue(res);
                    }
                });
        return data;
    }
}
