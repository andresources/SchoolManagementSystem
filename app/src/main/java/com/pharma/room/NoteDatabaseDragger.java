package com.pharma.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.pharma.room.dao.NoteDao;
import com.pharma.room.entities.Note;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NoteDatabaseDragger extends RoomDatabase {
    public abstract NoteDao noteDao();
}
