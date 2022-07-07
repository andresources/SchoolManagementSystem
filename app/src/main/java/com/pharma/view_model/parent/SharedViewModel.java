package com.pharma.view_model.parent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class SharedViewModel extends AndroidViewModel {
    MutableLiveData<CharSequence> mutableLiveData=new MutableLiveData<>();
    public SharedViewModel(@NonNull Application application) {
        super(application);
    }
    public void setData(CharSequence s){
        mutableLiveData.setValue(s);
    }

    public MutableLiveData<CharSequence> getData(){
        return mutableLiveData;
    }
}
