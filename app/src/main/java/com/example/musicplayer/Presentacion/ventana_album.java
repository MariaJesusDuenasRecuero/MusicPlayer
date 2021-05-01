package com.example.musicplayer.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.musicplayer.Adaptadores.AdaptadorListaAlbum;
import com.example.musicplayer.Dominio.Album;
import com.example.musicplayer.Persistencia.AlbumDAO;
import com.example.musicplayer.R;

import java.util.ArrayList;

public class ventana_album extends AppCompatActivity {

    private ArrayList<Album> albumes;
    private RecyclerView lstAlbum;
    private AdaptadorListaAlbum adaptador_albumes;

    private String [] id_albumes;
    private AlbumDAO gestor_album = new AlbumDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        inicializarDatos();
        rellenarDatos();

    }

    /**
     *
     * Descripcion: Obtenemos una referencia a la lista grafica y se crea
     * la lista de contactos y anadir algunos datos de prueba
     *
     */
    private void inicializarDatos(){

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        this.lstAlbum = findViewById(R.id.lstPlayList);

        this.albumes = new ArrayList<Album>();
        this.lstAlbum.setLayoutManager(mLayoutManager);
        this.adaptador_albumes = new AdaptadorListaAlbum(albumes);
        this.lstAlbum.setAdapter(adaptador_albumes);
        this.lstAlbum.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    }

    /**
     *
     * Descripcion: Metodo que permite inicializar la lista dependiendo de los datos obtenidos de la
     * consulta
     *
     */
    private void rellenarDatos(){

        int numero_albumes_sistema = obtenerNumeroAlbumes();

        if(numero_albumes_sistema != 0){

            this.id_albumes = gestor_album.getListaAlbumes(ventana_album.this,
                    numero_albumes_sistema);

            for(int i = 0; i < this.id_albumes.length; i++){

                String parametro_nombre_album = gestor_album.buscarDatosArtista(ventana_album.this,
                        this.id_albumes[i], "NombreAlbum");

                String parametro_id_artista = gestor_album.buscarDatosArtista(ventana_album.this,
                        this.id_albumes[i], "idAutor");

                String parametro_duracion_album = gestor_album.buscarDatosArtista(ventana_album.this,
                        this.id_albumes[i], "DuracionAlbum");

                Bitmap bitmap =  gestor_album.buscarImagenAlbum(ventana_album.this,
                        this.id_albumes[i],"ImagenAlbum");

                albumes.add(new Album(this.id_albumes[i], parametro_id_artista, parametro_nombre_album,
                        parametro_duracion_album, bitmap));

            }
        }
        else{

            albumes.add(new Album("No disponible","No disponible",
                    "No disponible","No disponible", null));

        }
    }

    /**
     *
     * Descripcion: Metodo que permite obtener el nuemero de albumes totales en la aplicacion
     *
     * @return un entero con el numero de albumes en la aplicacion
     */
    private int obtenerNumeroAlbumes(){

        return gestor_album.getNumeroTotalAlbumes(ventana_album.this);

    }
}