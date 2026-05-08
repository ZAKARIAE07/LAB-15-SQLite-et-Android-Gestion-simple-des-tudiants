package projet.fst.ma.app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab15.R;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import projet.fst.ma.app.classes.Etudiant;
import projet.fst.ma.app.service.EtudiantService;

public class MainActivity extends AppCompatActivity {

    private EditText nom;
    private EditText prenom;
    private Button add;

    private EditText id;
    private Button rechercher;
    private Button supprimer;
    private TextView res;

    // Méthode pour vider les champs après l’ajout
    private void clear() {
        nom.setText("");
        prenom.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EtudiantService es = new EtudiantService(this);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        add = findViewById(R.id.bn);

        id = findViewById(R.id.id);
        rechercher = findViewById(R.id.load);
        supprimer = findViewById(R.id.delete);
        res = findViewById(R.id.res);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomTxt = nom.getText().toString().trim();
                String prenomTxt = prenom.getText().toString().trim();

                if (nomTxt.isEmpty() || prenomTxt.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.remplir_champs, Toast.LENGTH_SHORT).show();
                    return;
                }

                es.create(new Etudiant(nomTxt, prenomTxt));
                clear();

                for (Etudiant e : es.findAll()) {
                    Log.d("EtudiantList", e.getId() + ": " + e.getNom() + " " + e.getPrenom());
                }

                Toast.makeText(MainActivity.this, R.string.etudiant_ajoute, Toast.LENGTH_SHORT).show();
            }
        });

        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = id.getText().toString().trim();
                if (txt.isEmpty()) {
                    res.setText("");
                    Toast.makeText(MainActivity.this, R.string.saisir_id, Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    Etudiant e = es.findById(Integer.parseInt(txt));
                    if (e == null) {
                        res.setText("");
                        Toast.makeText(MainActivity.this, R.string.etudiant_introuvable, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    res.setText(getString(R.string.resultat_format, e.getNom(), e.getPrenom()));
                } catch (NumberFormatException nfe) {
                    Toast.makeText(MainActivity.this, R.string.saisir_id, Toast.LENGTH_SHORT).show();
                }
            }
        });

        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = id.getText().toString().trim();
                if (txt.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.saisir_id, Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    Etudiant e = es.findById(Integer.parseInt(txt));
                    if (e == null) {
                        Toast.makeText(MainActivity.this, R.string.aucun_etudiant_supprimer, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    es.delete(e);
                    res.setText("");
                    Toast.makeText(MainActivity.this, R.string.etudiant_supprime, Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException nfe) {
                    Toast.makeText(MainActivity.this, R.string.saisir_id, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
