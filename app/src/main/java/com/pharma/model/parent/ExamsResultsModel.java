package com.pharma.model.parent;

public class ExamsResultsModel {
    private String subject;
    private String max_marks;
    private String min_marks;
    private String marks;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMax_marks() {
        return max_marks;
    }

    public void setMax_marks(String max_marks) {
        this.max_marks = max_marks;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getMin_marks() {
        return min_marks;
    }

    public void setMin_marks(String min_marks) {
        this.min_marks = min_marks;
    }
}
