package com.example.generatorhasel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String haslo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.stanowisko);
        Button generuj = findViewById(R.id.generuj);
        Button zatwierdz = findViewById(R.id.zatwierdz);
        EditText imie = findViewById(R.id.imie);
        EditText nazwisko = findViewById(R.id.nazwisko);
        EditText znaki = findViewById(R.id.znaki);
        CheckBox litery = findViewById(R.id.litery);
        CheckBox specjalne = findViewById(R.id.specjalne);
        CheckBox cyfry = findViewById(R.id.cyfry);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.zawody,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        generuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean literyCheck = litery.isChecked();
                boolean specjalneCheck = specjalne.isChecked();
                boolean cyfryCheck = cyfry.isChecked();
                haslo = generatePass(Integer.parseInt(znaki.getText().toString()), literyCheck, specjalneCheck, cyfryCheck);
                Toast.makeText(MainActivity.this, haslo, Toast.LENGTH_LONG).show();

            }
        });
        zatwierdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imieP = imie.getText().toString();
                String nazwiskoP = nazwisko.getText().toString();
                String posada = spinner.getSelectedItem().toString();

                String dane = "Dane pracownika: "+imieP+" "+nazwiskoP+" Stanowisko: "+posada+" Hasło: "+haslo;
                Toast.makeText(MainActivity.this, dane, Toast.LENGTH_LONG).show();
            }
        });
    }
    private String generatePass(int dlugosc, boolean l, boolean s, boolean c){
        String literyRandom = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        String cyfryRandom = "1234567890";
        String specjalneRandom = "!@#$%^&*()?><";
        Random r = new Random();
        StringBuilder sb = new StringBuilder(dlugosc);
            if(l==true && s==true && c==true){
                String a = literyRandom+cyfryRandom+specjalneRandom;
                for(int i=0;i<dlugosc;i++){
                    sb.append(a.charAt(r.nextInt(a.length())));
                }
            } else if (l==true && s==true && c==false) {
                String a = literyRandom+specjalneRandom;
                for(int i=0;i<dlugosc;i++){
                    sb.append(a.charAt(r.nextInt(a.length())));
                }
            } else if (l==true && s==false && c==true){
                String a = literyRandom+cyfryRandom;
                for(int i=0;i<dlugosc;i++){
                    sb.append(a.charAt(r.nextInt(a.length())));
                }
            } else if(l==false && s==true && c==true){
                String a = cyfryRandom+specjalneRandom;
                for(int i=0;i<dlugosc;i++){
                    sb.append(a.charAt(r.nextInt(a.length())));
                }
            } else if(l==true && s==false && c==false){
                for(int i=0;i<dlugosc;i++){
                    sb.append(literyRandom.charAt(r.nextInt(literyRandom.length())));
                }
            } else if (l==false && s==true && c==false){
                for(int i=0;i<dlugosc;i++){
                    sb.append(specjalneRandom.charAt(r.nextInt(specjalneRandom.length())));
                }
            } else if (l==false && s==false && c==true){
                for(int i=0;i<dlugosc;i++){
                    sb.append(cyfryRandom.charAt(r.nextInt(cyfryRandom.length())));
                }
            }
        return sb.toString();

    }
}
