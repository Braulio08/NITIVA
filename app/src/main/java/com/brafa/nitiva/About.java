package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentContainerView;

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
    FragmentContainerView fragmentContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        linearLayout = findViewById(R.id.linear_layout);
        btnCerrar = findViewById(R.id.button);
        cvAcercaDe = findViewById(R.id.cvAcercaDe);
        cvBotones = findViewById(R.id.cvBotones);
        cvAlzheimer = findViewById(R.id.cvAlzheimer);

        fragmentContainerView = findViewById(R.id.fragmentContainerView);

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fragmentContainerView.getVisibility()==View.VISIBLE)
                {
                    fragmentContainerView.setVisibility(View.GONE);
                    cvAcercaDe.setVisibility(View.VISIBLE);
                    cvAlzheimer.setVisibility(View.VISIBLE);
                    cvBotones.setVisibility(View.VISIBLE);
                }
                else
                {
                    Intent intent = new Intent(About.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        cvAlzheimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentContainerView.setVisibility(View.VISIBLE);
                cvAcercaDe.setVisibility(View.GONE);
                cvAlzheimer.setVisibility(View.GONE);
                cvBotones.setVisibility(View.GONE);
            }
        });
        cvBotones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentContainerView.setVisibility(View.VISIBLE);
                cvAcercaDe.setVisibility(View.GONE);
                cvAlzheimer.setVisibility(View.GONE);
                cvBotones.setVisibility(View.GONE);
            }
        });
        cvAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentContainerView.setVisibility(View.VISIBLE);
                cvAcercaDe.setVisibility(View.GONE);
                cvAlzheimer.setVisibility(View.GONE);
                cvBotones.setVisibility(View.GONE);
            }
        });
    }
}