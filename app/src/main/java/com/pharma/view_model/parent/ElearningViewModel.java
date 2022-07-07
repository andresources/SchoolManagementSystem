package com.pharma.view_model.parent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pharma.model.parent.ElearningNewModel;
import com.pharma.model.parent.EventsListModel;
import com.pharma.repository.student.ElearningRepository;
import com.pharma.repository.student.EventsRepository;

public class ElearningViewModel extends AndroidViewModel {
    ElearningRepository elearningRep;
    private LiveData<ElearningNewModel> elearningResponseLiveData;
    public ElearningViewModel(@NonNull Application application) {
        super(application);
        elearningRep = new ElearningRepository(application);
        this.elearningResponseLiveData = elearningRep.getElearning();
    }
    public LiveData<ElearningNewModel> getElearningResponseLiveData() {
        return elearningResponseLiveData;
    }
}
