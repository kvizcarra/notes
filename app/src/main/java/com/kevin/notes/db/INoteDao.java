package com.kevin.notes.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface INoteDao {

    @Query("SELECT * FROM note")
    LiveData<List<Note>> getNotes();

    @Query("SELECT * FROM note WHERE id = :noteId")
    LiveData<Note> getNote(int noteId);

    @Insert
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);
}
