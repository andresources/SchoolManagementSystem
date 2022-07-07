package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeeDataModel {
    @SerializedName("fee")
    @Expose
    private int fee;

    @SerializedName("concs")
    @Expose
    private int concs;

    @SerializedName("paid")
    @Expose
    private int paid;

    @SerializedName("balance")
    @Expose
    private int balance;

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getConcs() {
        return concs;
    }

    public void setConcs(int concs) {
        this.concs = concs;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
