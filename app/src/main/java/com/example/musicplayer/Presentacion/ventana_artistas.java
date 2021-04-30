package com.example.musicplayer.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.musicplayer.Adaptadores.AdaptadorListaArtista;
import com.example.musicplayer.Dominio.Artista;
import com.example.musicplayer.Persistencia.ArtistaDAO;
import com.example.musicplayer.R;

import java.util.ArrayList;

public class ventana_artistas extends AppCompatActivity {

    private ArrayList<Artista> artistas;
    private RecyclerView lstArtistas;
    private AdaptadorListaArtista adaptador_artistas;

    private String [] id_artistas;
    private ArtistaDAO gestor_artista = new ArtistaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_artistas);

        inicializarAdaptador();
        rellenarDatos();

    }

    /**
     *
     * Descripcion: Obtenemos una referencia a la lista grafica y se crea
     * la lista de contactos y anadir algunos datos de prueba
     *
     */
    private void inicializarAdaptador(){

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        this.lstArtistas = findViewById(R.id.lstPlayList);

        this.artistas = new ArrayList<Artista>();
        this.lstArtistas.setLayoutManager(mLayoutManager);
        this.adaptador_artistas = new AdaptadorListaArtista(artistas);
        this.lstArtistas.setAdapter(adaptador_artistas);
        this.lstArtistas.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    }

    /**
     *
     * Descripcion: Metodo que permite inicializar la lista dependiendo de los datos obtenidos de la
     * consulta
     *
     */
    private void rellenarDatos(){

        int numero_artistas_sistema = obtenerNumeroArtistas();

        if(numero_artistas_sistema != 0){

            this.id_artistas = gestor_artista.getListaArtistas(ventana_artistas.this,
                    numero_artistas_sistema);

            for(int i = 0; i < this.id_artistas.length; i++){

                String parametro_nombre_artista = gestor_artista.buscarDatosArtista(ventana_artistas.this,
                        this.id_artistas[i], "NombreArtista");

                String parametro_genero = gestor_artista.buscarDatosArtista(ventana_artistas.this,
                        this.id_artistas[i], "Tipo");

                Bitmap bitmap =  gestor_artista.buscarImagenArtista(ventana_artistas.this,
                        this.id_artistas[i],"ImagenArtista");

                artistas.add(new Artista(this.id_artistas[i], parametro_nombre_artista, parametro_genero, bitmap));

            }

        }
        else{

            artistas.add(new Artista("No disponible", "No disponible",
                    "No disponible", null));

        }

    }

    /**
     *
     * Descripcion: Metodo que permite obtener el nuemero de artistas totales en la aplicacion
     *
     * @return un entero con el numero de canciones del usuario en la aplicacion
     */
    private int obtenerNumeroArtistas(){

        return this.gestor_artista.getNumeroTotalArtistas(ventana_artistas.this);

    }

}