package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlertModel {
    @SerializedName("displayAlert")
    @Expose
    private String displayAlert;

    @SerializedName("displayAlertType")
    @Expose
    private String displayAlertType;

    @SerializedName("displayAlertTitle")
    @Expose
    private String displayAlertTitle;

    @SerializedName("displayAlertDes")
    @Expose
    private String displayAlertDes;

    @SerializedName("displayImg")
    @Expose
    private String displayImg;

    @SerializedName("imageName")
    @Expose
    private String imageName;

    @SerializedName("imageRef")
    @Expose
    private String imageRef;

    public String getDisplayAlert() {
        return displayAlert;
    }

    public void setDisplayAlert(String displayAlert) {
        this.displayAlert = displayAlert;
    }

    public String getDisplayAlertType() {
        return displayAlertType;
    }

    public void setDisplayAlertType(String displayAlertType) {
        this.displayAlertType = displayAlertType;
    }

    public String getDisplayAlertTitle() {
        return displayAlertTitle;
    }

    public void setDisplayAlertTitle(String displayAlertTitle) {
        this.displayAlertTitle = displayAlertTitle;
    }

    public String getDisplayAlertDes() {
        return displayAlertDes;
    }

    public void setDisplayAlertDes(String displayAlertDes) {
        this.displayAlertDes = displayAlertDes;
    }

    public String getDisplayImg() {
        return displayImg;
    }

    public void setDisplayImg(String displayImg) {
        this.displayImg = displayImg;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageRef() {
        return imageRef;
    }

    public void setImageRef(String imageRef) {
        this.imageRef = imageRef;
    }

    public String getImageUrl(){
        return "https://erp.tapasyaedu.com:9443/t/imgImgDisp.action?imgFolder="+displayImg+"&refType="+imageName+"&ref="+imageRef;
    }
}
