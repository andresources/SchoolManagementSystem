package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeWorkListModel {
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("homeworkData")
    @Expose
    private List<HomeWorkModel> homeWorkList;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<HomeWorkModel> getHomeWorkList() {
        return homeWorkList;
    }

    public void setHomeWorkList(List<HomeWorkModel> homeWorkList) {
        this.homeWorkList = homeWorkList;
    }
}
