package com.kevin.notes;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.kevin.notes.db.Note;

import java.util.List;

class NoteListViewModel extends ViewModel {

    private LiveData<List<Note>> notes;

    public NoteListViewModel() {
        notes = NoteApplication.getDb().noteDao().loadAllNotes();
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }
}
