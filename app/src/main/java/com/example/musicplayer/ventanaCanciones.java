package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.musicplayer.Adaptadores.AdaptadorListaArtista;
import com.example.musicplayer.Adaptadores.AdaptadorListaCancion;
import com.example.musicplayer.Dominio.Artista;
import com.example.musicplayer.Dominio.Cancion;
import com.example.musicplayer.Persistencia.ArtistaDAO;
import com.example.musicplayer.Persistencia.CancionDAO;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ventanaCanciones extends AppCompatActivity {

    private ArrayList<Cancion> canciones;
    private RecyclerView lstCanciones;
    private AdaptadorListaCancion adaptador_canciones;

    private CancionDAO gestor_cancion = new CancionDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_canciones);

        //IMPORTANTE

        Log.d("Debug_Excepcion", "INTEGER"+R.raw.avicii);

        String codigo = gestor_cancion.buscarDatosCancion(ventanaCanciones.this, "111","AudioCancion");
        int codigo_bueno = Integer.parseInt(codigo);
        MediaPlayer mp = MediaPlayer.create(this, codigo_bueno);
        mp.start();

        /**IMPORTANTE
         *
        MediaPlayer mp = MediaPlayer.create(this, R.raw.avicii);
        mp.start();

        */

        // Obtenemos una referencia a la lista grafica
        lstCanciones = findViewById(R.id.lstCancion);

        // Crear la lista de contactos y anadir algunos datos de prueba
        canciones = new ArrayList<Cancion>();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        lstCanciones.setLayoutManager(mLayoutManager);

        adaptador_canciones = new AdaptadorListaCancion(canciones);
        lstCanciones.setAdapter(adaptador_canciones);

        lstCanciones.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // Metodo que rellena el array con datos de prueba
        rellenarDatos();
    }

    private void rellenarDatos(){

        int max = gestor_cancion.getProfilesCount(ventanaCanciones.this);

        if(max != 0){

            for(int i = 1; i<= max; i++){

                String id_cancion = i+""+i+""+i;

                String parametro_id_album = gestor_cancion.buscarDatosCancion(ventanaCanciones.this, id_cancion,
                        "IdAlbumCancion");

                String parametro_id_cancion = gestor_cancion.buscarDatosCancion(ventanaCanciones.this, id_cancion,
                        "IdArtistaCancion");

                String parametro_nombre_cancion = gestor_cancion.buscarDatosCancion(ventanaCanciones.this, id_cancion,
                        "NombreCancion");

                String parametro_duracion_cancion = gestor_cancion.buscarDatosCancion(ventanaCanciones.this, id_cancion,
                        "DuracioCancion");

                String parametro_audio_cancion = gestor_cancion.buscarDatosCancion(ventanaCanciones.this, id_cancion,
                        "AudioCancion");

                Bitmap bitmap = gestor_cancion.buscarImagenCancion(ventanaCanciones.this, id_cancion,
                        "ImagenCancion");

                canciones.add(new Cancion(id_cancion, parametro_id_album, parametro_id_cancion, parametro_nombre_cancion,
                        parametro_duracion_cancion, parametro_audio_cancion, bitmap));

            }

        }

        else{
            canciones.add(new Cancion("No disponible","No disponible", "No disponible",
                    "No disponible","No disponible","No disponible",
                    null));
        }

    }

}