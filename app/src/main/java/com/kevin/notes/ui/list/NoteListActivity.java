package com.kevin.notes.ui.list;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.kevin.notes.NoteApplication;
import com.kevin.notes.R;
import com.kevin.notes.db.Note;
import com.kevin.notes.db.NoteRepository;
import com.kevin.notes.ui.CustomViewModelFactory;
import com.kevin.notes.ui.detail.NoteDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteListActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private static final String TAG = NoteListActivity.class.getCanonicalName();

    private final LifecycleRegistry registry = new LifecycleRegistry(this);

    private NoteListAdapter noteAdapter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.fab) FloatingActionButton fab;

    @Override
    public LifecycleRegistry getLifecycle() {
        return registry;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteListAdapter();
        noteAdapter.setOnNoteClickListener(new NoteListAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
                openNoteDetail(note.getId());
            }
        });
        recyclerView.setAdapter(noteAdapter);

        // Dependency injection
        CustomViewModelFactory customViewModelFactory = new CustomViewModelFactory(new NoteRepository(NoteApplication.getDb().noteDao()));

        NoteListViewModel viewModel = ViewModelProviders.of(this, customViewModelFactory).get(NoteListViewModel.class);
        viewModel.getNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                noteAdapter.setNotes(notes);
            }
        });
    }

    @OnClick(R.id.fab)
    public void onFabClick() {
        openNoteDetail();
    }

    private void openNoteDetail() {
        NoteDetailActivity.start(this);
    }
    private void openNoteDetail(int noteId) {
        NoteDetailActivity.start(this, noteId);
    }
}
