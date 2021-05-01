package com.example.musicplayer.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicplayer.Persistencia.CancionDAO;
import com.example.musicplayer.Persistencia.PlayListDAO;
import com.example.musicplayer.R;

public class ventana_reproducir extends AppCompatActivity {

    private Button btnReproducir;
    private Button btnDetener;
    private Button btnFavoritos;
    private ImageView imageCover;
    private TextView nombre_cancion;
    private Toast notification_play_list;

    private String identificador_cancion;
    private String nombre_cancion_BBDD;
    private Bitmap imagen_cancion;
    private int codigo_audio_BBDD;

    private MediaPlayer media_player = new MediaPlayer();
    private CancionDAO gestor_cancion = new CancionDAO();
    private PlayListDAO gestor_play_list = new PlayListDAO();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproducir);

        inicializarDatos();
        inicializarDatosBBDD();
        mostrarDatos();
        playAudio();
        oyentesBotones();

    }

    /**
     *
     * Descripcion: Metodo que permite inicializar los datos de la ventana reproductor
     *
     */
    private void inicializarDatos(){

        this.btnReproducir = (Button) findViewById(R.id.btnPlay);
        this.btnDetener = (Button) findViewById(R.id.btnDetener);
        this.btnFavoritos = (Button) findViewById(R.id.btnFavoritos);
        this.imageCover = (ImageView) findViewById(R.id.imageViewCover);
        this.nombre_cancion = (TextView) findViewById(R.id.txtNombreCancionMusica);

        Bundle bundle = getIntent().getExtras();
        this.identificador_cancion = bundle.getString("identificador_cancion");

    }

    /**
     *
     * Descripcion: Metodo que inicializa los datos realizando consultas en la base de datos
     *
     */
    private void inicializarDatosBBDD(){

        this.imagen_cancion = gestor_cancion.buscarImagenCancion(ventana_reproducir.this, identificador_cancion,
                "ImagenCancion");

        this.nombre_cancion_BBDD = gestor_cancion.buscarDatosCancion(ventana_reproducir.this,
                identificador_cancion, "NombreCancion");

        this.codigo_audio_BBDD = Integer.parseInt(gestor_cancion.buscarDatosCancion(ventana_reproducir.this,
                this.identificador_cancion, "AudioCancion"));

    }

    /**
     *
     * Descripcion: Metodo que permite mostart los datos despues de hacer las consultas a la base de datos
     *
     */
    private void mostrarDatos(){

        this.nombre_cancion.setText(nombre_cancion_BBDD);
        this.imageCover.setImageBitmap(imagen_cancion);

    }

    /**
     *
     * Descripcion: Metodo que al abrir la ventana permite que suene el audio por defecto
     *
     */
    private void playAudio(){

        this.media_player = MediaPlayer.create(this, this.codigo_audio_BBDD);
        this.media_player.start();

    }

    /**
     *
     * Descripcion: Metodo que contiene los oyentes de los botones asociados a esta ventana
     *
     */
    private void oyentesBotones(){

        this.btnReproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                media_player.start();

            }
        });

        this.btnDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                media_player.pause();

            }
        });

        this.btnFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                comprobarEstadoPlayList();

            }
        });

    }

    /**
     *
     * Descripcion: Metodo que comprueba si una cancion ya esta asociada a un usuario y no aniada a la playlist
     * las mismas canciones
     *
     */
    private void comprobarEstadoPlayList(){

        int comprobar_cancion_play_list = gestor_play_list.buscarCancionRegistrada(ventana_reproducir.this,
                identificador_cancion);

        if(comprobar_cancion_play_list == 0){

            gestor_play_list.insertarDatosTablaPlayList(ventana_reproducir.this, menu_principal.usuario_sesion_iniciada,
                    this.identificador_cancion);

            mostrarNotificacion("Cancion aniadida a favoritos");

        }
        else if(comprobar_cancion_play_list > 0){

            mostrarNotificacion("Esta cancion ya esta en su lista");

        }
    }

    /**
     *
     * Descripcion: Metodo que muestra una notificacion
     *
     * @param cadena variable que contiene el mensaje que se quiere mostrar al usuario
     */
    private void mostrarNotificacion(String cadena){

        this.notification_play_list = Toast.makeText(ventana_reproducir.this, cadena,
                Toast.LENGTH_LONG);

        this.notification_play_list.show();

    }
}