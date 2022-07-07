package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionsModel {
    /*"stuIncRef": 1529389,
       "date": "2021-08-05T18:30:00.000Z",
       "recNo": null,
       "transNo": "ATOM-325885",
       "amount": 12000*/
    @SerializedName("stuIncRef")
    @Expose
    private int stuIncRef;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("recNo")
    @Expose
    private String recNo;

    @SerializedName("transNo")
    @Expose
    private String transNo;

    @SerializedName("amount")
    @Expose
    private String amount;

    @SerializedName("txnStatus")
    @Expose
    private String txnStatus;

    public int getStuIncRef() {
        return stuIncRef;
    }

    public void setStuIncRef(int stuIncRef) {
        this.stuIncRef = stuIncRef;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRecNo() {
        return recNo;
    }

    public void setRecNo(String recNo) {
        this.recNo = recNo;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }
}
