package com.pharma.view_model.parent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.OnlineClassesListModel;
import com.pharma.repository.student.EventsRepository;
import com.pharma.repository.student.OnlineClassesRepository;

public class OnlineClassesViewModel extends AndroidViewModel {
    OnlineClassesRepository onlineClassesRep;
    private LiveData<OnlineClassesListModel> onlineClassesRepResponseLiveData;
    public OnlineClassesViewModel(@NonNull Application application) {
        super(application);
        onlineClassesRep = new OnlineClassesRepository(application);
        this.onlineClassesRepResponseLiveData = onlineClassesRep.getOnlineClasses();
    }
    public LiveData<OnlineClassesListModel> getOnlineClassesResponseLiveData() {
        return onlineClassesRepResponseLiveData;
    }
}
