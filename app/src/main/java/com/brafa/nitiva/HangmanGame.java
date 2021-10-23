package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class HangmanGame extends AppCompatActivity {

    private String [] palabras;
    Random random = new Random();
    private String actual;
    private TextView [] charView;
    LinearLayout palabraLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman_game);
        palabras = getResources().getStringArray(R.array.palabras);
        palabraLayout = findViewById(R.id.LinearCharts);
    }
    private void play(){
        String nPalabra = palabras[random.nextInt(palabras.length)];
        while (nPalabra.equals(actual))nPalabra = palabras[random.nextInt(palabras.length)];

        actual = nPalabra;

        charView = new TextView[actual.length()];

        for (int i = 0; i < actual.length(); i++) {
            charView[i] = new TextView(this);
            charView[i].setText(actual.charAt(i));
            charView[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            charView[i].setGravity(Gravity.CENTER);
            charView[i].setTextColor(Color.TRANSPARENT);
            charView[i].setBackgroundResource(R.drawable.buttonshape);
            palabraLayout.addView(charView[i]);
        }

    }

}