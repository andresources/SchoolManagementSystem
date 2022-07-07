package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ElearnLinkListModel {
    @SerializedName("clsRef")
    @Expose
    private String clsRef;

    @SerializedName("elearnLink")
    @Expose
    private List<ElearnLinkModel> elearnLink;

    public String getClsRef() {
        return clsRef;
    }

    public void setClsRef(String clsRef) {
        this.clsRef = clsRef;
    }

    public List<ElearnLinkModel> getElearnLink() {
        return elearnLink;
    }

    public void setElearnLink(List<ElearnLinkModel> elearnLink) {
        this.elearnLink = elearnLink;
    }
}
