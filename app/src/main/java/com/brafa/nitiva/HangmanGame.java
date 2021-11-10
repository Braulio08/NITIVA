package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class HangmanGame extends AppCompatActivity {

    private String [] palabras;
    Random random = new Random();
    private String actual;
    ScrollView scrollView;
    ImageButton salir, reiniciar, btnAsk;
    private WordAdapter wordAdapter;
    FrameLayout frameLayout;
    private GridView wordView;
    ArrayList<String> word = new ArrayList<>();
    TextView txtOportunidades;
    int puntaje=0;
    GridLayout gridLetters;
    private int numCorr;
    String oportunidades;
    final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman_game);
        scrollView = findViewById(R.id.scrollLetters);
        txtOportunidades = findViewById(R.id.textViewOportunidades);
        oportunidades = "❤❤❤❤❤";
        txtOportunidades.setText(oportunidades);
        puntaje = 0;
        gridLetters = findViewById(R.id.gridLetters);

        play();
        cargarBotones();
    }
    private void activarBotones(GridLayout view){
        for (int i = 0; i < view.getChildCount(); i++) {
            Button btn = (Button) view.getChildAt(i);
            btn.setVisibility(View.VISIBLE);
        }
    }
    public void cargarBotones(){





        frameLayout = findViewById(R.id.frame);
        btnAsk = findViewById(R.id.btn16);
        btnAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                frameLayout.setVisibility(View.VISIBLE);



                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frameLayout.setVisibility(View.GONE);
                    }
                }, 10000);

            }
        });






        salir = findViewById(R.id.imageButtonSalir);
        reiniciar = findViewById(R.id.imageButtonReiniciar);
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                word.clear();
                puntaje = 0;
                play();
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HangmanGame.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void play(){
        activarBotones(gridLetters);
        palabras = getResources().getStringArray(R.array.palabras);
        String nPalabra = palabras[random.nextInt(palabras.length)];
        while (nPalabra.equals(actual))nPalabra = palabras[random.nextInt(palabras.length)];

        actual = nPalabra;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PistaFragment pistaFragment = new PistaFragment();
        Bundle bundle = new Bundle();
        String palabra = actual;
        bundle.putString("palabra", palabra);
        pistaFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.frame,pistaFragment);
        fragmentTransaction.commit();

        for (int i = 0; i < actual.length(); i++) {
            word.add(""+actual.charAt(i));
        }


        wordView = findViewById(R.id.screenWord);




        wordAdapter = new WordAdapter( word, this);

        wordView.setAdapter(wordAdapter);

        numCorr =0;
        oportunidades = "❤❤❤❤❤";
        txtOportunidades.setText(oportunidades);

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