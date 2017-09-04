package com.kevin.notes.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract INoteDao noteDao();
}
