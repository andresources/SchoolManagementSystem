package com.pharma.model.parent;

public class StudentData {
    private UserDataModel str;
    public StudentData(UserDataModel str){
        this.str=str;
    }
    public UserDataModel getName(){
        return str;
    }
}
