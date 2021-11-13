package com.brafa.nitiva;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton memory, tictactoe, numberMemory, hangman;
    Button exit, sound;
    Switch switchDayNight;
    TextView textView;
    Animation animationButton, animationText;
    MediaPlayer mediaPlayer;
    private String game="";
    private String instruccion="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animationButton = AnimationUtils.loadAnimation(this,R.anim.button_animation);
        animationText = AnimationUtils.loadAnimation(this,R.anim.text_animation);
        //Activar un botón
        memory = findViewById(R.id.imbMemory); //Buscar el botón en la interfaz
        numberMemory = findViewById(R.id.imbNumberMemory);
        hangman = findViewById(R.id.imbHangman);
        tictactoe = findViewById(R.id.imbTicTacToe);
        textView = findViewById(R.id.txtTitle);

        textView.setAnimation(animationText);
        memory.setAnimation(animationButton);
        numberMemory.setAnimation(animationButton);
        hangman.setAnimation(animationButton);
        tictactoe.setAnimation(animationButton);

        hangman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Instruction.class);
                game="Hangman";
                instruccion="¡Trata de adivinar la palabra presionando las letras de la pantalla!\n" +
                        "Puedes ver una pista presionando el botón de '?'";
                intent.putExtra("instruccion", instruccion);
                intent.putExtra("game", game);
                startActivity(intent);
                finish();
            }
        });
        numberMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Instruction.class);
                game="NumberMemory";
                instruccion="¡Memoriza el número que se muestra antes que desaparezca y escríbelo para avanzar de nivel!";
                intent.putExtra("instruccion", instruccion);
                intent.putExtra("game", game);
                startActivity(intent);
                finish();
            }
        });
        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Instruction.class);
                game="Memory";
                instruccion="¡Pon atención a la posición de las imágenes en la pantalla antes que desaparezcan!\n" +
                        "Toca las tarjetas para revelar la imagen y buscar en donde se encontraba la pareja";
                intent.putExtra("instruccion", instruccion);
                intent.putExtra("game", game);
                startActivity(intent);
                finish();
            }
        });
        tictactoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Instruction.class);
                game="TicTacToe";
                instruccion="¡Piénsalo con calma!\n" +
                        "Toca la tarjeta para colocar la 'X' estratégicamente";
                intent.putExtra("instruccion", instruccion);
                intent.putExtra("game", game);
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

        //setDayNight();
        //switchDayNight = findViewById(R.id.swtDayNight);
        //SharedPreferences sp = getSharedPreferences("SP", this.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sp.edit();
        //Mantener el estado del switch
        //int theme = sp.getInt("Theme", 1);
        //if (theme == 1) {
        //    switchDayNight.setChecked(false);
        //} else {
        //    switchDayNight.setChecked(true);
        //}

        //switchDayNight.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        if (switchDayNight.isChecked()) {
        //            editor.putInt("Theme", 0);
        //        } else {
        //            editor.putInt("Theme", 1);
        //        }
        //        editor.commit();
        //        setDayNight();
        //    }
        //});
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

    public void setDayNight() {
        SharedPreferences sp = getSharedPreferences("SP", this.MODE_PRIVATE);
        int theme = sp.getInt("Theme", 1);
        if (theme == 0) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}