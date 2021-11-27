package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class TicTacToe extends AppCompatActivity {

    TextView txtWin;
    Button sound;
    ImageButton close, again;
    Integer[] arregloBotones;
    MediaPlayer mediaPlayer;
    boolean status = false;
    final Handler handler = new Handler();
    int[] tableroGato = new int[] {
            0, 0, 0,
            0, 0, 0,
            0, 0, 0
    }; //0 = vacío, 1 = colocó el usuario, -1 = colocó la "máquina"
    int estado = 0; //0 = seguir jugando, 1 = ganó el usuario, -1 si aún no ha ganado la "máquina", 2 = empate
    int espaciosUsados = 0;
    int turno = 1; //1 = usuario, -1 = "máquina"
    int[] lineaGanadora = new int[] {
        -1, -1, -1
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        close = findViewById(R.id.imageButtonClose);
        again = findViewById(R.id.imageButtonAgain);
        txtWin = findViewById(R.id.txtResult);
        txtWin.setVisibility(View.INVISIBLE);

        arregloBotones = new Integer[] {
                R.id.btn1,R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6,
                R.id.btn7, R.id.btn8, R.id.btn9
        };

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TicTacToe.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TicTacToe.this, TicTacToe.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*sound = findViewById(R.id.btnSound);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            mediaPlayer = MediaPlayer.create(TicTacToe.this, R.raw.tictactoe);
            mediaPlayer.start();
                if (!status){
                    mediaPlayer.setVolume(0, 0);
                    sound.setImageResource(R.drawable.mute);
                    status = true;
                } else {
                    mediaPlayer.setVolume(1, 1);
                    sound.setImageResource(R.drawable.volume);
                    status = false;
                }
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


    public void agregarSimbolo(View vista){
        if (estado == 0) {
            int numBoton = Arrays.asList(arregloBotones).indexOf(vista.getId()); //Obtener el botón pulsado

            if (tableroGato[numBoton] == 0) { //Validar que no se coloque una marca en un espacio que ya tenga una
                vista.setBackgroundResource(R.drawable.cancel);
                tableroGato[numBoton] = 1; //Asignar una X al gato
                espaciosUsados += 1;
                turno = 1;
                estado = comprobarEstado(); //Comprobar el estado de la partida

                terminarPartida();

                if (estado == 0) {

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            simularIA();
                        }
                    }, 1000);


                    espaciosUsados += 1;
                    turno = -1;
                    estado = comprobarEstado();

                    terminarPartida();
                }
            }
        }
    }

    public void simularIA() {
        Random aleatorio = new Random();
        int posicion = aleatorio.nextInt(tableroGato.length); //Generar número entre 0 y 8

        //Verificar si hay un espacio marcado
        while(tableroGato[posicion] != 0) {
            posicion = aleatorio.nextInt(tableroGato.length); //Buscar el espacio
        }

        Button btn = (Button) findViewById(arregloBotones[posicion]); //Acceder al botón vacío
        btn.setBackgroundResource(R.drawable.circlered); //Asignar un O al gato
        tableroGato[posicion] = -1;
    }

    public  int comprobarEstado() {
        int bandera = 0;
        if (Math.abs(tableroGato[0] + tableroGato[1] + tableroGato[2]) == 3) {
            lineaGanadora = new int[] {0, 1, 2};
            bandera = 1 * turno;//Saber quién ganó, 1*1 = 1 y 1*-1 = -1
        } else if (Math.abs(tableroGato[3] + tableroGato[4] + tableroGato[5]) == 3) {
            lineaGanadora = new int[] {3, 4, 5};
            bandera = 1 * turno;//Saber quién ganó, 1*1 = 1 y 1*-1 = -1
        } else if (Math.abs(tableroGato[6] + tableroGato[7] + tableroGato[8]) == 3) {
            lineaGanadora = new int[] {6, 7, 8};
            bandera = 1 * turno;//Saber quién ganó, 1*1 = 1 y 1*-1 = -1
        } else if (Math.abs(tableroGato[0] + tableroGato[3] + tableroGato[6]) == 3) {
            lineaGanadora = new int[] {0, 3, 6};
            bandera = 1 * turno;//Saber quién ganó, 1*1 = 1 y 1*-1 = -1
        } else if (Math.abs(tableroGato[1] + tableroGato[4] + tableroGato[7]) == 3) {
            lineaGanadora = new int[] {1, 4, 7};
            bandera = 1 * turno;//Saber quién ganó, 1*1 = 1 y 1*-1 = -1
        } else if (Math.abs(tableroGato[2] + tableroGato[5] + tableroGato[8]) == 3) {
            lineaGanadora = new int[] {2, 5, 8};
            bandera = 1 * turno;//Saber quién ganó, 1*1 = 1 y 1*-1 = -1
        }else if (Math.abs(tableroGato[0] + tableroGato[4] + tableroGato[8]) == 3) {
            lineaGanadora = new int[] {0, 4, 8};
            bandera = 1 * turno;//Saber quién ganó, 1*1 = 1 y 1*-1 = -1
        } else if (Math.abs(tableroGato[2] + tableroGato[4] + tableroGato[6]) == 3) {
            lineaGanadora = new int[] {2, 4, 6};
            bandera = 1 * turno;//Saber quién ganó, 1*1 = 1 y 1*-1 = -1
        } else if (espaciosUsados == 9) {
            bandera = 2; //Empate
        }
        return bandera;
    }

    public  void terminarPartida() {
        int simboloGanador = R.drawable.cancelblue;
        if (estado == 1 || estado == -1) {
            if (estado == 1) {
                txtWin.setVisibility(View.VISIBLE);
                txtWin.setTextColor(Color.rgb(59,98,15));

            } else {
                txtWin.setVisibility(View.VISIBLE);
                txtWin.setTextColor(Color.rgb(98,15,15));
                simboloGanador = R.drawable.circleblue;
                txtWin.setText("¡Lo siento, has perdido!");
            }
            for (int i = 0; i < lineaGanadora.length; i++) {
                Button btn = findViewById(arregloBotones[lineaGanadora[i]]);
                btn.setBackgroundResource(simboloGanador);
            }
        } else {
            if (estado == 2) {
                txtWin.setVisibility(View.VISIBLE);
                txtWin.setText("¡Has empatado!");
            }
        }
    }

}