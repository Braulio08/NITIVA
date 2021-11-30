package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

public class About extends AppCompatActivity {

    Button btnCerrar, cvBotones, cvAlzheimer, cvAcercaDe;
    ConstraintLayout linearLayout;
    FragmentContainerView fragmentContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        linearLayout = findViewById(R.id.linear_layout);
        btnCerrar = findViewById(R.id.button);
        cvAcercaDe = findViewById(R.id.button4);
        cvBotones = findViewById(R.id.button3);
        cvAlzheimer = findViewById(R.id.button2);

        fragmentContainerView = findViewById(R.id.fragmentContainerView);

        AcercaDeFragment acercaDeFragment = new AcercaDeFragment();
        AlzheimerFragment alzheimerFragment = new AlzheimerFragment();
        BotonesFragment botonesFragment = new BotonesFragment();
        BlankFragment blankFragment = new BlankFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainerView, blankFragment);
        fragmentTransaction.commit();




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
                fragmentContainerView.setVisibility(View.VISIBLE);
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(R.id.fragmentContainerView, alzheimerFragment);
                fragmentTransaction1.commit();

            }
        });
        cvBotones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentContainerView.setVisibility(View.VISIBLE);
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                fragmentTransaction2.replace(R.id.fragmentContainerView, botonesFragment);
                fragmentTransaction2.commit();

            }
        });
        cvAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentContainerView.setVisibility(View.VISIBLE);
                FragmentManager fragmentManager3 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                fragmentTransaction3.replace(R.id.fragmentContainerView, acercaDeFragment);
                fragmentTransaction3.commit();

            }
        });
    }
}