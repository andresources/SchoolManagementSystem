package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainFeeDetailsModel {
    @SerializedName("feeCatg")
    @Expose
    private String feeCatg;

    @SerializedName("catgFullName")
    @Expose
    private String catgFullName;

    @SerializedName("dueDate")
    @Expose
    private String dueDate;

    @SerializedName("minPayable")
    @Expose
    private String minPayable;

    @SerializedName("feeData")
    @Expose
    private List<FeesDataModel> feeData;

    public String getFeeCatg() {
        return feeCatg;
    }

    public void setFeeCatg(String feeCatg) {
        this.feeCatg = feeCatg;
    }

    public String getCatgFullName() {
        return catgFullName;
    }

    public void setCatgFullName(String catgFullName) {
        this.catgFullName = catgFullName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getMinPayable() {
        return minPayable;
    }

    public void setMinPayable(String minPayable) {
        this.minPayable = minPayable;
    }

    public List<FeesDataModel> getFeeData() {
        return feeData;
    }

    public void setFeeData(List<FeesDataModel> feeData) {
        this.feeData = feeData;
    }
}
