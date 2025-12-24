package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotes.adapters.NoteAdapter;
import com.example.mynotes.models.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity {
    private ListView listView;
    private NoteAdapter adapter;
    private ArrayList<Note> notesList;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        // Initialisation
        listView = findViewById(R.id.listViewNotes);
        fabAdd = findViewById(R.id.fabAddNote);
        notesList = new ArrayList<>();

        // Ajouter des notes exemple (pour test)
        notesList.add(new Note("Réunion projet", "Discussion sur l'avancement du projet MyNotes", "24/12/2025", "Haute"));
        notesList.add(new Note("Courses", "Acheter du pain, lait et oeufs", "25/12/2025", "Basse"));
        notesList.add(new Note("Appel client", "Contacter le client pour validation", "26/12/2025", "Moyenne"));

        // Configuration de l'adaptateur
        adapter = new NoteAdapter(this, notesList);
        listView.setAdapter(adapter);

        // Clic sur une note pour voir les détails
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note selectedNote = notesList.get(position);
                Intent intent = new Intent(NoteListActivity.this, DetailsNoteActivity.class);
                intent.putExtra("note", selectedNote);
                startActivity(intent);
            }
        });

        // Bouton pour ajouter une nouvelle note
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteListActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Note newNote = data.getParcelableExtra("newNote");
            if (newNote != null) {
                notesList.add(newNote);
                adapter.notifyDataSetChanged();
            }
        }
    }
}