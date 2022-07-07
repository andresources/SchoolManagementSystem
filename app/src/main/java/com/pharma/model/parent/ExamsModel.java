package com.pharma.model.parent;

public class ExamsModel {
    private String term;
    private String termDate;
    private String termclass;
    private int percentage;
    private String grade;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTermDate() {
        return termDate;
    }

    public void setTermDate(String termDate) {
        this.termDate = termDate;
    }

    public String getTermclass() {
        return termclass;
    }

    public void setTermclass(String termclass) {
        this.termclass = termclass;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
