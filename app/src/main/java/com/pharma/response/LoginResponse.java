package com.pharma.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pharma.model.parent.CompusDataModel;
import com.pharma.model.parent.StuDetailsModel;
import com.pharma.model.parent.UserDataModel;

public class LoginResponse {
    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("userData")
    @Expose
    private UserDataModel userData;

    @SerializedName("compData")
    @Expose
    private CompusDataModel compData;

    @SerializedName("stuDetails")
    @Expose
    private StuDetailsModel stuDetails;


    @SerializedName("auth_token")
    @Expose
    private String jwt_token;

    private long resTime;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getJwt_token() {
        return jwt_token;
    }

    public void setJwt_token(String jwt_token) {
        this.jwt_token = jwt_token;
    }

    public UserDataModel getUserData() {
        return userData;
    }

    public void setUserData(UserDataModel userData) {
        this.userData = userData;
    }

    public long getResTime() {
        return resTime;
    }

    public void setResTime(long resTime) {
        this.resTime = resTime;
    }

    public CompusDataModel getCompData() {
        return compData;
    }

    public void setCompData(CompusDataModel compData) {
        this.compData = compData;
    }

    public StuDetailsModel getStuDetails() {
        return stuDetails;
    }

    public void setStuDetails(StuDetailsModel stuDetails) {
        this.stuDetails = stuDetails;
    }
}
