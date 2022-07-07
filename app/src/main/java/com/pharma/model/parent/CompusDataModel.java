package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompusDataModel {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("mob")
    @Expose
    private String mob;

    @SerializedName("addr")
    @Expose
    private String addr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
