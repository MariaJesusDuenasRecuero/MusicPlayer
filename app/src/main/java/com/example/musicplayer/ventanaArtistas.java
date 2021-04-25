package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.musicplayer.Adaptadores.AdaptadorListaArtista;
import com.example.musicplayer.Adaptadores.AdaptadorListaCancion;
import com.example.musicplayer.Dominio.Artista;
import com.example.musicplayer.Dominio.Cancion;
import com.example.musicplayer.Persistencia.ArtistaDAO;

import java.util.ArrayList;

public class ventanaArtistas extends AppCompatActivity {

    private ArrayList<Artista> artistas;
    private RecyclerView lstArtistas;
    private AdaptadorListaArtista adaptador_artistas;

    private ArtistaDAO gestor_artista = new ArtistaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_canciones);

        // Obtenemos una referencia a la lista grafica
        lstArtistas = findViewById(R.id.lstArtistas);

        // Crear la lista de contactos y anadir algunos datos de prueba
        artistas = new ArrayList<Artista>();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        lstArtistas.setLayoutManager(mLayoutManager);

        adaptador_artistas = new AdaptadorListaArtista(artistas);
        lstArtistas.setAdapter(adaptador_artistas);

        // Metodo que rellena el array con datos de prueba
        rellenarDatos();
    }

    private void rellenarDatos(){

        int max = 2;

        for(int i = 1; i<= max; i++){

            String j = i+"";
            String id_artista = String.valueOf((int)i);
            String parametro_nombre_artista = gestor_artista.buscarDatosArtista(ventanaArtistas.this, id_artista, "NombreArtista");
            String parametro_genero = gestor_artista.buscarDatosArtista(ventanaArtistas.this, id_artista, "Tipo");

            Log.d("Debug_Excepcion", parametro_nombre_artista+" "+parametro_genero);

            artistas.add(new Artista(id_artista, parametro_nombre_artista, parametro_genero));

        }
        /**
        artistas.add(new Artista("1", gestor_artista.buscarDatosArtista(ventanaArtistas.this, "1", "NombreArtista"),
                gestor_artista.buscarDatosArtista(ventanaArtistas.this, "1", "Tipo")));
         /*
        //artistas[0] = new Artista()

        //artistas.add(new Artista("a","POP"));
        */

    }
}