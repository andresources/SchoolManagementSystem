package com.pharma.view_model.parent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pharma.model.parent.EventsListModel;
import com.pharma.repository.student.EventsRepository;
import com.pharma.repository.student.LoginRepository;
import com.pharma.response.LoginResponse;

public class EventsViewModel extends AndroidViewModel {
    EventsRepository eventsRep;
    private LiveData<EventsListModel> eventsResponseLiveData;
    public EventsViewModel(@NonNull Application application) {
        super(application);
        eventsRep = new EventsRepository(application);
        this.eventsResponseLiveData = eventsRep.getEvents();
    }
    public LiveData<EventsListModel> getEventsResponseLiveData() {
        return eventsResponseLiveData;
    }
}
