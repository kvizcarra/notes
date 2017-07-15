package com.kevin.notes;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NoteListViewModel extends ViewModel {

    private MutableLiveData<List<Note>> notes;

    public LiveData<List<Note>> getNotes() {
        if (notes == null) {
            notes = new MutableLiveData<>();
            loadNotes();
        }
        return notes;
    }

    private void loadNotes() {
        notes.setValue(new ArrayList<>(Arrays.asList(
                new Note("Title 1", "Value 1"),
                new Note("Title 2", "Value 2"),
                new Note("Title 3", "Value 3")
        )));
    }
}
