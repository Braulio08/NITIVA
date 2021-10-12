package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

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
        cargarBotones();
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

    }
    public void cargarBotones(){
        salir = findViewById(R.id.imageButtonSalir);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemoryGame.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}