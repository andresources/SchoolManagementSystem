package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentHistoryModel {
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("onlineTransKeys")
    @Expose
    private List<RazorPayKeysModel> onlineTransKeys;

    @SerializedName("mainFeeCatg")
    @Expose
    private List<MainFeeCatgModel> mainFeeCatg;

    @SerializedName("mainFeeDetails")
    @Expose
    private MainFeeDetailsModel mainFeeDetails;

    @SerializedName("dupFeeDetails")
    @Expose
    private MainFeeDetailsModel dupFeeDetails;

    @SerializedName("dupFeeCatg")
    @Expose
    private List<MainFeeCatgModel> dupFeeCatg;


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

    public MainFeeDetailsModel getMainFeeDetails() {
        return mainFeeDetails;
    }

    public void setMainFeeDetails(MainFeeDetailsModel mainFeeDetails) {
        this.mainFeeDetails = mainFeeDetails;
    }

    public List<MainFeeCatgModel> getMainFeeCatg() {
        return mainFeeCatg;
    }

    public void setMainFeeCatg(List<MainFeeCatgModel> mainFeeCatg) {
        this.mainFeeCatg = mainFeeCatg;
    }

    public MainFeeDetailsModel getDupFeeDetails() {
        return dupFeeDetails;
    }

    public void setDupFeeDetails(MainFeeDetailsModel dupFeeDetails) {
        this.dupFeeDetails = dupFeeDetails;
    }

    public List<MainFeeCatgModel> getDupFeeCatg() {
        return dupFeeCatg;
    }

    public void setDupFeeCatg(List<MainFeeCatgModel> dupFeeCatg) {
        this.dupFeeCatg = dupFeeCatg;
    }

    public List<RazorPayKeysModel> getOnlineTransKeys() {
        return onlineTransKeys;
    }

    public void setOnlineTransKeys(List<RazorPayKeysModel> onlineTransKeys) {
        this.onlineTransKeys = onlineTransKeys;
    }
}
