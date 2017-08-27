package com.kevin.notes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.notes.db.Note;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    private OnNoteClickListener onNoteClickListener;

    private List<Note> notes;

    interface OnNoteClickListener {
        void onNoteClick(Note note);
    }

    void setOnNoteClickListener(OnNoteClickListener listener) {
        onNoteClickListener = listener;
    }

    public void setNotes (List<Note> notes) {
        // TODO: Use DiffUtil
        this.notes = notes;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleTextView.setText(notes.get(position).getTitle());
        holder.valueTextView.setText(notes.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return notes != null ? notes.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.note_title)
        TextView titleTextView;
        @BindView(R.id.note_value)
        TextView valueTextView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteClickListener.onNoteClick(notes.get(getAdapterPosition()));
        }
    }
}
