package com.pharma.model.parent;

import static com.pharma.utils.DateUtils.SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T;
import static com.pharma.utils.DateUtils.SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_TIME_24;
import static com.pharma.utils.DateUtils.SIMPLE_TIME_FORMAT_24_HOURS;
import static com.pharma.utils.DateUtils.SIMPLE_TIME_FORMAT_AM_PM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pharma.utils.DateUtils;

public class EventsModel {
    @SerializedName("eventName")
    @Expose
    private String title;

    @SerializedName("dateFrom")
    @Expose
    private String ndate;

    @SerializedName("eventDescr")
    @Expose
    private String description;

    @SerializedName("eventRef")
    @Expose
    private String eventRef;

    @SerializedName("imgFolder")
    @Expose
    private String imgFolder;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNdate() {
        //return DateUtils.getDesiredDateFormat(SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T,SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_TIME_24,ndate.substring(0,ndate.lastIndexOf(".")));
        //return ndate;
        try {
            String d[]=ndate.substring(0,ndate.indexOf(".")).split("T");
            return DateUtils.getDesiredDateFormat(SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T,SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_TIME_24,d[0])+", "+DateUtils.getDesiredDateFormat(SIMPLE_TIME_FORMAT_24_HOURS,SIMPLE_TIME_FORMAT_AM_PM,d[1]);
        }catch (Exception e){
            return ndate;
        }

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

    public String getImg_url() {
        return "https://erp.tapasyaedu.com:9443/t/imgImgDisp.action?imgFolder="+getImgFolder()+"&refType=event_&ref="+getEventRef();
        //return "https://www.sakshi.com/sites/default/files/styles/section_big_image/public/article_images/2022/05/16/online%20games.jpg";
        //return img_url;
    }


    public String getEventRef() {
        return eventRef;
    }

    public void setEventRef(String eventRef) {
        this.eventRef = eventRef;
    }

    public String getImgFolder() {
        return imgFolder;
    }

    public void setImgFolder(String imgFolder) {
        this.imgFolder = imgFolder;
    }
}
