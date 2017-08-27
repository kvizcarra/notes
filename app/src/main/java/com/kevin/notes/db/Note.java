package com.kevin.notes.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String value;

    public Note(int id, String title, String value) {
        this.id = id;
        this.title = title;
        this.value = value;
    }

    @Ignore
    public Note(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }
}
