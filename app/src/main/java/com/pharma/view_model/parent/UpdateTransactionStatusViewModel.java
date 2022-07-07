package com.pharma.view_model.parent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pharma.model.parent.TransactionStatusModel;
import com.pharma.model.parent.TransactionsListModel;
import com.pharma.repository.student.TransactionsRepository;
import com.pharma.repository.student.UpdateTransactionStatusRepositoy;

public class UpdateTransactionStatusViewModel extends AndroidViewModel {
    UpdateTransactionStatusRepositoy transactionsRepository;
    private LiveData<TransactionStatusModel> transactionResponseLiveData;
    public UpdateTransactionStatusViewModel(@NonNull Application application) {
        super(application);
        transactionsRepository = new UpdateTransactionStatusRepositoy(application);

    }
    public LiveData<TransactionStatusModel> getTransactionResponseLiveData(String tstatus, String tid, String tamount, String tdate) {
        this.transactionResponseLiveData = transactionsRepository.getTransStatus( tstatus,  tid,  tamount,  tdate);
        return transactionResponseLiveData;
    }
}
