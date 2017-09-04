package com.kevin.notes.db;

import android.arch.lifecycle.LiveData;

import java.util.List;

public interface INoteRepository {
    LiveData<List<Note>> getNotes();

    LiveData<Note> getNote(int noteId);

    void insertNote(Note note);

    void updateNote(Note note);

    void deleteNote(Note note);
}
