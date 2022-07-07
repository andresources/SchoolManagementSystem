package com.pharma.model.parent;

import java.util.List;

public class ELearningSectionModel {
    private String section_name;
    private List<ELearningModel> list;

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public List<ELearningModel> getList() {
        return list;
    }

    public void setList(List<ELearningModel> list) {
        this.list = list;
    }
}
