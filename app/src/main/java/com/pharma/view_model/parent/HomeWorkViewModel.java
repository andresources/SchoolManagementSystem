package com.pharma.view_model.parent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.HomeWorkListModel;
import com.pharma.repository.student.EventsRepository;
import com.pharma.repository.student.HomeWorkRepository;

public class HomeWorkViewModel extends AndroidViewModel {
    HomeWorkRepository homeWorksRep;
    private LiveData<HomeWorkListModel> homeworkResponseLiveData;
    public HomeWorkViewModel(@NonNull Application application) {
        super(application);
        homeWorksRep = new HomeWorkRepository(application);
        this.homeworkResponseLiveData = homeWorksRep.getHomeworks();
    }
    public LiveData<HomeWorkListModel> getHomeWorkResponseLiveData() {
        return homeworkResponseLiveData;
    }
}
