package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

public class About extends AppCompatActivity {

    Button btnCerrar;
    CardView cvBotones, cvAlzheimer, cvAcercaDe;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        linearLayout = findViewById(R.id.linear_layout);
        btnCerrar = findViewById(R.id.button);
        cvAcercaDe = findViewById(R.id.cvAcercaDe);
        cvBotones = findViewById(R.id.cvBotones);
        cvAlzheimer = findViewById(R.id.cvAlzheimer);

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(About.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cvAlzheimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(linearLayout,"En mantenimiento",Snackbar.LENGTH_SHORT).show();
            }
        });
        cvBotones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(linearLayout,"En mantenimiento",Snackbar.LENGTH_SHORT).show();
            }
        });
        cvAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(linearLayout,"En mantenimiento",Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}