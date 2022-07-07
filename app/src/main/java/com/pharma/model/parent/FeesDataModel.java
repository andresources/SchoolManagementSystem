package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeesDataModel {
    @SerializedName("feetype")
    @Expose
    private String feetype;

    @SerializedName("feetypeName")
    @Expose
    private String feetypeName;

    @SerializedName("fee")
    @Expose
    private String fee;

    @SerializedName("schlr")
    @Expose
    private String schlr;

    @SerializedName("concs")
    @Expose
    private String concs;

    @SerializedName("paid")
    @Expose
    private String paid;

    /*@SerializedName("discPerc")
    @Expose
    private int discPerc;*/

    public String getFeetype() {
        return feetype;
    }

    public void setFeetype(String feetype) {
        this.feetype = feetype;
    }

    public String getFeetypeName() {
        return feetypeName;
    }

    public void setFeetypeName(String feetypeName) {
        this.feetypeName = feetypeName;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getSchlr() {
        return schlr;
    }

    public void setSchlr(String schlr) {
        this.schlr = schlr;
    }

    public String getConcs() {
        return concs;
    }

    public void setConcs(String concs) {
        this.concs = concs;
    }

    public String getPaid() {
        float amm=Float.parseFloat(fee)-Float.parseFloat(schlr)-Float.parseFloat(paid)-Float.parseFloat(concs);
        return ""+amm;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

   /* public int getDiscPerc() {
        return discPerc;
    }

    public void setDiscPerc(int discPerc) {
        this.discPerc = discPerc;
    }*/
}
