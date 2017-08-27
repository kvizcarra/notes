package com.kevin.notes;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.kevin.notes.db.Note;
import com.kevin.notes.db.NoteDao;


public class NoteDetailViewModel extends ViewModel {

    private final NoteDao noteDao;

    public NoteDetailViewModel() {
        noteDao = NoteApplication.getDb().noteDao();
    }

    public LiveData<Note> getNote(int id) {
        return noteDao.loadNote(id);
    }
}
