package com.brafa.nitiva;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageButton memory, tictactoe, numberMemory, hangman, about;
    Button exit, sound;
    Switch switchDayNight;
    TextView textView;
    Animation animationButton, animationText;
    MediaPlayer mediaPlayer;
    Integer count = 0;
    private String game="";
    private String instruccion="";
    boolean status = false;


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

        about = findViewById(R.id.btn);
        textView.setAnimation(animationText);
        memory.setAnimation(animationButton);
        numberMemory.setAnimation(animationButton);
        hangman.setAnimation(animationButton);
        tictactoe.setAnimation(animationButton);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
                finish();
            }
        });

        hangman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Instruction.class);
                game="Hangman";
                instruccion="¡Trata de adivinar la palabra eligiendo las letras de la pantalla!\n" +
                        "Puedes ver una pista presionando el botón de '?'";
                intent.putExtra("instruccion", instruccion);
                intent.putExtra("game", game);
                //mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.hangman);
                //mediaPlayer.start();
                startActivity(intent);
                finish();
            }
        });
        numberMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Instruction.class);
                game="NumberMemory";
                instruccion="¡Memoriza el número antes de que desaparezca y escríbelo para avanzar de nivel!";
                intent.putExtra("instruccion", instruccion);
                intent.putExtra("game", game);
                //mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.nummemory);
                //mediaPlayer.start();
                startActivity(intent);
                finish();
            }
        });
        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Instruction.class);
                game="Memory";
                instruccion="¡Pon atención a la posición de las imágenes en la pantalla antes de que desaparezcan!\n" +
                        "Toca las tarjetas para revelar la imagen y encontrar su respectiva pareja";
                intent.putExtra("instruccion", instruccion);
                intent.putExtra("game", game);
                //mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.memory);
                //mediaPlayer.start();
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
                //mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.tictactoe);
                //mediaPlayer.start();
                startActivity(intent);
                finish();
            }
        });
        exit = findViewById(R.id.btnExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDialogMessage();
                finish();
            }
        });

    }

    @Override
    protected void onResume() {

        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast1));
        TextView textView = view.findViewById(R.id.textView8);
        textView.setText("Has desactivado el sonido");
        final Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);

        LayoutInflater layoutInflater2 = getLayoutInflater();
        View view2 = layoutInflater2.inflate(R.layout.custom2_toast, (ViewGroup) findViewById(R.id.toast2));
        TextView textView2 = view2.findViewById(R.id.textView8);
        textView2.setText("Has activado el sonido");
        final Toast toast2 = new Toast(getApplicationContext());
        toast2.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast2.setDuration(Toast.LENGTH_SHORT);
        toast2.setView(view2);


        super.onResume();

        //if  (!status) {
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.adaytoremember);
            mediaPlayer.start();
        //    status = true;
        //}

        sound = findViewById(R.id.btnSound);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!status){
                    mediaPlayer.setVolume(0, 0);
                    toast.show();
                    status = true;
                } else {
                    mediaPlayer.setVolume(1, 1);
                    toast2.show();
                    status = false;
                }
            }
        });

        /*hangman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Instruction.class);
                game="Hangman";
                instruccion="¡Trata de adivinar la palabra eligiendo las letras de la pantalla!\n" +
                        "Puedes ver una pista presionando el botón de '?'";
                intent.putExtra("instruccion", instruccion);
                intent.putExtra("game", game);
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.hangman);
                mediaPlayer.start();
                startActivity(intent);
                finish();
            }
        });
        numberMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Instruction.class);
                game="NumberMemory";
                instruccion="¡Memoriza el número que se muestra antes de que desaparezca y escríbelo para avanzar de nivel!";
                intent.putExtra("instruccion", instruccion);
                intent.putExtra("game", game);
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.nummemory);
                mediaPlayer.start();
                startActivity(intent);
                finish();
            }
        });
        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Instruction.class);
                game="Memory";
                instruccion="¡Pon atención a la posición de las imágenes en la pantalla antes de que desaparezcan!\n" +
                        "Toca las tarjetas para revelar la imagen y buscar en donde se encontraba la pareja";
                intent.putExtra("instruccion", instruccion);
                intent.putExtra("game", game);
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.memory);
                mediaPlayer.start();
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
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.tictactoe);
                mediaPlayer.start();
                startActivity(intent);
                finish();
            }
        });*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*if (!status){
            mediaPlayer.setVolume(0, 0);
            status = true;
        } else {
            mediaPlayer.setVolume(1, 1);
            status = false;
        }*/
        mediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
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