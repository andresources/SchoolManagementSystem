package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ElearningNewModel {
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("elearnData")
    @Expose
    private List<ElearnLinkListModel> elearnData;

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

    public List<ElearnLinkListModel> getElearnData() {
        return elearnData;
    }

    public void setElearnData(List<ElearnLinkListModel> elearnData) {
        this.elearnData = elearnData;
    }
}
