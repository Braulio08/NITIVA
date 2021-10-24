package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class HangmanGame extends AppCompatActivity {

    private String [] palabras;
    Random random = new Random();
    private String actual;
    private TextView [] charView;
    private LinearLayout palabraLayout;
    private LetterAdapter adapter;
    private WordAdapter wordAdapter;
    private GridView gridView, wordView;
    ArrayList<String> word = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman_game);
        palabras = getResources().getStringArray(R.array.palabras);
        palabraLayout = findViewById(R.id.LinearCharts);
        gridView = findViewById(R.id.screenLetters);
        wordView = findViewById(R.id.screenWord);
        play();
    }
    private void play(){

        String nPalabra = palabras[random.nextInt(palabras.length)];
        while (nPalabra.equals(actual))nPalabra = palabras[random.nextInt(palabras.length)];

        actual = nPalabra;

        for (int i = 0; i < actual.length(); i++) {
            word.add(""+actual.charAt(i));
        }


        /*String nPalabra = palabras[random.nextInt(palabras.length)];
        while (nPalabra.equals(actual))nPalabra = palabras[random.nextInt(palabras.length)];

        actual = nPalabra;

        charView = new TextView[actual.length()];

        for (int i = 0; i < actual.length(); i++) {
            charView[i] = new TextView(this);
            charView[i].setText(""+actual.charAt(i));
            charView[i].setLayoutParams(new ViewGroup.LayoutParams(130, ViewGroup.LayoutParams.MATCH_PARENT));
            charView[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            charView[i].setTextColor(Color.BLACK);
            charView[i].setTextSize(75);
            charView[i].setBackgroundResource(R.drawable.charshape);
            palabraLayout.addView(charView[i]);
        }*/




        adapter = new LetterAdapter(this);
        gridView.setAdapter(adapter);

        wordAdapter = new WordAdapter( word, this);
        wordView.setAdapter(wordAdapter);

    }

}