package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToe1vs1 extends AppCompatActivity {

    int[] matriz = new int[9];
    Button imageView1, imageView2, imageView3,
              imageView4, imageView5, imageView6,
              imageView7, imageView8, imageView9;
    ImageView imageViewTurnoX, imageViewTurnoO;
    TextView txtTurno;
    ImageButton close, again, sound;
    MediaPlayer mediaPlayer;
    boolean status = false;
    final Handler handler = new Handler();
    boolean ganaJugador1, ganaJugador2, notificar = false;
    int tiradas = 0, aleatorio = 0;
    private static int jugador1 = 0, jugador2 = 0, empate = 0;
    TextView marcador0, marcador1, marcador2;
    int jugador = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe1vs1);

        txtTurno = findViewById(R.id.txtTurno);
        close = findViewById(R.id.imageButtonClose);
        again = findViewById(R.id.imageButtonAgain);

        imageView1 = findViewById(R.id.imgView1);
        imageView2 = findViewById(R.id.imgView2);
        imageView3 = findViewById(R.id.imgView3);
        imageView4 = findViewById(R.id.imgView4);
        imageView5 = findViewById(R.id.imgView5);
        imageView6 = findViewById(R.id.imgView6);
        imageView7 = findViewById(R.id.imgView7);
        imageView8 = findViewById(R.id.imgView8);
        imageView9 = findViewById(R.id.imgView9);

        for (int i = 0; i <= 8; i++) {
            matriz[i] = 0;
        }

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TicTacToe1vs1.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TicTacToe1vs1.this, TicTacToe1vs1.class);
                startActivity(intent);
                finish();
            }
        });
        //imageView1.setOnClickListener(this);
        //imageView2.setOnClickListener(this);
        //imageView3.setOnClickListener(this);
        //imageView4.setOnClickListener(this);
        //imageView5.setOnClickListener(this);
        //imageView6.setOnClickListener(this);
        //imageView7.setOnClickListener(this);
        //imageView8.setOnClickListener(this);
        //imageView9.setOnClickListener(this);
        //borrar();
        aleatorio = (int)(8 * Math.random()) + 1;
        //actualizarMarcador();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(TicTacToe1vs1.this, R.raw.tictactoe);
        //mediaPlayer.start();
        sound = findViewById(R.id.imageButtonSound);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!status){
                    mediaPlayer.start();
                    sound.setImageResource(R.drawable.volume);
                    status = true;
                } else if (!mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    sound.setImageResource(R.drawable.mute);
                    mediaPlayer = MediaPlayer.create(TicTacToe1vs1.this, R.raw.hangman);
                    status = false;
                }
            }
        });
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

    public void agregarSimbolo(View vista) {
        boolean tiro = false;
        switch (vista.getId()) {
            case R.id.imgView1:
                if (matriz[0] == 0 && jugador == 1) {
                    imageView1.setBackgroundResource(R.drawable.cancel);
                    matriz[0] = 1;
                    txtTurno.setText("Turno: O");
                    //imageViewTurnoX.setBackgroundColor(Color.WHITE);
                    //imageViewTurnoO.setBackgroundColor(Color.RED);
                    tiro = true;
                }
                if (matriz[0] == 0 && jugador == 2) {
                    imageView1.setBackgroundResource(R.drawable.circlered);
                    matriz[0] = 2;
                    txtTurno.setText("Turno: X");
                    //imageViewTurnoX.setBackgroundColor(Color.RED);
                    //imageViewTurnoO.setBackgroundColor(Color.WHITE);
                    tiro = true;
                }
                break;
            case R.id.imgView2:
                if (matriz[1] == 0 && jugador == 1) {
                    imageView2.setBackgroundResource(R.drawable.cancel);
                    matriz[1] = 1;
                    txtTurno.setText("Turno: O");
                    //imageViewTurnoX.setBackgroundColor(Color.WHITE);
                    //imageViewTurnoO.setBackgroundColor(Color.RED);
                    tiro = true;
                }
                if (matriz[1] == 0 && jugador == 2) {
                    imageView2.setBackgroundResource(R.drawable.circlered);
                    matriz[1] = 2;
                    txtTurno.setText("Turno: X");
                    //imageViewTurnoX.setBackgroundColor(Color.RED);
                    //imageViewTurnoO.setBackgroundColor(Color.WHITE);
                    tiro = true;
                }
                break;
            case R.id.imgView3:
                if (matriz[2] == 0 && jugador == 1) {
                    imageView3.setBackgroundResource(R.drawable.cancel);
                    matriz[2] = 1;
                    txtTurno.setText("Turno: O");
                    //imageViewTurnoX.setBackgroundColor(Color.WHITE);
                    //imageViewTurnoO.setBackgroundColor(Color.RED);
                    tiro = true;
                }
                if (matriz[2] == 0 && jugador == 2) {
                    imageView3.setBackgroundResource(R.drawable.circlered);
                    matriz[2] = 2;
                    txtTurno.setText("Turno: X");
                    //imageViewTurnoX.setBackgroundColor(Color.RED);
                    //imageViewTurnoO.setBackgroundColor(Color.WHITE);
                    tiro = true;
                }
                break;
            case R.id.imgView4:
                if (matriz[3] == 0 && jugador == 1) {
                    imageView4.setBackgroundResource(R.drawable.cancel);
                    matriz[3] = 1;
                    txtTurno.setText("Turno: O");
                    //imageViewTurnoX.setBackgroundColor(Color.WHITE);
                    //imageViewTurnoO.setBackgroundColor(Color.RED);
                    tiro = true;
                }
                if (matriz[3] == 0 && jugador == 2) {
                    imageView4.setBackgroundResource(R.drawable.circlered);
                    matriz[3] = 2;
                    txtTurno.setText("Turno: X");
                    //imageViewTurnoX.setBackgroundColor(Color.RED);
                    //imageViewTurnoO.setBackgroundColor(Color.WHITE);
                    tiro = true;
                }
                break;
            case R.id.imgView5:
                if (matriz[4] == 0 && jugador == 1) {
                    imageView5.setBackgroundResource(R.drawable.cancel);
                    matriz[4] = 1;
                    txtTurno.setText("Turno: O");
                    //imageViewTurnoX.setBackgroundColor(Color.WHITE);
                    //imageViewTurnoO.setBackgroundColor(Color.RED);
                    tiro = true;
                }
                if (matriz[4] == 0 && jugador == 2) {
                    imageView5.setBackgroundResource(R.drawable.circlered);
                    matriz[4] = 2;
                    txtTurno.setText("Turno: X");
                    //imageViewTurnoX.setBackgroundColor(Color.RED);
                    //imageViewTurnoO.setBackgroundColor(Color.WHITE);
                    tiro = true;
                }
                break;
            case R.id.imgView6:
                if (matriz[5] == 0 && jugador == 1) {
                    imageView6.setBackgroundResource(R.drawable.cancel);
                    matriz[5] = 1;
                    txtTurno.setText("Turno: O");
                    //imageViewTurnoX.setBackgroundColor(Color.WHITE);
                    //imageViewTurnoO.setBackgroundColor(Color.RED);
                    tiro = true;
                }
                if (matriz[5] == 0 && jugador == 2) {
                    imageView6.setBackgroundResource(R.drawable.circlered);
                    matriz[5] = 2;
                    txtTurno.setText("Turno: X");
                    //imageViewTurnoX.setBackgroundColor(Color.RED);
                    //imageViewTurnoO.setBackgroundColor(Color.WHITE);
                    tiro = true;
                }
                break;
            case R.id.imgView7:
                if (matriz[6] == 0 && jugador == 1) {
                    imageView7.setBackgroundResource(R.drawable.cancel);
                    matriz[6] = 1;
                    txtTurno.setText("Turno: O");
                    //imageViewTurnoX.setBackgroundColor(Color.WHITE);
                    //imageViewTurnoO.setBackgroundColor(Color.RED);
                    tiro = true;
                }
                if (matriz[6] == 0 && jugador == 2) {
                    imageView7.setBackgroundResource(R.drawable.circlered);
                    matriz[6] = 2;
                    txtTurno.setText("Turno: X");
                    //imageViewTurnoX.setBackgroundColor(Color.RED);
                    //imageViewTurnoO.setBackgroundColor(Color.WHITE);
                    tiro = true;
                }
                break;
            case R.id.imgView8:
                if (matriz[7] == 0 && jugador == 1) {
                    imageView8.setBackgroundResource(R.drawable.cancel);
                    matriz[7] = 1;
                    txtTurno.setText("Turno: O");
                    //imageViewTurnoX.setBackgroundColor(Color.WHITE);
                    //imageViewTurnoO.setBackgroundColor(Color.RED);
                    tiro = true;
                }
                if (matriz[7] == 0 && jugador == 2) {
                    imageView8.setBackgroundResource(R.drawable.circlered);
                    matriz[7] = 2;
                    txtTurno.setText("Turno: X");
                    //imageViewTurnoX.setBackgroundColor(Color.RED);
                    //imageViewTurnoO.setBackgroundColor(Color.WHITE);
                    tiro = true;
                }
                break;
            case R.id.imgView9:
                if (matriz[8] == 0 && jugador == 1) {
                    imageView9.setBackgroundResource(R.drawable.cancel);
                    matriz[8] = 1;
                    txtTurno.setText("Turno: O");
                    //imageViewTurnoX.setBackgroundColor(Color.WHITE);
                    //imageViewTurnoO.setBackgroundColor(Color.RED);
                    tiro = true;
                }
                if (matriz[8] == 0 && jugador == 2) {
                    imageView9.setBackgroundResource(R.drawable.circlered);
                    matriz[8] = 2;
                    txtTurno.setText("Turno: X");
                    //imageViewTurnoX.setBackgroundColor(Color.RED);
                    //imageViewTurnoO.setBackgroundColor(Color.WHITE);
                    tiro = true;
                }
                break;
        }
        if (tiro == true) {
            if (jugador == 1) {
                jugador = 2;
            } else {
                jugador = 1;
            }
            tiradas++;
            lineaGanadora();
            obtenerGanador();
        }
    }

    /*public void borrar() {
        imageView1.setImageResource(R.drawable.);
        imageView2.setImageResource(R.drawable.);
        imageView3.setImageResource(R.drawable.);
        imageView4.setImageResource(R.drawable.);
        imageView5.setImageResource(R.drawable.);
        imageView6.setImageResource(R.drawable.);
        imageView7.setImageResource(R.drawable.);
        imageView8.setImageResource(R.drawable.);
        imageView9.setImageResource(R.drawable.);
        for (int i = 0; i <= 8; i++) {
            matriz[i] = 0;
        }
        ganaJugador1 = false;
        ganaJugador2 = false;
        tiradas = 0;
        aleatorio = (int)(8 * Math.random()) + 1;
    }*/

   /* public void actualizarMarcador() {
        marcador0.setText("");
        marcador1.setText("");
        marcador2.setText("");
        String text1 = String.valueOf(jugador1);
        String text2 = String.valueOf(jugador2);
        String text3 = String.valueOf(empate);
        marcador0.setText(text1);
        marcador1.setText(text2);
        marcador2.setText(text3);
    }*/

    public void obtenerGanador() {
        if (ganaJugador1 == true) {
            Toast toast = Toast.makeText(this, "Ha ganado el jugador 1", Toast.LENGTH_SHORT);
            toast.show();
            jugador1++;
            reiniciarActivity(this);
        }
        if (ganaJugador2 == true) {
            Toast toast = Toast.makeText(this, "Ha ganado el jugador 2", Toast.LENGTH_SHORT);
            toast.show();
            jugador2++;
            reiniciarActivity(this);
        }
        if (tiradas == 9 && ganaJugador1 == false && ganaJugador2 == false) {
            Toast toast = Toast.makeText(this, "Ha sido un empate", Toast.LENGTH_SHORT);
            toast.show();
            empate++;
            reiniciarActivity(this);
        }
    }

    public static void reiniciarActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, activity.getClass());

        activity.startActivity(intent); //Llamar a la actividad

        activity.finish(); //Terminar la actividad actual
    }

    public void lineaGanadora() {
        //Gana X
        if (matriz[0] == 1 && matriz[1] == 1 && matriz[2] == 1) {
            ganaJugador1 = true;
        }
        if (matriz[3] == 1 && matriz[4] == 1 && matriz[5] == 1) {
            ganaJugador1 = true;
        }
        if (matriz[6] == 1 && matriz[7] == 1 && matriz[8] == 1) {
            ganaJugador1 = true;
        }
        if (matriz[0] == 1 && matriz[3] == 1 && matriz[6] == 1) {
            ganaJugador1 = true;
        }
        if (matriz[1] == 1 && matriz[4] == 1 && matriz[7] == 1) {
            ganaJugador1 = true;
        }
        if (matriz[2] == 1 && matriz[5] == 1 && matriz[8] == 1) {
            ganaJugador1 = true;
        }
        if (matriz[0] == 1 && matriz[4] == 1 && matriz[8] == 1) {
            ganaJugador1 = true;
        }
        if (matriz[2] == 1 && matriz[4] == 1 && matriz[6] == 1) {
            ganaJugador1 = true;
        }

        //Gana O
        if (matriz[0] == 2 && matriz[1] == 2 && matriz[2] == 2) {
            ganaJugador2 = true;
        }
        if (matriz[3] == 2 && matriz[4] == 2 && matriz[5] == 2) {
            ganaJugador2 = true;
        }
        if (matriz[6] == 2 && matriz[7] == 2 && matriz[8] == 2) {
            ganaJugador2 = true;
        }
        if (matriz[0] == 2 && matriz[3] == 2 && matriz[6] == 2) {
            ganaJugador2 = true;
        }
        if (matriz[1] == 2 && matriz[4] == 2 && matriz[7] == 2) {
            ganaJugador2 = true;
        }
        if (matriz[2] == 2 && matriz[5] == 2 && matriz[8] == 2) {
            ganaJugador2 = true;
        }
        if (matriz[0] == 2 && matriz[4] == 2 && matriz[8] == 2) {
            ganaJugador2 = true;
        }
        if (matriz[2] == 2 && matriz[4] == 2 && matriz[6] == 2) {
            ganaJugador2 = true;
        }
    }
}