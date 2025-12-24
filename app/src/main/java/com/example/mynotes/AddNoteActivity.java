package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotes.models.Note;

public class AddNoteActivity extends AppCompatActivity {
    private EditText etNom, etDescription, etDate;
    private Spinner spinnerPriorite;
    private Button btnEnregistrer, btnCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // Initialisation des vues
        etNom = findViewById(R.id.etNom);
        etDescription = findViewById(R.id.etDescription);
        etDate = findViewById(R.id.etDate);
        spinnerPriorite = findViewById(R.id.spinnerPriorite);
        btnEnregistrer = findViewById(R.id.btnEnregistrer);
        btnCamera = findViewById(R.id.btnCamera);

        // Configuration du Spinner de priorité
        String[] priorites = {"Basse", "Moyenne", "Haute"};
        ArrayAdapter<String> adapterPriorite = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                priorites
        );
        adapterPriorite.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPriorite.setAdapter(adapterPriorite);

        // Bouton Enregistrer
        btnEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enregistrerNote();
            }
        });

        // Bouton Caméra
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNoteActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });
    }

    private void enregistrerNote() {
        String nom = etNom.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String priorite = spinnerPriorite.getSelectedItem().toString();

        // Validation
        if (nom.isEmpty()) {
            Toast.makeText(this, "Le nom est obligatoire", Toast.LENGTH_SHORT).show();
            return;
        }

        if (description.isEmpty()) {
            Toast.makeText(this, "La description est obligatoire", Toast.LENGTH_SHORT).show();
            return;
        }

        if (date.isEmpty()) {
            Toast.makeText(this, "La date est obligatoire", Toast.LENGTH_SHORT).show();
            return;
        }

        // Créer la note
        Note newNote = new Note(nom, description, date, priorite);

        // Retourner le résultat
        Intent resultIntent = new Intent();
        resultIntent.putExtra("newNote", newNote);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}