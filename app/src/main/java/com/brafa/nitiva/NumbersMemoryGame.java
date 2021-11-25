package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class NumbersMemoryGame extends AppCompatActivity {

    TextView txtLevel, txtNumberShow, textViewTemp;
    EditText etAnswer;
    Button btnRevisar;
    ImageButton salir, reiniciar;
    int nivelActual = 1;
    String rNumber;
    Random random = new Random();
    final Handler handler = new Handler();
    CountDownTimer countDownTimer;
    private static final long TOTAL_TIME = 6000;
    Boolean timerContinue;
    long timeLeft = TOTAL_TIME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_memory_game);
        startTimer();
        cargarJuego();
        cargarBotones();
    }
    public void cargarBotones(){
        salir = findViewById(R.id.imageButtonSalir);
        reiniciar = findViewById(R.id.imageButtonReiniciar);
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nivelActual = 1;
                pauseTimer();
                resetTimer();
                startTimer();
                cargarJuego();
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NumbersMemoryGame.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void startTimer()
    {
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

                txtNumberShow.setVisibility(View.GONE);
                etAnswer.setVisibility(View.VISIBLE);
                btnRevisar.setVisibility(View.VISIBLE);
                etAnswer.requestFocus();
            }
        }.start();
        timerContinue = true;
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

    public void cargarJuego() {

        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast1));
        TextView textView = view.findViewById(R.id.textView8);
        textView.setText("¡Correcto!");
        final Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);

        LayoutInflater layoutInflater2 = getLayoutInflater();
        View view2 = layoutInflater2.inflate(R.layout.custom2_toast, (ViewGroup) findViewById(R.id.toast2));
        TextView textView2 = view2.findViewById(R.id.textView8);
        textView2.setText("¡Ups!");
        final Toast toast2 = new Toast(getApplicationContext());
        toast2.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast2.setDuration(Toast.LENGTH_SHORT);
        toast2.setView(view2);





        txtLevel = findViewById(R.id.textViewLevel);
        txtNumberShow = findViewById(R.id.textViewNumberShow);
        etAnswer = findViewById(R.id.editTextNumber);
        btnRevisar = findViewById(R.id.btnRevisar);
        textViewTemp = findViewById(R.id.textViewTemp);


        textViewTemp.setVisibility(View.VISIBLE);
        txtNumberShow.setVisibility(View.VISIBLE);
        etAnswer.setVisibility(View.GONE);
        btnRevisar.setVisibility(View.GONE);


        txtLevel.setText("Nivel: "+ nivelActual);
        rNumber = generarNumeroRandom(nivelActual);
        txtNumberShow.setText(rNumber);


       /* handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewTemp.setVisibility(View.INVISIBLE);

                txtNumberShow.setVisibility(View.GONE);
                etAnswer.setVisibility(View.VISIBLE);
                btnRevisar.setVisibility(View.VISIBLE);
                etAnswer.requestFocus();
            }
        }, 6000);*/

        btnRevisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rNumber.equals(etAnswer.getText().toString())){

                    resetTimer();
                    startTimer();
                    textViewTemp.setVisibility(View.VISIBLE);
                    txtNumberShow.setVisibility(View.VISIBLE);

                    etAnswer.setVisibility(View.GONE);
                    btnRevisar.setVisibility(View.GONE);

                    etAnswer.setText("");

                    nivelActual++;

                    toast.show();
                    txtLevel.setText("Nivel: "+ nivelActual);

                    rNumber = generarNumeroRandom(nivelActual);
                    txtNumberShow.setText(rNumber);

                   /* handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textViewTemp.setVisibility(View.INVISIBLE);

                            txtNumberShow.setVisibility(View.GONE);
                            etAnswer.setVisibility(View.VISIBLE);
                            btnRevisar.setVisibility(View.VISIBLE);
                            etAnswer.requestFocus();
                        }
                    }, 6000);*/
                } else {
                    Intent intent = new Intent(NumbersMemoryGame.this, Result.class);
                    toast2.show();
                    intent.putExtra("nivelNumber", nivelActual);
                    startActivity(intent);
                    finish();
                }





            }
        });
    }

    private String generarNumeroRandom(int digits){
        String result = "";
        for (int i = 0; i <digits ; i++) {
            result = result + "" + random.nextInt(10);
        }
        return result;
    }
}