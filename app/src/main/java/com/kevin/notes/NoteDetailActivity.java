package com.kevin.notes;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.kevin.notes.db.Note;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteDetailActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private static final String NOTE_ID = "NOTE_ID";

    private final LifecycleRegistry registry = new LifecycleRegistry(this);

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.noteTitleEditText) EditText noteTitleEditText;
    @BindView(R.id.noteValueEditText) EditText noteValueEditText;

    private int noteId;

    @Override
    public LifecycleRegistry getLifecycle() {
        return registry;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, NoteDetailActivity.class);
        context.startActivity(starter);
    }
    public static void start(Context context, int id) {
        Intent starter = new Intent(context, NoteDetailActivity.class);
        starter.putExtra(NOTE_ID, id);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent.getExtras() != null && intent.getExtras().containsKey(NOTE_ID)) {
            noteId = intent.getIntExtra(NOTE_ID, 0);
            NoteDetailViewModel viewModel = ViewModelProviders.of(this).get(NoteDetailViewModel.class);

            subscribeUi(viewModel);
        }
    }

    private void subscribeUi(NoteDetailViewModel viewModel) {
        viewModel.getNote(noteId).observe(this, new Observer<Note>() {
            @Override
            public void onChanged(@Nullable Note note) {
                if (note != null) {
                    noteTitleEditText.setText(note.getTitle());
                    noteValueEditText.setText(note.getValue());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                // Insert note
                if (noteId == 0) {
                    Note note = new Note(noteTitleEditText.getText().toString(), noteValueEditText.getText().toString());
                    new AsyncTask<Note, Void, Void>() {
                        @Override
                        protected Void doInBackground(Note... notes) {
                            NoteApplication.getDb().noteDao().insertNote(notes[0]);
                            return null;
                        }
                    }.execute(note);
                    Toast.makeText(this, "Note inserted!", Toast.LENGTH_SHORT).show();
                } else {
                    // Update note
                    Note note = new Note(noteId, noteTitleEditText.getText().toString(), noteValueEditText.getText().toString());
                    new AsyncTask<Note, Void, Void>() {
                        @Override
                        protected Void doInBackground(Note... notes) {
                            NoteApplication.getDb().noteDao().updateNote(notes[0]);
                            return null;
                        }
                    }.execute(note);
                    Toast.makeText(this, "Note updated!", Toast.LENGTH_SHORT).show();
                }

                finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
