package com.pharma.view_model.parent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pharma.model.parent.NotificationListModel;
import com.pharma.repository.student.NotificationRepository;


public class NotificationViewModel extends AndroidViewModel {
    NotificationRepository notificationsRep;
    private LiveData<NotificationListModel> notificationsResponseLiveData;
    public NotificationViewModel(@NonNull Application application) {
        super(application);
        notificationsRep = new NotificationRepository(application);
    }
    public LiveData<NotificationListModel> getNotificationResponseLiveData() {
        this.notificationsResponseLiveData = notificationsRep.getNotifications();
        return notificationsResponseLiveData;
    }
}

