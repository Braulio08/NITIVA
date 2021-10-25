package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView result;
    Button playAgain;
    Button exit;
    int puntajeMemory=0;
    int nivelNumbersMemory=0;
    int puntajeHangman=0;
    String sw = "";
    String userResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = findViewById(R.id.textViewScoreResult);
        playAgain = findViewById(R.id.buttonPlayAgain);
        exit = findViewById(R.id.buttonExit);
        Intent intent = getIntent();


        puntajeMemory = intent.getIntExtra("puntajeMemory", 0);
        nivelNumbersMemory = intent.getIntExtra("nivelNumber", 0);
        puntajeHangman = intent.getIntExtra("puntajeHangman", 0);

        if(puntajeMemory>0){
            sw = "Memory";
            userResult = "Puntaje: "+ puntajeMemory;
            result.setText(userResult);
        }
        if(nivelNumbersMemory>0){
            sw = "NumberMemory";
            userResult = "Nivel: "+ nivelNumbersMemory;
            result.setText(userResult);
        }
        if(puntajeHangman>0){
            sw = "Hangman";
            userResult = "Puntaje: "+ puntajeHangman;
            result.setText(userResult);
        }



        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (sw){
                    case "Memory":
                        Intent intent = new Intent(Result.this, MemoryGame.class);
                        startActivity(intent);
                        finish();
                        break;
                    case "NumberMemory":
                        Intent intent2 = new Intent(Result.this, NumbersMemoryGame.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case "Hangman":
                        Intent intent3 = new Intent(Result.this, HangmanGame.class);
                        startActivity(intent3);
                        finish();
                        break;
                }
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Result.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}