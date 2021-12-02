package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Instruction extends AppCompatActivity {


    ImageButton imageButtonAtras, imageButtonSiguiente;
    TextView txtParrafo;
    private String game="";
    private String instruccion="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        imageButtonAtras = findViewById(R.id.imbAtras);
        imageButtonSiguiente = findViewById(R.id.imbSiguiente);
        txtParrafo = findViewById(R.id.textViewParrafo);

        Intent intent = getIntent();
        game = intent.getStringExtra("game");
        instruccion = intent.getStringExtra("instruccion");
        txtParrafo.setText(instruccion);

        imageButtonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (game){
                    case "Hangman":
                        Intent intent1 = new Intent(Instruction.this, HangmanGame.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case "NumberMemory":
                        Intent intent2 = new Intent(Instruction.this, NumbersMemoryGame.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case "Memory":
                        Intent intent3 = new Intent(Instruction.this, MemoryGame.class);
                        startActivity(intent3);
                        finish();
                        break;
                    case "TicTacToe":
                        Intent intent4 = new Intent(Instruction.this, ChooseTicTac.class);
                        startActivity(intent4);
                        finish();
                        break;
                }
            }
        });

        imageButtonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Instruction.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}