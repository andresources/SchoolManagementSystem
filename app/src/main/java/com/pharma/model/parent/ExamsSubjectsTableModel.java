package com.pharma.model.parent;

import static com.pharma.utils.DateUtils.SIMPLE_DATE_FORMAT_DAY_FIRST_AND_T;
import static com.pharma.utils.DateUtils.SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T;
import static com.pharma.utils.DateUtils.SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_TIME_24;
import static com.pharma.utils.DateUtils.SIMPLE_TIME_FORMAT_24_HOURS;
import static com.pharma.utils.DateUtils.SIMPLE_TIME_FORMAT_AM_PM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pharma.utils.DateUtils;

public class ExamsSubjectsTableModel {
    @SerializedName("subjName")
    @Expose
    private String subjName;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("min")
    @Expose
    private int min;

    @SerializedName("max")
    @Expose
    private int max;

    @SerializedName("duration")
    @Expose
    private String duration;

    @SerializedName("marks")
    @Expose
    private String marks;

    public String getSubjName() {
        return subjName;
    }

    public void setSubjName(String subjName) {
        this.subjName = subjName;
    }

    public String getDate() {
        try {
            String d[]=date.substring(0,date.indexOf(".")).split("T");
            return DateUtils.getDesiredDateFormat(SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T,SIMPLE_DATE_FORMAT_DAY_FIRST_AND_T,d[0]);
        }catch (Exception e){
            return date;
        }
        //return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }
}
