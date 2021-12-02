package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseTicTac extends AppCompatActivity {

    Button btnSolo, btnPareja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_tic_tac);

        btnSolo = findViewById(R.id.button5);
        btnPareja = findViewById(R.id.button6);

        btnSolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseTicTac.this, TicTacToe.class);
                startActivity(intent);
                finish();
            }
        });
        btnPareja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseTicTac.this, TicTacToe.class);
                startActivity(intent);
                finish();
            }
        });
    }
}