package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YearWiseListModel {
    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("acadyear")
    @Expose
    private String acadyear;

    @SerializedName("yearWiseStuAtt")
    @Expose
    private List<YearWiseModel> yearWiseStuAtt;

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

    public List<YearWiseModel> getYearWiseStuAtt() {
        return yearWiseStuAtt;
    }

    public void setYearWiseStuAtt(List<YearWiseModel> yearWiseStuAtt) {
        this.yearWiseStuAtt = yearWiseStuAtt;
    }

    public String getAcadyear() {
        return acadyear;
    }

    public void setAcadyear(String acadyear) {
        this.acadyear = acadyear;
    }
}
