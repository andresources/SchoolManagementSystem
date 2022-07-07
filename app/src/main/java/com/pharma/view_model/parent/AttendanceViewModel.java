package com.pharma.view_model.parent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pharma.model.parent.AttendanceListModel;
import com.pharma.model.parent.EventsListModel;
import com.pharma.repository.student.AttendanceRepository;
import com.pharma.repository.student.EventsRepository;

public class AttendanceViewModel extends AndroidViewModel {
    AttendanceRepository attendanceRep;
    private LiveData<AttendanceListModel> attendanceResponseLiveData;
    public AttendanceViewModel(@NonNull Application application) {
        super(application);
        attendanceRep = new AttendanceRepository(application);
    }
    public LiveData<AttendanceListModel> getAttendanceResponseLiveData(String month,String year) {
        this.attendanceResponseLiveData = attendanceRep.getAttendance(year,month);
        return attendanceResponseLiveData;
    }
}
