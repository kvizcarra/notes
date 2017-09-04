package com.kevin.notes.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.kevin.notes.db.INoteRepository;
import com.kevin.notes.db.Note;


public class NoteDetailViewModel extends ViewModel {
    private final INoteRepository noteRepository;

    public NoteDetailViewModel(INoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public LiveData<Note> getNote(int noteId) {
        return noteRepository.getNote(noteId);
    }
}
