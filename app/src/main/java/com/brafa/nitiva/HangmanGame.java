package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HangmanGame extends AppCompatActivity {

    private String [] palabras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman_game);
    }
}