package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExamSingleDataModel {
    @SerializedName("examDetails")
    @Expose
    private ExamsDataModel examDetails;

    public ExamsDataModel getExamDetails() {
        return examDetails;
    }

    public void setExamDetails(ExamsDataModel examDetails) {
        this.examDetails = examDetails;
    }
}
