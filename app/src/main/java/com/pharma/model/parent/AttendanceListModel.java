package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AttendanceListModel {
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("stuAttRespMonthArray")
    @Expose
    private List<MonthlyAttendanceModel> stuAttRespMonthArray;

    @SerializedName("yearWise")
    @Expose
    private YearWiseListModel yearWise;

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

    public List<MonthlyAttendanceModel> getStuAttRespMonthArray() {
        return stuAttRespMonthArray;
    }

    public void setStuAttRespMonthArray(List<MonthlyAttendanceModel> stuAttRespMonthArray) {
        this.stuAttRespMonthArray = stuAttRespMonthArray;
    }

    public YearWiseListModel getYearWise() {
        return yearWise;
    }

    public void setYearWise(YearWiseListModel yearWise) {
        this.yearWise = yearWise;
    }
}
