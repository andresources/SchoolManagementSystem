package com.pharma.model.parent;

import static com.pharma.utils.DateUtils.SIMPLE_TIME_FORMAT_24_HOURS;
import static com.pharma.utils.DateUtils.SIMPLE_TIME_FORMAT_AM_PM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pharma.utils.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OnlineClassesModel {
    private String month;
    private int day;

    @SerializedName("subjectName")
    @Expose
    private String subject;

    @SerializedName("path")
    @Expose
    private String path;

    @SerializedName("stTime")
    @Expose
    private String stTime;

    @SerializedName("endTime")
    @Expose
    private String endTime;

    private String dayOfWeek;
    private String time;

    public String getMonth() {
        try {
            String d[] = stTime.substring(0, stTime.indexOf(".")).split("T");
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            Date dt1 = format1.parse(d[0]);
            DateFormat format2 = new SimpleDateFormat("MMM");
            return format2.format(dt1);
        }catch (Exception e){
            return "";
        }
        //return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getDay() {
        try {
            String d[] = stTime.substring(0, stTime.indexOf(".")).split("T");
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            Date dt1 = format1.parse(d[0]);
            DateFormat format2 = new SimpleDateFormat("dd");
            return Integer.parseInt(format2.format(dt1));
        }catch (Exception e){
            return 0;
        }
       // return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDayOfWeek() {
        try {
            String d[] = stTime.substring(0, stTime.indexOf(".")).split("T");
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            Date dt1 = format1.parse(d[0]);
            DateFormat format2 = new SimpleDateFormat("EEEE");
            return format2.format(dt1);
        }catch (Exception e){
            return "";
        }

    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getTime() {
        try {
            String d[] = stTime.substring(0, stTime.indexOf(".")).split("T");
            return DateUtils.getDesiredDateFormat(SIMPLE_TIME_FORMAT_24_HOURS,SIMPLE_TIME_FORMAT_AM_PM,d[1]);
        }catch (Exception e){
            return "";
        }

    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStTime() {
        return stTime;
    }

    public void setStTime(String stTime) {
        this.stTime = stTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
