package com.pharma.di;

import android.content.Context;

import androidx.room.Room;

import com.pharma.room.NoteDatabaseDragger;
import com.pharma.room.dao.NoteDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    private Context context;

    public DatabaseModule(Context context) {
        this.context = context;
    }
    /*

    @Singleton
    @Provides
    public Context provideContext(){
        return context;
    }*/

    @Singleton
    @Provides
    public NoteDatabaseDragger provideMyDatabase(Context context){
        return Room.databaseBuilder(context, NoteDatabaseDragger.class, "my-db").build();
    }

    @Singleton
    @Provides
    public NoteDao provideUserDao(NoteDatabaseDragger myDatabase){
        return myDatabase.noteDao();
    }
}
