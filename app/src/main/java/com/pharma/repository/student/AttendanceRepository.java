package com.pharma.repository.student;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pharma.model.parent.AttendanceListModel;
import com.pharma.model.parent.EventsListModel;
import com.pharma.retrofit.ApiRequest;
import com.pharma.retrofit.RetrofitRequest;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceRepository {
    private ApiRequest apiRequest;
    public AttendanceRepository(@NonNull Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<AttendanceListModel> getAttendance(String year,String month) {
        JsonObject obj=new JsonObject();
        obj.addProperty("month",month);
        obj.addProperty("year",year);
        Gson gson = new Gson();
        String jsonString = gson.toJson(obj);
        final MutableLiveData<AttendanceListModel> data = new MutableLiveData<>();
        apiRequest
                .getAttendance(obj)
                .enqueue(new Callback<AttendanceListModel>() {
                    @Override
                    public void onResponse(Call<AttendanceListModel> call, Response<AttendanceListModel> response) {
                        Log.v("Cooode",response.code()+"");
                        if (response.body() != null) {
                            AttendanceListModel e=response.body();
                            data.setValue(e);
                        }else{
                            AttendanceListModel res=new AttendanceListModel();
                            res.setCode(response.code());
                            data.setValue(res);
                        }
                    }
                    @Override
                    public void onFailure(Call<AttendanceListModel> call, Throwable t) {
                        AttendanceListModel res=new AttendanceListModel();
                        res.setCode(100);
                        data.setValue(res);
                    }
                });
        return data;
    }
}
