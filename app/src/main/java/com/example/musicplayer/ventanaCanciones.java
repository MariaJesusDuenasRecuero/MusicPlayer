package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.musicplayer.Adaptadores.AdaptadorListaCancion;
import com.example.musicplayer.Dominio.Cancion;

import java.util.ArrayList;

public class ventanaCanciones extends AppCompatActivity {

    private ArrayList<Cancion> canciones;
    private RecyclerView lstCanciones;
    private AdaptadorListaCancion adaptador_canciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_canciones);

        // Obtenemos una referencia a la lista grafica
        lstCanciones = findViewById(R.id.lstArtistas);

        // Crear la lista de contactos y anadir algunos datos de prueba
        canciones = new ArrayList<Cancion>();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        lstCanciones.setLayoutManager(mLayoutManager);

        adaptador_canciones = new AdaptadorListaCancion(canciones);
        lstCanciones.setAdapter(adaptador_canciones);

        // Metodo que rellena el array con datos de prueba
        rellenarDatos();
    }

    private void rellenarDatos(){

        canciones.add(new Cancion("a","45"));
        canciones.add(new Cancion("a","45"));
        canciones.add(new Cancion("a","45"));
        canciones.add(new Cancion("a","45"));
        canciones.add(new Cancion("a","45"));
        canciones.add(new Cancion("a","45"));
        canciones.add(new Cancion("a","45"));
        canciones.add(new Cancion("a","45"));


    }
}