package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainFeeCatgModel {
    @SerializedName("catgName")
    @Expose
    private String catgName;

    @SerializedName("installment")
    @Expose
    private String installment;

    @SerializedName("dueDate")
    @Expose
    private String dueDate;

    @SerializedName("dueAmt")
    @Expose
    private String dueAmt;

    public String getCatgName() {
        return catgName;
    }

    public void setCatgName(String catgName) {
        this.catgName = catgName;
    }

    public String getInstallment() {
        return installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueAmt() {
        return dueAmt;
    }

    public void setDueAmt(String dueAmt) {
        this.dueAmt = dueAmt;
    }
}
