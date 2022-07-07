package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YearWiseModel {
    @SerializedName("year")
    @Expose
    private String year;

    @SerializedName("month")
    @Expose
    private int month;

    @SerializedName("present")
    @Expose
    private int present;

    @SerializedName("absent")
    @Expose
    private int absent;

    @SerializedName("holiday")
    @Expose
    private int holiday;

    @SerializedName("total")
    @Expose
    private int total;


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    public int getHoliday() {
        return holiday;
    }

    public void setHoliday(int holiday) {
        this.holiday = holiday;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
