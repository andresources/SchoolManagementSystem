package com.pharma.view_model.parent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pharma.model.parent.PaymentHistoryModel;
import com.pharma.model.parent.PaymentsListModel;
import com.pharma.repository.student.PaymentsRepository;
import com.pharma.repository.student.StudentPaymentsRepository;

public class StudentPaymentsViewModel extends AndroidViewModel {
    StudentPaymentsRepository paymentsRep;
    private LiveData<PaymentHistoryModel> paymentsResponseLiveData;
    public StudentPaymentsViewModel(@NonNull Application application) {
        super(application);
        paymentsRep = new StudentPaymentsRepository(application);
    }
    public LiveData<PaymentHistoryModel> getPaymentsResponseLiveData() {
        this.paymentsResponseLiveData = paymentsRep.getPayments();
        return paymentsResponseLiveData;
    }
}