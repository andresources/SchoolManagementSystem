package com.pharma.view_model.parent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.PaymentsListModel;
import com.pharma.repository.student.EventsRepository;
import com.pharma.repository.student.PaymentsRepository;

public class PaymentsViewModel extends AndroidViewModel {
    PaymentsRepository paymentsRep;
    private LiveData<PaymentsListModel> paymentsResponseLiveData;
    public PaymentsViewModel(@NonNull Application application) {
        super(application);
        paymentsRep = new PaymentsRepository(application);
        this.paymentsResponseLiveData = paymentsRep.getPayments();
    }
    public LiveData<PaymentsListModel> getPaymentsResponseLiveData() {
        return paymentsResponseLiveData;
    }
}
