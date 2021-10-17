package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MemoryGame extends AppCompatActivity {

    ImageButton imb00, imb01, imb02, imb03, imb04, imb05, imb06, imb07, imb08, imb09, imb10, imb11;
    ImageButton[] matriz = new ImageButton[12];
    ImageButton salir, reiniciar;
    TextView puntajePantalla;
    int puntaje;
    int aciertos;
    int[] imagenes;
    int fondo;
    ArrayList<Integer>arrayCaos;
    ImageButton primero;
    int number1, number2;
    boolean bandera = false;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);
        iniciar();
    }
    private void cargarMatriz(){
        imb00 = findViewById(R.id.btn00);
        imb01 = findViewById(R.id.btn01);
        imb02 = findViewById(R.id.btn02);
        imb03 = findViewById(R.id.btn03);
        imb04 = findViewById(R.id.btn04);
        imb05 = findViewById(R.id.btn05);
        imb06 = findViewById(R.id.btn06);
        imb07 = findViewById(R.id.btn07);
        imb08 = findViewById(R.id.btn08);
        imb09 = findViewById(R.id.btn09);
        imb10 = findViewById(R.id.btn10);
        imb11 = findViewById(R.id.btn11);

        matriz[0] = imb00;
        matriz[1] = imb01;
        matriz[2] = imb02;
        matriz[3] = imb03;
        matriz[4] = imb04;
        matriz[5] = imb05;
        matriz[6] = imb06;
        matriz[7] = imb07;
        matriz[8] = imb08;
        matriz[9] = imb09;
        matriz[10] = imb10;
        matriz[11] = imb11;

    }
    public void cargarTexto(){
        puntajePantalla = findViewById(R.id.textViewPuntaje);
        puntaje=0;
        aciertos=0;
        puntajePantalla.setText(puntaje);
    }
    public void cargarImagenes(){
        imagenes = new int[]{
                R.drawable.bee,
                R.drawable.cherry,
                R.drawable.cow,
                R.drawable.flower,
                R.drawable.grapes,
                R.drawable.hen,
                R.drawable.leaf,
                R.drawable.parrot,
                R.drawable.pawprint,
                R.drawable.pig,
                R.drawable.rose,
                R.drawable.strawberry,
                R.drawable.sunny
        };
        fondo = R.drawable.background;
    }
    public void cargarBotones(){
        salir = findViewById(R.id.imageButtonSalir);
        reiniciar = findViewById(R.id.imageButtonReiniciar);
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciar();
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemoryGame.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private ArrayList<Integer> barajar(int longitud){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < longitud*2; i++) {
            result.add(i%longitud);
        }
        Collections.shuffle(result);
        return result;
    }
    public void iniciar(){
        cargarMatriz();
        cargarBotones();
        cargarTexto();
        cargarImagenes();
        arrayCaos = barajar(imagenes.length);
        for (int i = 0; i < matriz.length; i++) {
            matriz[i].setScaleType(ImageView.ScaleType.FIT_CENTER);
            matriz[i].setImageResource(imagenes[arrayCaos.get(i)]);
        }
    }
}