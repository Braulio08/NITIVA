package com.brafa.nitiva;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton memory, tictactoe, numberMemory, hangman;
    Button exit;
    Button sound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Activar un botón
        memory = findViewById(R.id.imbMemory); //Buscar el botón en la interfaz
        numberMemory = findViewById(R.id.imbNumberMemory);
        hangman = findViewById(R.id.imbHangman);
        tictactoe = findViewById(R.id.imbTicTacToe);
        hangman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HangmanGame.class);
                startActivity(intent);
                finish();
            }
        });
        numberMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NumbersMemoryGame.class);
                startActivity(intent);
                finish();
            }
        });
        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MemoryGame.class);
                startActivity(intent);
                finish();
            }
        });
        tictactoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TicTacToe.class);
                startActivity(intent);
                finish();
            }
        });
        exit = findViewById(R.id.btnExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogMessage();
                //finish();
            }
        });

        sound = findViewById(R.id.btnSound);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (true){

                    Toast.makeText(getApplicationContext(), "Has desactivado el sonido.", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(getApplicationContext(), "Has activado el sonido.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void showDialogMessage() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Salir").setMessage("¿Está seguro(a) de que desea salir?")
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        })
        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).show();
        alertDialog.create();
    }
}