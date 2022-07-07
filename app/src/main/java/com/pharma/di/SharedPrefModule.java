package com.pharma.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefModule {
    private Context mContext;
    public SharedPrefModule(Context cnt){
        mContext = cnt;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return mContext;
    }

    @Singleton
    @Provides
    public SharedPreferences provideSharedPreferences(Context cnt){
        return PreferenceManager.getDefaultSharedPreferences(cnt);
    }
}
