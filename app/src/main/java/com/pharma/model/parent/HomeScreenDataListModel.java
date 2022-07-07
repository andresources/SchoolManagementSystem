package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeScreenDataListModel {
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("attPerc")
    @Expose
    private float attPerc;

    @SerializedName("homeScrnData")
    @Expose
    private HomeScreenDataModel homeScrnData;

    @SerializedName("homeScreenAlert")
    @Expose
    private AlertModel homeScreenAlert;

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

    public float getAttPerc() {
        return attPerc;
    }

    public void setAttPerc(float attPerc) {
        this.attPerc = attPerc;
    }

    public HomeScreenDataModel getHomeScrnData() {
        return homeScrnData;
    }

    public void setHomeScrnData(HomeScreenDataModel homeScrnData) {
        this.homeScrnData = homeScrnData;
    }

    public AlertModel getHomeScreenAlert() {
        return homeScreenAlert;
    }

    public void setHomeScreenAlert(AlertModel homeScreenAlert) {
        this.homeScreenAlert = homeScreenAlert;
    }
}
