package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventsListModel {
    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("stuOlClsData")
    @Expose
    private List<EventsModel> stuOlClsData;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<EventsModel> getStuOlClsData() {
        return stuOlClsData;
    }

    public void setStuOlClsData(List<EventsModel> stuOlClsData) {
        this.stuOlClsData = stuOlClsData;
    }
}
