package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.musicplayer.Adaptadores.AdaptadorListaArtista;
import com.example.musicplayer.Dominio.Artista;
import com.example.musicplayer.Persistencia.ArtistaDAO;

import java.util.ArrayList;

public class ventana_artistas extends AppCompatActivity {

    private ArrayList<Artista> artistas;
    private RecyclerView lstArtistas;
    private AdaptadorListaArtista adaptador_artistas;

    private ArtistaDAO gestor_artista = new ArtistaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_artistas);

        // Obtenemos una referencia a la lista grafica
        lstArtistas = findViewById(R.id.lstCancion);


        // Crear la lista de contactos y anadir algunos datos de prueba
        artistas = new ArrayList<Artista>();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        lstArtistas.setLayoutManager(mLayoutManager);

        adaptador_artistas = new AdaptadorListaArtista(artistas);
        lstArtistas.setAdapter(adaptador_artistas);

        lstArtistas.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // Metodo que rellena el array con datos de prueba
        rellenarDatos();
    }

    private void rellenarDatos(){

        int max = gestor_artista.getProfilesCount(ventana_artistas.this);

        if(max != 0){

            for(int i = 1; i<= max; i++){

                String j = i+"";
                String id_artista = String.valueOf((int)i);
                String parametro_nombre_artista = gestor_artista.buscarDatosArtista(ventana_artistas.this, id_artista, "NombreArtista");
                String parametro_genero = gestor_artista.buscarDatosArtista(ventana_artistas.this, id_artista, "Tipo");
                Bitmap bitmap =  gestor_artista.buscarImagenArtista(ventana_artistas.this, id_artista,"ImagenArtista");

                artistas.add(new Artista(id_artista, parametro_nombre_artista, parametro_genero, bitmap));

            }

        }
        else{
            artistas.add(new Artista("No disponible", "No disponible", "No disponible", null));
        }

    }
}