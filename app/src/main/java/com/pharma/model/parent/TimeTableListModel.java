package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TimeTableListModel {
    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("timeTable")
    @Expose
    private List<TimeTableModel> timeTable;

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

    public List<TimeTableModel> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(List<TimeTableModel> timeTable) {
        this.timeTable = timeTable;
    }
}
