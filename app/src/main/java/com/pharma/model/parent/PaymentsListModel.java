package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentsListModel {
    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("feeData")
    @Expose
    private FeeDataModel feeData;

    @SerializedName("recieptWiseData")
    @Expose
    private List<RecieptWiseDataModel> recieptWiseData;

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

    public FeeDataModel getFeeData() {
        return feeData;
    }

    public void setFeeData(FeeDataModel feeData) {
        this.feeData = feeData;
    }

    public List<RecieptWiseDataModel> getRecieptWiseData() {
        return recieptWiseData;
    }

    public void setRecieptWiseData(List<RecieptWiseDataModel> recieptWiseData) {
        this.recieptWiseData = recieptWiseData;
    }
}
