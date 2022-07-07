package com.pharma.view_model.parent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.ExamListModel;
import com.pharma.repository.student.EventsRepository;
import com.pharma.repository.student.ExamsRepository;

public class ExamsViewModel extends AndroidViewModel {
    ExamsRepository examsRep;
    private LiveData<ExamListModel> examsResponseLiveData;
    public ExamsViewModel(@NonNull Application application) {
        super(application);
        examsRep = new ExamsRepository(application);
        this.examsResponseLiveData = examsRep.getEvents();
    }
    public LiveData<ExamListModel> getEventsResponseLiveData() {
        return examsResponseLiveData;
    }
}
