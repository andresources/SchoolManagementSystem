package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentPaymentsModel {
    @SerializedName("Feehead")
    @Expose
    private String Feehead;

    @SerializedName("catgAmt")
    @Expose
    private int catgAmt;

    public String getFeehead() {
        return Feehead;
    }

    public void setFeehead(String feehead) {
        Feehead = feehead;
    }

    public int getCatgAmt() {
        return catgAmt;
    }

    public void setCatgAmt(int catgAmt) {
        this.catgAmt = catgAmt;
    }
}
