package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    TextView txtLevel, txtNumberShow;
    EditText etAnswer;
    Button btnRevisar;
    ImageButton salir, reiniciar;
    int nivelActual = 1;
    String rNumber;
    Random random = new Random();
    final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_memory_game);
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
        }, 5000);

        btnRevisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rNumber.equals(etAnswer.getText().toString())){

                    txtNumberShow.setVisibility(View.VISIBLE);
                    etAnswer.setVisibility(View.GONE);
                    btnRevisar.setVisibility(View.GONE);

                    etAnswer.setText("");

                    nivelActual++;

                    toast.show();
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
                    }, 5000);
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