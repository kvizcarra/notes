package com.kevin.notes.ui.list;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.kevin.notes.db.INoteRepository;
import com.kevin.notes.db.Note;

import java.util.List;

public class NoteListViewModel extends ViewModel {

    private final INoteRepository noteRepository;

    public NoteListViewModel(INoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public LiveData<List<Note>> getNotes() {
        return noteRepository.getNotes();
    }
}
