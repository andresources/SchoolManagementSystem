package com.pharma;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.room.OnConflictStrategy;

public class MyOwnLifeCycleObserver implements DefaultLifecycleObserver {
    private MutableLiveData<String> m=new MutableLiveData<>();
    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        Log.i("LO","onCreate-MyOwnLife");
        m.setValue("onCreate");
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        Log.i("LO","onStart-MyOwnLife");
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        Log.i("LO","onResume-MyOwnLife");
        m.setValue("onResume");
    }
    public LiveData<String> getData(){
        return m;
    }

}
