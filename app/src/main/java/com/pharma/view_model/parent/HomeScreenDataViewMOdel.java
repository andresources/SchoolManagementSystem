package com.pharma.view_model.parent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pharma.model.parent.ExamListModel;
import com.pharma.model.parent.HomeScreenDataListModel;
import com.pharma.repository.student.ExamsRepository;
import com.pharma.repository.student.HomeScreenDetailsRepository;

public class HomeScreenDataViewMOdel  extends AndroidViewModel {
    HomeScreenDetailsRepository homeDetailsRep;
    private LiveData<HomeScreenDataListModel> homeDetailsResponseLiveData;
    public HomeScreenDataViewMOdel(@NonNull Application application) {
        super(application);
        homeDetailsRep = new HomeScreenDetailsRepository(application);
    }
    public LiveData<HomeScreenDataListModel> getHomeScreenDetailsResponseLiveData() {
        this.homeDetailsResponseLiveData = homeDetailsRep.getHomeScreenData();
        return homeDetailsResponseLiveData;
    }
}