package com.kevin.notes.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note")
    LiveData<List<Note>> loadAllNotes();

    @Query("SELECT * FROM note WHERE id = :id")
    LiveData<Note> loadNote(int id);

    @Insert
    void insertNote(Note note);

    @Insert
    void insertNotes(Note... notes);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);
}
