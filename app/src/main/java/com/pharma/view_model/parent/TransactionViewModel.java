package com.pharma.view_model.parent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pharma.model.parent.ElearningNewModel;
import com.pharma.model.parent.TransactionsListModel;
import com.pharma.repository.student.ElearningRepository;
import com.pharma.repository.student.TransactionsRepository;

public class TransactionViewModel extends AndroidViewModel {
    TransactionsRepository transactionsRepository;
    private LiveData<TransactionsListModel> transactionResponseLiveData;
    public TransactionViewModel(@NonNull Application application) {
        super(application);
        transactionsRepository = new TransactionsRepository(application);
    }
    public LiveData<TransactionsListModel> getTransactionResponseLiveData() {
        this.transactionResponseLiveData = transactionsRepository.getTransactions();
        return transactionResponseLiveData;
    }
}