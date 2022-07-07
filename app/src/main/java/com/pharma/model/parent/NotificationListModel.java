package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationListModel {
    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("stuNotices")
    @Expose
    private List<NotificationModel> stuNotices;

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

    public List<NotificationModel> getStuNotices() {
        return stuNotices;
    }

    public void setStuNotices(List<NotificationModel> stuNotices) {
        this.stuNotices = stuNotices;
    }
}
