package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.musicplayer.Adaptadores.AdaptadorListaAlbum;
import com.example.musicplayer.Dominio.Album;
import com.example.musicplayer.Persistencia.AlbumDAO;

import java.util.ArrayList;

public class ventana_album extends AppCompatActivity {

    private ArrayList<Album> albumes;
    private RecyclerView lstAlbum;
    private AdaptadorListaAlbum adaptador_albumes;

    private AlbumDAO gestor_album = new AlbumDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        // Obtenemos una referencia a la lista grafica
        lstAlbum = findViewById(R.id.lstPlayList);


        // Crear la lista de contactos y anadir algunos datos de prueba
        albumes = new ArrayList<Album>();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        lstAlbum.setLayoutManager(mLayoutManager);

        adaptador_albumes = new AdaptadorListaAlbum(albumes);
        lstAlbum.setAdapter(adaptador_albumes);

        lstAlbum.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // Metodo que rellena el array con datos de prueba
        rellenarDatos();
    }

    private void rellenarDatos(){

        int max = gestor_album.getProfilesCountAlbum(ventana_album.this);

        if(max != 0){

            for(int i = 1; i<= max; i++){

                String j = i+""+i;
                String id_album = i+""+i;
                String parametro_nombre_album = gestor_album.buscarDatosArtista(ventana_album.this, id_album, "NombreAlbum");
                String parametro_id_artista = gestor_album.buscarDatosArtista(ventana_album.this, id_album, "idAutor");
                String parametro_duracion_album = gestor_album.buscarDatosArtista(ventana_album.this, id_album, "DuracionAlbum");
                Bitmap bitmap =  gestor_album.buscarImagenAlbum(ventana_album.this, id_album,"ImagenAlbum");

                albumes.add(new Album(id_album, parametro_id_artista, parametro_nombre_album, parametro_duracion_album, bitmap));

            }

        }

        else{
            albumes.add(new Album("No disponible","No disponible", "No disponible","No disponible", null));
        }
    }
}