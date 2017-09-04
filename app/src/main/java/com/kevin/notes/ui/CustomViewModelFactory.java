package com.kevin.notes.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.kevin.notes.ui.detail.NoteDetailViewModel;
import com.kevin.notes.ui.list.NoteListViewModel;
import com.kevin.notes.db.INoteRepository;


public class CustomViewModelFactory implements ViewModelProvider.Factory {
    private final INoteRepository noteRepository;

    public CustomViewModelFactory(INoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NoteListViewModel.class)) {
            return modelClass.cast(new NoteListViewModel(noteRepository));
        } else if (modelClass.isAssignableFrom(NoteDetailViewModel.class)) {
            return modelClass.cast(new NoteDetailViewModel(noteRepository));
        } else {
            throw new IllegalArgumentException("ViewModel of type " + modelClass + " not found.");
        }
    }
}
