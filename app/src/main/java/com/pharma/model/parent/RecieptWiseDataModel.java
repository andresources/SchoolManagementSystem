package com.pharma.model.parent;

import static com.pharma.utils.DateUtils.SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T;
import static com.pharma.utils.DateUtils.SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T1;
import static com.pharma.utils.DateUtils.SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_TIME_24;
import static com.pharma.utils.DateUtils.SIMPLE_TIME_FORMAT_24_HOURS;
import static com.pharma.utils.DateUtils.SIMPLE_TIME_FORMAT_AM_PM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pharma.utils.DateUtils;

import java.util.List;

public class RecieptWiseDataModel {
    @SerializedName("recNo")
    @Expose
    private String recNo;

    @SerializedName("amount")
    @Expose
    private int amount;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("paymode")
    @Expose
    private String paymode;

    @SerializedName("current_payments")
    @Expose
    private List<CurrentPaymentsModel> current_payments;


    public String getRecNo() {
        return recNo;
    }

    public void setRecNo(String recNo) {
        this.recNo = recNo;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return DateUtils.getDesiredDateFormat(SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T1,SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_TIME_24,date);
        //return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaymode() {
        return paymode;
    }

    public void setPaymode(String paymode) {
        this.paymode = paymode;
    }

    public List<CurrentPaymentsModel> getCurrent_payments() {
        return current_payments;
    }

    public void setCurrent_payments(List<CurrentPaymentsModel> current_payments) {
        this.current_payments = current_payments;
    }
}
