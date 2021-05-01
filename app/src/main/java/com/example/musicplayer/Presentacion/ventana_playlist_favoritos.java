package com.example.musicplayer.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import com.example.musicplayer.Adaptadores.AdaptadorListaPlayList;
import com.example.musicplayer.Dominio.Cancion;
import com.example.musicplayer.Persistencia.CancionDAO;
import com.example.musicplayer.Persistencia.PlayListDAO;
import com.example.musicplayer.R;

import java.util.ArrayList;

public class ventana_playlist_favoritos extends AppCompatActivity {

    private String id_usuario;
    private String id_cancion;
    private String [] id_canciones;

    private ArrayList<Cancion> canciones_playlist;
    private RecyclerView lstPlayList;
    private AdaptadorListaPlayList adaptador_playlist;
    private Toast notification;

    private PlayListDAO gestor_play_list = new PlayListDAO();
    private CancionDAO gestor_cancion = new CancionDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_playlist_favoritos);

        inicializarDatos();
        inicializarAdaptador();
        rellenarDatos();

    }

    /**
     *
     * Descripcion: Metodo que permite inicializar los datos de la ventana playlist
     *
     */
    private void inicializarDatos(){

        this.id_usuario = id_usuario = menu_principal.usuario_sesion_iniciada;

    }

    /**
     *
     * Descripcion: Obtenemos una referencia a la lista grafica y se crea
     * la lista de contactos y anadir algunos datos de prueba
     *
     */
    private void inicializarAdaptador(){

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        this.lstPlayList = findViewById(R.id.lstPlayList);

        this.canciones_playlist = new ArrayList<Cancion>();
        this.lstPlayList.setLayoutManager(mLayoutManager);
        this.adaptador_playlist = new AdaptadorListaPlayList(canciones_playlist);
        this.lstPlayList.setAdapter(adaptador_playlist);
        this.lstPlayList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    }

    /**
     *
     * Descripcion: Metodo que permite inicializar la lista dependiendo de los datos obtenidos de la
     * consulta
     *
     */
    private void rellenarDatos(){

        int numero_canciones_usuario = obtenerNumeroCancionesUsuario();

        if(numero_canciones_usuario != 0) {

            mostrarNotificacion("Canciones favoritas de "+this.id_usuario);

            this.id_canciones = gestor_play_list.getListaCancionesFavoritas(ventana_playlist_favoritos.this,
                    id_usuario, numero_canciones_usuario);

            for(int i = 0; i< this.id_canciones.length; i++) {

                String parametro_id_album = gestor_cancion.buscarDatosCancion(ventana_playlist_favoritos.this,
                        this.id_canciones[i], "IdAlbumCancion");

                String parametro_id_cancion = gestor_cancion.buscarDatosCancion(ventana_playlist_favoritos.this,
                        this.id_canciones[i], "IdArtistaCancion");

                String parametro_nombre_cancion = gestor_cancion.buscarDatosCancion(ventana_playlist_favoritos.this,
                        this.id_canciones[i], "NombreCancion");

                String parametro_duracion_cancion = gestor_cancion.buscarDatosCancion(ventana_playlist_favoritos.this,
                        this.id_canciones[i], "DuracioCancion");

                String parametro_audio_cancion = gestor_cancion.buscarDatosCancion(ventana_playlist_favoritos.this,
                        this.id_canciones[i], "AudioCancion");

                Bitmap bitmap = gestor_cancion.buscarImagenCancion(ventana_playlist_favoritos.this,
                        this.id_canciones[i], "ImagenCancion");

                this.canciones_playlist.add(new Cancion(this.id_canciones[i], parametro_id_album, parametro_id_cancion,
                        parametro_nombre_cancion, parametro_duracion_cancion, parametro_audio_cancion, bitmap));

            }
        }
        else {

            this.canciones_playlist.add(new Cancion("No disponible","No disponible", "No disponible",
                    "No disponible","No disponible","No disponible",
                    null));

            mostrarNotificacion("Ninguna cancion en favoritos");

        }

    }

    /**
     *
     * Descripcion: Metodo que permite obtener el nuemero de canciones favoritas de un determinado usuario
     * en la aplicacion
     *
     * @return un entero con el numero de canciones del usuario en la aplicacion
     */
    private int obtenerNumeroCancionesUsuario(){

        return this.gestor_play_list.getNumeroCancionesUsuario(ventana_playlist_favoritos.this, this.id_usuario);

    }

    /**
     *
     * Descripcion: Metodo que notifica al usuario de una accion
     *
     * @param cadena con el mensaje personaliszado dependiendo de la situacion
     */
    private void mostrarNotificacion(String cadena){

        this.notification = Toast.makeText(ventana_playlist_favoritos.this, cadena,
                Toast.LENGTH_LONG);

        this.notification.show();

    }

}