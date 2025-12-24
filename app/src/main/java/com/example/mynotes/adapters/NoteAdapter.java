package com.example.mynotes.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mynotes.R;
import com.example.mynotes.models.Note;

import java.util.List;

public class NoteAdapter extends BaseAdapter {
    private Context context;
    private List<Note> notes;
    private LayoutInflater inflater;

    public NoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.note_item, parent, false);
            holder = new ViewHolder();
            holder.tvNom = convertView.findViewById(R.id.tvNoteNom);
            holder.tvDate = convertView.findViewById(R.id.tvNoteDate);
            holder.viewPriorite = convertView.findViewById(R.id.viewPrioriteIndicator);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Note note = notes.get(position);
        holder.tvNom.setText(note.getNom());
        holder.tvDate.setText(note.getDate());

        // Définir la couleur selon la priorité
        int couleur;
        switch (note.getPriorite()) {
            case "Haute":
                couleur = Color.parseColor("#FF5252"); // Rouge
                break;
            case "Moyenne":
                couleur = Color.parseColor("#FFA726"); // Orange
                break;
            case "Basse":
                couleur = Color.parseColor("#66BB6A"); // Vert
                break;
            default:
                couleur = Color.GRAY;
        }
        holder.viewPriorite.setBackgroundColor(couleur);

        return convertView;
    }

    static class ViewHolder {
        TextView tvNom;
        TextView tvDate;
        View viewPriorite;
    }
}