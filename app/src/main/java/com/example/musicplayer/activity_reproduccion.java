package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class activity_reproduccion extends AppCompatActivity {
    Button play_pause, btnRepetir;
    MediaPlayer mp;
    ImageView imgV;

    MediaPlayer vectormp[] = new MediaPlayer[3];
    int repetir = 2, posicion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproduccion);

        play_pause = (Button) findViewById(R.id.btnplay);
        btnRepetir = (Button) findViewById(R.id.btnAleatorio);
        imgV = (ImageView) findViewById(R.id.imageView6);

        //Lista de canciones a reproducir esta implementado sin bbdd aqui seria donde poner las canciones
       // vectormp[0] = MediaPlayer.create(this, R.cancion.race);
       // vectormp[1] = MediaPlayer.create(this, R.cancion.sound);
        //vectormp[2] = MediaPlayer.create(this, R.cancion.race);

    }

    //Metodo para el boton PlayPause
    public void oyente_btnPlay(View view) {
        if (vectormp[posicion].isPlaying()) {
            vectormp[posicion].pause();
            play_pause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
        } else {
            vectormp[posicion].start();
            play_pause.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this, "play", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo para el boton stop
    public void oyente_btnDetener(View view) {
        if (vectormp[posicion] != null) {
            vectormp[posicion].stop();
            //vectormp[0] = MediaPlayer.create(this, R.cancion.race);
            //vectormp[1] = MediaPlayer.create(this, R.cancion.sound);
           // vectormp[2] = MediaPlayer.create(this, R.cancion.race);
            posicion = 0;
            play_pause.setBackgroundResource(R.drawable.reproducir);
            imgV.setImageResource(R.drawable.portada1);
            Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
        }
    }

    public void oyente_btnAnterior(View view) {
        if (posicion >= 1) {
            if (vectormp[posicion].isPlaying()) {
                vectormp[posicion].stop();
               // vectormp[0] = MediaPlayer.create(this, R.cancion.race);
               // vectormp[1] = MediaPlayer.create(this, R.cancion.sound);
              //  vectormp[2] = MediaPlayer.create(this, R.cancion.race);
                posicion--;

                //Cambia la cancion a corde con la caratula de la cancion que suena
                if (posicion == 0) {
                    imgV.setImageResource(R.drawable.portada1);
                } else if (posicion == 1) {
                    imgV.setImageResource(R.drawable.portada2);
                } else if (posicion == 2) {
                    imgV.setImageResource(R.drawable.portada3);

                }
                vectormp[posicion].start();
            } else {
                posicion--;
                if (posicion == 0) {
                    imgV.setImageResource(R.drawable.portada1);
                } else if (posicion == 1) {
                    imgV.setImageResource(R.drawable.portada2);
                } else if (posicion == 2) {
                    imgV.setImageResource(R.drawable.portada3);

                }
            }

        } else {
            Toast.makeText(this, "No hay más canciones", Toast.LENGTH_SHORT).show();
        }
    }

    public void oyente_btnSiguiente(View view) {
        if (posicion < vectormp.length - 1) {
            if (vectormp[posicion].isPlaying()) {
                vectormp[posicion].stop();
                posicion++;
                vectormp[posicion].start();
                //Cambia la cancion a corde con la caratula de la cancion que suena
                if (posicion == 0) {
                    imgV.setImageResource(R.drawable.portada1);
                } else if (posicion == 1) {
                    imgV.setImageResource(R.drawable.portada2);
                } else if (posicion == 2) {
                    imgV.setImageResource(R.drawable.portada3);

                }

            } else {
                Toast.makeText(this, "No hay más canciones", Toast.LENGTH_SHORT).show();
            }

        }
    }

    //Metodo repetir canción me confudi con aleatorio
    public void oyente_btnAleatorio(View view) {
        if (repetir == 1) {
            btnRepetir.setBackgroundResource(R.drawable.no_repetir);
            Toast.makeText(this, "No repetir", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(false);
            repetir = 2;

        } else {
            btnRepetir.setBackgroundResource(R.drawable.repetir);
            Toast.makeText(this, "No repetir", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(true);
            repetir = 1;

        }
    }
}
