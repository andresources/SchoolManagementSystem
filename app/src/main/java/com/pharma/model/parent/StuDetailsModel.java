package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StuDetailsModel {
    @SerializedName("clsName")
    @Expose
    private String clsName;

    @SerializedName("secName")
    @Expose
    private String secName;

    @SerializedName("acadYearName")
    @Expose
    private String acadYearName;

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public String getAcadYearName() {
        return acadYearName;
    }

    public void setAcadYearName(String acadYearName) {
        this.acadYearName = acadYearName;
    }
}
