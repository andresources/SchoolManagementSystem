package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExamsDataModel {
    @SerializedName("examName")
    @Expose
    private String examName;

    @SerializedName("perc")
    @Expose
    private String perc;

    @SerializedName("grade")
    @Expose
    private String grade;

    @SerializedName("ettData")
    @Expose
    private List<ExamsSubjectsTableModel> ettData;

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public List<ExamsSubjectsTableModel> getEttData() {
        return ettData;
    }

    public void setEttData(List<ExamsSubjectsTableModel> ettData) {
        this.ettData = ettData;
    }

    public String getPerc() {
        return perc!=null?perc:"0";
    }

    public void setPerc(String perc) {
        this.perc = perc;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
