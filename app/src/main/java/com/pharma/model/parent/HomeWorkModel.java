package com.pharma.model.parent;

import static com.pharma.utils.DateUtils.SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T;
import static com.pharma.utils.DateUtils.SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_TIME_24;
import static com.pharma.utils.DateUtils.SIMPLE_TIME_FORMAT_24_HOURS;
import static com.pharma.utils.DateUtils.SIMPLE_TIME_FORMAT_AM_PM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pharma.utils.DateUtils;

public class HomeWorkModel {

    @SerializedName("subj")
    @Expose
    private String subject;

    private String topic;

    @SerializedName("assignDate")
    @Expose
    private String assign_date;

    @SerializedName("submissionDate")
    @Expose
    private String last_submission_date;

    @SerializedName("homeWork")
    @Expose
    private String description;

    public String getSubject() {
        return subject;
        //return "From Model";
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopic() {
        //return topic;
        return "From Model";
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAssign_date() {
        //return assign_date;
        try {
            String d[]=assign_date.substring(0,assign_date.indexOf(".")).split("T");
            return DateUtils.getDesiredDateFormat(SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T,SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_TIME_24,d[0])+", "+DateUtils.getDesiredDateFormat(SIMPLE_TIME_FORMAT_24_HOURS,SIMPLE_TIME_FORMAT_AM_PM,d[1]);
        }catch (Exception e){
            return assign_date;
        }
    }

    public void setAssign_date(String assign_date) {
        this.assign_date = assign_date;
    }

    public String getLast_submission_date() {
        try {
            String d[]=last_submission_date.substring(0,last_submission_date.indexOf(".")).split("T");
            return DateUtils.getDesiredDateFormat(SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T,SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_TIME_24,d[0])+", "+DateUtils.getDesiredDateFormat(SIMPLE_TIME_FORMAT_24_HOURS,SIMPLE_TIME_FORMAT_AM_PM,d[1]);
        }catch (Exception e){
            return assign_date;
        }
        //return last_submission_date;
    }

    public void setLast_submission_date(String last_submission_date) {
        this.last_submission_date = last_submission_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
