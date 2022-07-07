package com.pharma.repository.student;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.GalleryListModel;
import com.pharma.retrofit.ApiRequest;
import com.pharma.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryRepository {
    private ApiRequest apiRequest;
    public GalleryRepository(@NonNull Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<GalleryListModel> getGallery() {
        final MutableLiveData<GalleryListModel> data = new MutableLiveData<>();
        apiRequest
                .getGallery()
                .enqueue(new Callback<GalleryListModel>() {
                    @Override
                    public void onResponse(Call<GalleryListModel> call, Response<GalleryListModel> response) {
                        Log.v("Cooode",response.code()+"");
                        if (response.body() != null) {
                            GalleryListModel e=response.body();
                            data.setValue(e);
                        }else{
                            GalleryListModel res=new GalleryListModel();
                            res.setCode(response.code());
                            data.setValue(res);
                        }
                    }
                    @Override
                    public void onFailure(Call<GalleryListModel> call, Throwable t) {
                        GalleryListModel res=new GalleryListModel();
                        res.setCode(100);
                        data.setValue(res);
                    }
                });
        return data;
    }
}
