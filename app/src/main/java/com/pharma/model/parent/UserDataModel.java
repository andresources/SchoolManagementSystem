package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDataModel {
    @SerializedName("clsName")
    @Expose
    private String clsName;

    @SerializedName("secName")
    @Expose
    private String secName;

    @SerializedName("deptName")
    @Expose
    private String deptName;

    @SerializedName("sno")
    @Expose
    private int sno;

    @SerializedName("nameF")
    @Expose
    private String nameF;

    @SerializedName("nameL")
    @Expose
    private String nameL;

    @SerializedName("fathName")
    @Expose
    private String fathName;

    @SerializedName("acadYear")
    @Expose
    private int acadYear;

    @SerializedName("dateJ")
    @Expose
    private String dateJ;

    @SerializedName("hstl")
    @Expose
    private int hstl;

    @SerializedName("ph")
    @Expose
    private String ph;

    @SerializedName("gender")
    @Expose
    private int gender;

    @SerializedName("dob")
    @Expose
    private String dob;

    @SerializedName("nation")
    @Expose
    private String nation;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("addr")
    @Expose
    private String addr;

    @SerializedName("mobF")
    @Expose
    private String mobF;

    @SerializedName("rollNo")
    @Expose
    private String rollNo;

    @SerializedName("motherTongue")
    @Expose
    private String motherTongue;

    @SerializedName("oldTcNo")
    @Expose
    private String oldTcNo;

    @SerializedName("imgFolder")
    @Expose
    private String imgFolder;


    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getNameF() {
        return nameF;
    }

    public void setNameF(String nameF) {
        this.nameF = nameF;
    }

    public String getNameL() {
        return nameL;
    }

    public void setNameL(String nameL) {
        this.nameL = nameL;
    }

    public String getFathName() {
        return fathName;
    }

    public void setFathName(String fathName) {
        this.fathName = fathName;
    }

    public int getAcadYear() {
        return acadYear;
    }

    public void setAcadYear(int acadYear) {
        this.acadYear = acadYear;
    }

    public String getDateJ() {
        return dateJ;
    }

    public void setDateJ(String dateJ) {
        this.dateJ = dateJ;
    }

    public int getHstl() {
        return hstl;
    }

    public void setHstl(int hstl) {
        this.hstl = hstl;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getMobF() {
        return mobF;
    }

    public void setMobF(String mobF) {
        this.mobF = mobF;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getMotherTongue() {
        return motherTongue;
    }

    public void setMotherTongue(String motherTongue) {
        this.motherTongue = motherTongue;
    }

    public String getOldTcNo() {
        return oldTcNo;
    }

    public void setOldTcNo(String oldTcNo) {
        this.oldTcNo = oldTcNo;
    }

    public String getImgFolder() {
        return imgFolder;
    }

    public void setImgFolder(String imgFolder) {
        this.imgFolder = imgFolder;
    }
}
