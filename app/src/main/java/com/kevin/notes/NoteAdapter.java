package com.kevin.notes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    private List<Note> notes;

    public void setNotes (List<Note> notes) {
        // TODO: Use DiffUtil
        this.notes = notes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleTextView.setText(notes.get(position).title);
        holder.valueTextView.setText(notes.get(position).value);
    }

    @Override
    public int getItemCount() {
        return notes != null ? notes.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView valueTextView;

        ViewHolder(View view) {
            super(view);
            titleTextView = (TextView) view.findViewById(R.id.note_title);
            valueTextView = (TextView) view.findViewById(R.id.note_value);
        }
    }


}
