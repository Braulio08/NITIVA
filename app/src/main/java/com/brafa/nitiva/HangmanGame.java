package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView txtOportunidades;
    int puntaje=0;
    private int numCorr;
    String oportunidades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman_game);

        palabraLayout = findViewById(R.id.LinearCharts);

        txtOportunidades = findViewById(R.id.textViewOportunidades);
        oportunidades = "❤❤❤❤❤";
        txtOportunidades.setText(oportunidades);
        puntaje = 0;
        play();
    }
    private void play(){
        palabras = getResources().getStringArray(R.array.palabras);
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

        gridView = findViewById(R.id.screenLetters);
        wordView = findViewById(R.id.screenWord);


        adapter = new LetterAdapter(this);

        gridView.setAdapter(adapter);


        wordAdapter = new WordAdapter( word, this);

        wordView.setAdapter(wordAdapter);

        numCorr =0;
        oportunidades = "❤❤❤❤❤";

    }
    public void letterPressed(View view){
        String letter = ((TextView)view).getText().toString();
        char letterChar = letter.charAt(0);
        view.setVisibility(View.INVISIBLE);
        boolean correct = false;

        for (int i = 0; i < actual.length(); i++) {
            if(actual.charAt(i)==letterChar){
                correct = true;
                numCorr++;
                puntaje+=10;
                final int numVisibleChildren = wordView.getChildCount();
                final int firstVisiblePosition = wordView.getFirstVisiblePosition();

                for ( int j = 0; j < numVisibleChildren; j++ ) {
                    int positionOfView = firstVisiblePosition + j;

                    if (positionOfView == i) {
                        View element = wordView.getChildAt(j);
                        element.setBackgroundResource(R.drawable.charshape);
                    }
                }
            }
        }
        if(correct){
            if(numCorr== actual.length()){
                word.clear();
                play();

            }
        }else {
            if(!oportunidades.isEmpty()){
                oportunidades = oportunidades.substring(0, oportunidades.length()-1);
                txtOportunidades.setText(oportunidades);
            }else {
                Intent intent = new Intent(HangmanGame.this, Result.class);
                intent.putExtra("puntajeHangman", puntaje);
                startActivity(intent);
                finish();
            }
        }
    }

}