package com.pharma.di;
import com.pharma.model.parent.Employee;
import com.pharma.model.parent.StudentData;
import com.pharma.model.parent.UserDataModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StudentModule {
    private UserDataModel uname;

    public StudentModule(UserDataModel uname){
        this.uname = uname;
    }

    @Singleton
    @Provides
    public StudentData getStudent(){
        return new StudentData(uname);
    }
}
