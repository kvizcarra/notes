package com.kevin.notes;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.kevin.notes.db.AppDatabase;


public class NoteApplication extends Application {

    private static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();

        if (db == null) {
            db = Room.databaseBuilder(this, AppDatabase.class, "note-database").build();
        }
    }

    public static AppDatabase getDb() {
        return db;
    }
}
