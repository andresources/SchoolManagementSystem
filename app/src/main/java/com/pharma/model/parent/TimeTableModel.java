package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeTableModel {

    @SerializedName("dayOfWeek")
    @Expose
    private int dayOfWeek;

    @SerializedName("nameSubj")
    @Expose
    private String subject;

    @SerializedName("timings")
    @Expose
    private String timings;

    @SerializedName("staffName")
    @Expose
    private String facality;

    @SerializedName("period")
    @Expose
    private int period;

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getFacality() {
        return facality;
    }

    public void setFacality(String facality) {
        this.facality = facality;
    }

    public String getPeriod() {
        return "Period - "+period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
