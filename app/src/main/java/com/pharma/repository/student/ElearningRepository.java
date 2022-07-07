package com.pharma.repository.student;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pharma.model.parent.ElearningNewModel;
import com.pharma.model.parent.EventsListModel;
import com.pharma.retrofit.ApiRequest;
import com.pharma.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElearningRepository {
    private ApiRequest apiRequest;
    public ElearningRepository(@NonNull Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<ElearningNewModel> getElearning() {
        final MutableLiveData<ElearningNewModel> data = new MutableLiveData<>();
        apiRequest
                .getElearningLinks()
                .enqueue(new Callback<ElearningNewModel>() {
                    @Override
                    public void onResponse(Call<ElearningNewModel> call, Response<ElearningNewModel> response) {
                        Log.v("Cooode",response.code()+"");
                        if (response.body() != null) {
                            ElearningNewModel e=response.body();
                            data.setValue(e);
                        }else{
                            ElearningNewModel res=new ElearningNewModel();
                            res.setCode(response.code());
                            data.setValue(res);
                        }
                    }
                    @Override
                    public void onFailure(Call<ElearningNewModel> call, Throwable t) {
                        ElearningNewModel res=new ElearningNewModel();
                        res.setCode(100);
                        data.setValue(res);
                    }
                });
        return data;
    }
}
