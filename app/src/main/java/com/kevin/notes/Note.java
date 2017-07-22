package com.kevin.notes;

import java.io.Serializable;

public class Note implements Serializable{

    public String title;
    public String value;

    public Note(String title, String value) {
        this.title = title;
        this.value = value;
    }
}
