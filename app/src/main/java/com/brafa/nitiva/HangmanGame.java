package com.brafa.nitiva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class HangmanGame extends AppCompatActivity {

    private String [] palabras;
    Random random = new Random();
    private String actual;
    ScrollView scrollView;
    ImageButton salir, reiniciar, btnAsk;
    private WordAdapter wordAdapter;
    FrameLayout frameLayout;
    private GridView wordView;
    ArrayList<String> word = new ArrayList<>();
    TextView txtOportunidades;
    int puntaje=0;
    GridLayout gridLetters;
    private int numCorr;
    String oportunidades;
    MediaPlayer mediaPlayer;
    boolean status = false;
    final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman_game);
        scrollView = findViewById(R.id.scrollLetters);
        txtOportunidades = findViewById(R.id.textViewOportunidades);
        oportunidades = "❤❤❤❤❤";
        txtOportunidades.setText(oportunidades);
        puntaje = 0;
        gridLetters = findViewById(R.id.gridLetters);

        play();
        cargarBotones();
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*sound = findViewById(R.id.btnSound);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            mediaPlayer = MediaPlayer.create(HangmanGame.this, R.raw.hangman);
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

    private void activarBotones(GridLayout view){
        for (int i = 0; i < view.getChildCount(); i++) {
            Button btn = (Button) view.getChildAt(i);
            btn.setVisibility(View.VISIBLE);
        }
    }
    public void cargarBotones(){





        frameLayout = findViewById(R.id.frame);
        btnAsk = findViewById(R.id.btn16);

        btnAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout.setVisibility(View.VISIBLE);



                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frameLayout.setVisibility(View.GONE);
                    }
                }, 8000);

            }
        });






        salir = findViewById(R.id.imageButtonSalir);
        reiniciar = findViewById(R.id.imageButtonReiniciar);
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                word.clear();
                puntaje = 0;
                play();
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HangmanGame.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void play(){
        activarBotones(gridLetters);
        palabras = getResources().getStringArray(R.array.palabras);
        String nPalabra = palabras[random.nextInt(palabras.length)];
        while (nPalabra.equals(actual))nPalabra = palabras[random.nextInt(palabras.length)];

        actual = nPalabra;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PistaFragment pistaFragment = new PistaFragment();
        Bundle bundle = new Bundle();

        String pista = "";

        switch (actual)
        {
            case "PERRO":
                pista = "El mejor amigo del hombre.";
                break;
            case "GATO":
                pista = "Son leones en miniatura pero no saben cazar bien.";
                break;
            case "PAJARO":
                pista = "Algunos cantan bonito y son de colores muy llamativos.";
                break;
            case "MARIPOSA":
                pista = "Ama el néctar de las flores y hay de varios colores.";
                break;
            case "ARBOL":
                pista = "Hogar de pájaros y ardillas.";
                break;
            case "CASA":
                pista = "Es bonito llegar despúes de trabajar";
                break;
            case "PERSONA":
                pista = "El mundo está lleno de ellas, cada una única y especial a su manera.";
                break;
            case "IGLESIA":
                pista = "Suenan las campanas todos los domingos.";
                break;
            case "PRESIDENTE":
                pista = "Persona importante que representa al país.";
                break;
            case "ESCUELA":
                pista = "Aquí se hacen los primeros amigos y se aprenden muchas cosas.";
                break;
            case "PARQUE":
                pista = "Lugar pacífico lleno de personas y palomas.";
                break;
            case "COMEDOR":
                pista = "Las personas se reúnen para comer aquí.";
                break;
            case "MASCOTA":
                pista = "Fiel compañero que es parte de la familia.";
                break;
            case "INSECTO":
                pista = "Solo tiene seis patas y es bastante pequeño.";
                break;
            case "ESTRELLA":
                pista = "Se ven miles en las noches de verano.";
                break;
            case "JUEGO":
                pista = "Ideal para pasar un rato de diversión.";
                break;
            case "APRENDER":
                pista = "Todos los días debemos buscar hacerlo.";
                break;
            case "TECNOLOGIA":
                pista = "Maravillosa y avanza cada día.";
                break;
            case "CABELLO":
                pista = "Algunos lo tienen largo, otros corto y algunos... algunos no tienen.";
                break;
            case "HOJAS":
                pista = "Algunas se secan y caen en verano, otras se mantienen verdes.";
                break;
            case "EDIFICIO":
                pista = "Son altos y la ciudad está llena de ellos.";
                break;
            case "TELAS":
                pista = "Hay mucha variedad en colores y material, se pueden cortar para prendas realizar.";
                break;
            case "TRAJE":
                pista = "Vestimenta elegante para eventos importantes.";
                break;
            case "VESTIDO":
                pista = "Se puede usar en fiestas, eventos de gala y hasta para casarse.";
                break;
            case "MONTAÑA":
                pista = "Puedes caminar en ella hasta la cima y tener una gran vista.";
                break;
            case "TIBURON":
                pista = "Nada rápido si su aleta ves en el mar.";
                break;
            case "BALLENA":
                pista = "Es el más grande y majestuoso mamífero acuatico.";
                break;
            case "VOLCAN":
                pista = "Si está activo es bastante reisgoso visitarlo.";
                break;
            case "PLAYA":
                pista = "Ideal para relajarse y tomar el sol.";
                break;
            case "BOSQUE":
                pista = "Lleno de árboles pero cuidado con los animales que te puedas encontrar.";
                break;
            case "FUTURO":
                pista = "Lleno de nuevas historias e incertidumbre.";
                break;
            case "PASADO":
                pista = "Lleno de memorables historias y hermosos recuerdos.";
                break;
            case "PRESENTE":
                pista = "En donde estamos por nuestro pasado y donde podemos definir nuestro futuro.";
                break;
            case "REGALO":
                pista = "Es algo especial que se le entega a una persona querida, algunos están envueltos.";
                break;
            case "DORMIR":
                pista = "Las noches son ideales para esto, aunque en la tarde no está mal tomarse un descanso.";
                break;
            case "LIBRO":
                pista = "Objetos llenos de historias y asombrosas aventuras.";
                break;
            case "LAPIZ":
                pista = "Escribe hasta que se gaste.";
                break;
            case "BORRADOR":
                pista = "Es bastante útil si quieres corregir algo mal escrito.";
                break;
            case "COLORES":
                pista = "Hay miles de ellos y para todos los gustos.";
                break;
            case "AMIGOS":
                pista = "Si tienes pocos no te preocupes, de seguro son de verdad.";
                break;
            case "AHINCO":
                pista = "Se debe poner en práctica si se desea algo con fuerza.";
                break;
            case "HAZAÑA":
                pista = "Resultado de un gran valor y esfuerzo.";
                break;
            case "HEGEMONIA":
                pista = "Cuando un estado tiene poder sobre otro.";
                break;
            case "EPIFANIA":
                pista = "Es una manifestación magnífica.";
                break;
            case "INTRANSIGENTE":
                pista = "Se mantiene firme y no cambiará su postura.";
                break;
            default:
                pista = "No se encontró pista";
                break;
        }



        bundle.putString("pista", pista);
        pistaFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.frame,pistaFragment);
        fragmentTransaction.commit();







        for (int i = 0; i < actual.length(); i++) {
            word.add(""+actual.charAt(i));
        }


        wordView = findViewById(R.id.screenWord);




        wordAdapter = new WordAdapter( word, this);

        wordView.setAdapter(wordAdapter);

        numCorr =0;
        oportunidades = "❤❤❤❤❤";
        txtOportunidades.setText(oportunidades);

    }
    public void letterPressed(View view){
        String letter = ((TextView)view).getText().toString();
        char letterChar = letter.charAt(0);
        view.setVisibility(View.INVISIBLE);
        boolean correct = false;

        for (int i = 0; i < actual.length(); i++) {
            if(actual.charAt(i)==letterChar){
                correct = true;
                numCorr++;
                puntaje+=10;
                final int numVisibleChildren = wordView.getChildCount();
                final int firstVisiblePosition = wordView.getFirstVisiblePosition();

                for ( int j = 0; j < numVisibleChildren; j++ ) {
                    int positionOfView = firstVisiblePosition + j;

                    if (positionOfView == i) {
                        View element = wordView.getChildAt(j);
                        element.setBackgroundResource(R.drawable.charshape);
                    }
                }
            }
        }
        if(correct){
            if(numCorr== actual.length()){
                LayoutInflater layoutInflater2 = getLayoutInflater();
                View view2 = layoutInflater2.inflate(R.layout.custom2_toast, (ViewGroup) findViewById(R.id.toast2));
                TextView textView2 = view2.findViewById(R.id.textView8);
                textView2.setText(actual);
                final Toast toast2 = new Toast(getApplicationContext());
                toast2.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast2.setDuration(Toast.LENGTH_SHORT);
                toast2.setView(view2);
                toast2.show();
                word.clear();
                play();
            }
        }else {
            if(!oportunidades.isEmpty()){
                oportunidades = oportunidades.substring(0, oportunidades.length()-1);
                txtOportunidades.setText(oportunidades);

            }else {
                Intent intent = new Intent(HangmanGame.this, Result.class);
                intent.putExtra("puntajeHangman", puntaje);
                startActivity(intent);
                finish();
            }
        }
    }

}