package com.example.mynotes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotes.models.Note;

public class DetailsNoteActivity extends AppCompatActivity {
    private TextView tvNom, tvDescription, tvDate, tvPriorite;
    private Button btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_note);

        // Initialisation des vues
        tvNom = findViewById(R.id.tvDetailsNom);
        tvDescription = findViewById(R.id.tvDetailsDescription);
        tvDate = findViewById(R.id.tvDetailsDate);
        tvPriorite = findViewById(R.id.tvDetailsPriorite);
        btnRetour = findViewById(R.id.btnRetour);

        // Récupérer la note passée en paramètre
        Note note = getIntent().getParcelableExtra("note");

        if (note != null) {
            tvNom.setText(note.getNom());
            tvDescription.setText(note.getDescription());
            tvDate.setText(note.getDate());
            tvPriorite.setText(note.getPriorite());

            // Changer la couleur du texte de priorité selon le niveau
            switch (note.getPriorite()) {
                case "Haute":
                    tvPriorite.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    break;
                case "Moyenne":
                    tvPriorite.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
                    break;
                case "Basse":
                    tvPriorite.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                    break;
            }
        }

        // Bouton retour
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}