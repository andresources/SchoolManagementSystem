package com.pharma.view_model.parent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.TimeTableListModel;
import com.pharma.repository.student.EventsRepository;
import com.pharma.repository.student.TimeTableRepository;

public class TimeTableViewModel extends AndroidViewModel {
    TimeTableRepository timeTableRep;
    private LiveData<TimeTableListModel> timeTableResponseLiveData;
    public TimeTableViewModel(@NonNull Application application) {
        super(application);
        timeTableRep = new TimeTableRepository(application);
        this.timeTableResponseLiveData = timeTableRep.getTimeTable();
    }
    public LiveData<TimeTableListModel> getTimeTableResponseLiveData() {
        return timeTableResponseLiveData;
    }
}
