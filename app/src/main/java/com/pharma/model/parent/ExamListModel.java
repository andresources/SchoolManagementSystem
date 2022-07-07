package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExamListModel {
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("examFinalDetails")
    @Expose
    private List<ExamSingleDataModel> examFinalDetails;


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

    public List<ExamSingleDataModel> getExamFinalDetails() {
        return examFinalDetails;
    }

    public void setExamFinalDetails(List<ExamSingleDataModel> examFinalDetails) {
        this.examFinalDetails = examFinalDetails;
    }
}
