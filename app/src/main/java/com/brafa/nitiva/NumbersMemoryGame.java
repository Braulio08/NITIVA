package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class NumbersMemoryGame extends AppCompatActivity {

    TextView txtLevel, txtNumberShow;
    EditText etAnswer;
    Button btnRevisar;
    int nivelActual = 1;
    String rNumber;
    Random random = new Random();
    final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_memory_game);
        cargarJuego();
    }
    public void cargarJuego() {
        txtLevel = findViewById(R.id.textViewLevel);
        txtNumberShow = findViewById(R.id.textViewNumberShow);
        etAnswer = findViewById(R.id.editTextNumber);
        btnRevisar = findViewById(R.id.btnRevisar);

        txtNumberShow.setVisibility(View.VISIBLE);
        etAnswer.setVisibility(View.GONE);
        btnRevisar.setVisibility(View.GONE);

        txtLevel.setText("Nivel: "+ nivelActual);
        rNumber = generarNumeroRandom(nivelActual);
        txtNumberShow.setText(rNumber);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                txtNumberShow.setVisibility(View.GONE);
                etAnswer.setVisibility(View.VISIBLE);
                btnRevisar.setVisibility(View.VISIBLE);
                etAnswer.requestFocus();
            }
        }, 3000);

        btnRevisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rNumber.equals(etAnswer.getText().toString())){

                    txtNumberShow.setVisibility(View.VISIBLE);
                    etAnswer.setVisibility(View.GONE);
                    btnRevisar.setVisibility(View.GONE);

                    etAnswer.setText("");

                    nivelActual++;
                    txtLevel.setText("Nivel: "+ nivelActual);

                    rNumber = generarNumeroRandom(nivelActual);
                    txtNumberShow.setText(rNumber);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            txtNumberShow.setVisibility(View.GONE);
                            etAnswer.setVisibility(View.VISIBLE);
                            btnRevisar.setVisibility(View.VISIBLE);
                            etAnswer.requestFocus();
                        }
                    }, 3000);
                } else {
                    Intent intent = new Intent(NumbersMemoryGame.this, Result.class);
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