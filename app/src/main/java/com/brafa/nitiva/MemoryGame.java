package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MemoryGame extends AppCompatActivity {

    ImageButton imb00, imb01, imb02, imb03, imb04, imb05, imb06, imb07, imb08, imb09, imb10, imb11;
    ImageButton[] matriz = new ImageButton[12];
    ImageButton salir, reiniciar;
    TextView puntajePantalla, textViewTemp;
    int puntaje = 0;
    int aciertos = 0;
    int[] imagenes;
    int[] imagenes1;
    int[] imagenes2;
    int[] imagenes3;
    int[] imagenes4;
    int fondo;
    Random random = new Random();
    int numberRandom;
    ArrayList<Integer>arrayCaos;
    ImageButton primero, sound;
    int number1, number2;
    boolean bandera = false;
    final Handler handler = new Handler();
    MediaPlayer mediaPlayer;
    boolean status = false;

    CountDownTimer countDownTimer;
    private static final long TOTAL_TIME = 6000;
    Boolean timerContinue;
    long timeLeft = TOTAL_TIME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);
        startTimer();
        iniciar();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mediaPlayer = MediaPlayer.create(MemoryGame.this, R.raw.memory);
        //mediaPlayer.start();

        sound = findViewById(R.id.imageButtonSound);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!status){
                    mediaPlayer.start();
                    sound.setImageResource(R.drawable.volume);
                    status = true;
                } else {
                    mediaPlayer.stop();
                    sound.setImageResource(R.drawable.mute);
                    mediaPlayer = MediaPlayer.create(MemoryGame.this, R.raw.hangman);
                    status = false;
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*if (!status){
            mediaPlayer.setVolume(0, 0);
            status = true;
        } else {
            mediaPlayer.setVolume(1, 1);
            status = false;
        }*/
        mediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
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
        textViewTemp = findViewById(R.id.textViewTemp);
        puntaje=0;
        aciertos=0;
        puntajePantalla.setText(""+puntaje);
    }
    public void cargarImagenes(){
        imagenes = new int[]{
                R.drawable.leaf,
                R.drawable.parrot,
                R.drawable.pawprint,
                R.drawable.pig,
                R.drawable.rose,
                R.drawable.flower,
        };
        imagenes1 = new int[]{
                R.drawable.bee,
                R.drawable.cherry,
                R.drawable.cow,
                R.drawable.flower,
                R.drawable.grapes,
                R.drawable.hen
        };
        imagenes2 = new int[]{
                R.drawable.rose,
                R.drawable.strawberry,
                R.drawable.sunny,
                R.drawable.flower,
                R.drawable.grapes,
                R.drawable.hen
        };
        imagenes3 = new int[]{
                R.drawable.rose,
                R.drawable.cow,
                R.drawable.sunny,
                R.drawable.flower,
                R.drawable.leaf,
                R.drawable.hen
        };
        imagenes4 = new int[]{
                R.drawable.pawprint,
                R.drawable.cow,
                R.drawable.strawberry,
                R.drawable.bee,
                R.drawable.leaf,
                R.drawable.hen
        };
        fondo = R.drawable.fingerprint;
    }
    public void cargarBotones(){
        salir = findViewById(R.id.imageButtonSalir);
        reiniciar = findViewById(R.id.imageButtonReiniciar);
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseTimer();
                resetTimer();
                startTimer();
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
            result.add(i % longitud);
        }
        Collections.shuffle(result);
        return result;
    }


    public void resetTimer()
    {
        timeLeft = TOTAL_TIME;
        updateCountDownText();
    }
    public void updateCountDownText()
    {
        int second = (int)(timeLeft / 1000) % 60;
        textViewTemp.setText(""+second);
    }
    public void pauseTimer()
    {
        countDownTimer.cancel();
        timerContinue = false;
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerContinue = false;
                pauseTimer();
                textViewTemp.setVisibility(View.INVISIBLE);
                for (int i = 0; i < matriz.length; i++) {
                    matriz[i].setScaleType(ImageView.ScaleType.FIT_CENTER);
                    matriz[i].setImageResource(fondo);
                }

            }
        }.start();
        timerContinue = true;
    }

    public void iniciar(){
        cargarMatriz();
        cargarBotones();
        cargarTexto();
        cargarImagenes();
        textViewTemp.setVisibility(View.VISIBLE);
        arrayCaos = barajar(imagenes.length);
        numberRandom = random.nextInt(5);
        for (int i = 0; i < matriz.length; i++) {
            matriz[i].setScaleType(ImageView.ScaleType.FIT_CENTER);
            switch (numberRandom){
                case 0:
                    matriz[i].setImageResource(imagenes[arrayCaos.get(i)]);
                    break;
                case 1:
                    matriz[i].setImageResource(imagenes1[arrayCaos.get(i)]);
                    break;
                case 2:
                    matriz[i].setImageResource(imagenes2[arrayCaos.get(i)]);
                    break;
                case 3:
                    matriz[i].setImageResource(imagenes2[arrayCaos.get(i)]);
                    break;
                case 4:
                    matriz[i].setImageResource(imagenes2[arrayCaos.get(i)]);
                    break;
            }
        }

        /*handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < matriz.length; i++) {
                    matriz[i].setScaleType(ImageView.ScaleType.FIT_CENTER);
                    matriz[i].setImageResource(fondo);
                }
            }
        }, 5000);*/


        //Aquí va



        for (int i = 0; i < matriz.length; i++) {
            final int j = i;
            matriz[i].setEnabled(true);
            matriz[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!bandera){
                        comprobar(j, matriz[j]);
                    }
                }
            });
        }
    }
    private void comprobar(int i, final ImageButton imb){
        if(primero == null){
            primero = imb;
            primero.setScaleType(ImageView.ScaleType.FIT_CENTER);
            switch (numberRandom){
                case 0:
                    primero.setImageResource(imagenes[arrayCaos.get(i)]);
                    break;
                case 1:
                    primero.setImageResource(imagenes1[arrayCaos.get(i)]);
                    break;
                case 2:
                    primero.setImageResource(imagenes2[arrayCaos.get(i)]);
                    break;
                case 3:
                    primero.setImageResource(imagenes2[arrayCaos.get(i)]);
                    break;
                case 4:
                    primero.setImageResource(imagenes2[arrayCaos.get(i)]);
                    break;
            }
            primero.setEnabled(false);
            number1=arrayCaos.get(i);
        }else{
            bandera = true;
            imb.setScaleType(ImageView.ScaleType.FIT_CENTER);
            switch (numberRandom){
                case 0:
                    imb.setImageResource(imagenes[arrayCaos.get(i)]);
                    break;
                case 1:
                    imb.setImageResource(imagenes1[arrayCaos.get(i)]);
                    break;
                case 2:
                    imb.setImageResource(imagenes2[arrayCaos.get(i)]);
                    break;
                case 3:
                    imb.setImageResource(imagenes2[arrayCaos.get(i)]);
                    break;
                case 4:
                    imb.setImageResource(imagenes2[arrayCaos.get(i)]);
                    break;
            }
            imb.setEnabled(false);
            number2=arrayCaos.get(i);
            if (number1==number2){
                primero = null;
                bandera = false;
                aciertos++;
                puntaje+=2;
                puntajePantalla.setText(""+puntaje);

                switch (numberRandom){
                    case 0:
                        if (aciertos==imagenes.length){
                            Intent intent = new Intent(MemoryGame.this, Result.class);
                            intent.putExtra("puntajeMemory", puntaje);
                            startActivity(intent);
                            finish();
                        }
                        break;
                    case 1:
                        if (aciertos==imagenes1.length){
                            Intent intent = new Intent(MemoryGame.this, Result.class);
                            intent.putExtra("puntajeMemory", puntaje);
                            startActivity(intent);
                            finish();
                        }
                        break;
                    case 2:
                        if (aciertos==imagenes2.length){
                            Intent intent = new Intent(MemoryGame.this, Result.class);
                            intent.putExtra("puntajeMemory", puntaje);
                            startActivity(intent);
                            finish();
                        }
                        break;
                    case 3:
                        if (aciertos==imagenes3.length){
                            Intent intent = new Intent(MemoryGame.this, Result.class);
                            intent.putExtra("puntajeMemory", puntaje);
                            startActivity(intent);
                            finish();
                        }
                        break;
                    case 4:
                        if (aciertos==imagenes4.length){
                            Intent intent = new Intent(MemoryGame.this, Result.class);
                            intent.putExtra("puntajeMemory", puntaje);
                            startActivity(intent);
                            finish();
                        }
                        break;
                }



            }else{
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        primero.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        primero.setImageResource(fondo);
                        primero.setEnabled(true);
                        imb.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        imb.setImageResource(fondo);
                        imb.setEnabled(true);
                        bandera=false;
                        primero=null;
                        if(!(puntaje<=1)){
                            puntaje--;
                        }
                        puntajePantalla.setText(""+puntaje);
                    }
                }, 1000);
            }
        }
    }
}