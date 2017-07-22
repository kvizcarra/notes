package com.kevin.notes;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteDetailActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private static final String NOTE = "NOTE";

    private final LifecycleRegistry registry = new LifecycleRegistry(this);

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.noteTitleEditText) EditText noteTitleEditText;
    @BindView(R.id.noteValueEditText) EditText noteValueEditText;

    @Override
    public LifecycleRegistry getLifecycle() {
        return registry;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, NoteDetailActivity.class);
        context.startActivity(starter);
    }
    public static void start(Context context, Note note) {
        Intent starter = new Intent(context, NoteDetailActivity.class);
        starter.putExtra(NOTE, note);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent.getExtras() != null && intent.getExtras().containsKey(NOTE)) {
            Note note = (Note) intent.getSerializableExtra(NOTE);
            noteTitleEditText.setText(note.title);
            noteValueEditText.setText(note.value);
        }
    }
}
