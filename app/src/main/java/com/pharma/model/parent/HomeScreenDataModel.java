package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeScreenDataModel {
    @SerializedName("fee_due")
    @Expose
    private String fee_due;

    @SerializedName("delay_msg")
    @Expose
    private String delay_msg;

    @SerializedName("count")
    @Expose
    private int count;

    private String displayAlert;
    private String displayAlertType;
    private String displayAlertTitle;
    private String displayAlertDes;



    public String getFee_due() {
        return fee_due;
    }

    public void setFee_due(String fee_due) {
        this.fee_due = fee_due;
    }

    public String getDelay_msg() {
        return delay_msg;
    }

    public void setDelay_msg(String delay_msg) {
        this.delay_msg = delay_msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDisplayAlert() {
        //return displayAlert;
        return "yes";
    }

    public void setDisplayAlert(String displayAlert) {
        this.displayAlert = displayAlert;
    }

    public String getDisplayAlertType() {
        //return displayAlertType;
        return "image";
    }

    public void setDisplayAlertType(String displayAlertType) {
        this.displayAlertType = displayAlertType;

    }

    public String getDisplayAlertTitle() {
        //return displayAlertTitle;
        return "Demo Title";
    }

    public void setDisplayAlertTitle(String displayAlertTitle) {
        this.displayAlertTitle = displayAlertTitle;
    }

    public String getDisplayAlertDes() {
        //return displayAlertDes;
        return "https://learn.tapasyaedu.com/welcome.png";
    }

    public void setDisplayAlertDes(String displayAlertDes) {
        this.displayAlertDes = displayAlertDes;
    }
}
