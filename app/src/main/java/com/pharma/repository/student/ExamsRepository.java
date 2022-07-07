package com.pharma.repository.student;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.ExamListModel;
import com.pharma.retrofit.ApiRequest;
import com.pharma.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamsRepository {
    private ApiRequest apiRequest;
    public ExamsRepository(@NonNull Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<ExamListModel> getEvents() {
        final MutableLiveData<ExamListModel> data = new MutableLiveData<>();
        apiRequest
                .getExams()
                .enqueue(new Callback<ExamListModel>() {
                    @Override
                    public void onResponse(Call<ExamListModel> call, Response<ExamListModel> response) {
                        Log.v("Cooode",response.code()+"");
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }else{
                            ExamListModel res=new ExamListModel();
                            res.setCode(response.code());
                            data.setValue(res);
                        }
                    }
                    @Override
                    public void onFailure(Call<ExamListModel> call, Throwable t) {
                        ExamListModel res=new ExamListModel();
                        res.setCode(100);
                        data.setValue(res);
                    }
                });
        return data;
    }
}
