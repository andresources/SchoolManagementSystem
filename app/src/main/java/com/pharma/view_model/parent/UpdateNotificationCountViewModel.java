package com.pharma.view_model.parent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pharma.model.parent.NotificationListModel;
import com.pharma.model.parent.NotificationUpdateModel;
import com.pharma.repository.student.NotificationRepository;
import com.pharma.repository.student.NotificationUpdateRepository;


public class UpdateNotificationCountViewModel extends AndroidViewModel {
    NotificationUpdateRepository notificationsRep;
    private LiveData<NotificationUpdateModel> notificationsResponseLiveData;
    public UpdateNotificationCountViewModel(@NonNull Application application) {
        super(application);
        notificationsRep = new NotificationUpdateRepository(application);
        this.notificationsResponseLiveData = notificationsRep.getTransactions();
    }
    public LiveData<NotificationUpdateModel> updateNotificationCntViewModel() {
        return notificationsResponseLiveData;
    }
}