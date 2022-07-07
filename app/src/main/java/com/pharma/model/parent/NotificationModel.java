package com.pharma.model.parent;

import static com.pharma.utils.DateUtils.SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T;
import static com.pharma.utils.DateUtils.SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_TIME_24;
import static com.pharma.utils.DateUtils.SIMPLE_TIME_FORMAT_24_HOURS;
import static com.pharma.utils.DateUtils.SIMPLE_TIME_FORMAT_AM_PM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pharma.utils.DateUtils;

public class NotificationModel {
    @SerializedName("alertType")
    @Expose
    private String title;

    @SerializedName("notifyTime")
    @Expose
    private String ndate;

    @SerializedName("notice")
    @Expose
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNdate() {
        try {
            String d[]=ndate.substring(0,ndate.indexOf(".")).split("T");
            return DateUtils.getDesiredDateFormat(SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T,SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_TIME_24,d[0])+", "+DateUtils.getDesiredDateFormat(SIMPLE_TIME_FORMAT_24_HOURS,SIMPLE_TIME_FORMAT_AM_PM,d[1]);
        }catch (Exception e){
            return ndate;
        }
        //return ndate;
    }

    public void setNdate(String ndate) {
        this.ndate = ndate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
