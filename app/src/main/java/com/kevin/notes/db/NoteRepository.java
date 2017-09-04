package com.kevin.notes.db;

import android.arch.lifecycle.LiveData;

import java.util.List;

public class NoteRepository implements INoteRepository {
    private final INoteDao noteDao;

    public NoteRepository(INoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public LiveData<List<Note>> getNotes() {
        return noteDao.getNotes();
    }

    @Override
    public LiveData<Note> getNote(int noteId) {
        return noteDao.getNote(noteId);
    }

    @Override
    public void insertNote(Note note) {
        noteDao.insertNote(note);
    }

    @Override
    public void updateNote(Note note) {
        noteDao.updateNote(note);
    }

    @Override
    public void deleteNote(Note note) {
        noteDao.deleteNote(note);
    }
}
