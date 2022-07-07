package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonthlyAttendanceModel {
    @SerializedName("month")
    @Expose
    private String month;

    @SerializedName("year")
    @Expose
    private String year;

    @SerializedName("leaveType")
    @Expose
    private int leaveType;

    @SerializedName("day")
    @Expose
    private String day;

    @SerializedName("holidayNote")
    @Expose
    private String holidayNote;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(int leaveType) {
        this.leaveType = leaveType;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHolidayNote() {
        return holidayNote;
    }

    public void setHolidayNote(String holidayNote) {
        this.holidayNote = holidayNote;
    }
}
