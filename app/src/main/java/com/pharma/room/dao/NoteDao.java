package com.pharma.room.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.pharma.room.entities.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM Note")
    LiveData<List<Note>> getAllNotes();

    // Dao method to insert note
    @Insert(onConflict = REPLACE)
    void insertNote(Note note);

    // Dao method to delete note
    @Delete
    void deleteNote(Note note);
}
