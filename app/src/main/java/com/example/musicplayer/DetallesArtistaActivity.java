package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicplayer.Persistencia.ArtistaDAO;

public class DetallesArtistaActivity extends AppCompatActivity {

    private String identificador_artista;
    private Toast notification;

    private ImageView image_artista;
    private TextView nombre_artista;
    private TextView descripcion_artista;

    private ArtistaDAO gestor_artista = new ArtistaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalles_artista);

        Bundle bundle = getIntent().getExtras();
        this.identificador_artista = bundle.getString("identificador_artista");

        nombre_artista = findViewById(R.id.lblTituloAlbum);
        image_artista = findViewById(R.id.ivAlbum);
        descripcion_artista = findViewById(R.id.lblDescripcionAlbum);

        image_artista.setImageBitmap(gestor_artista.buscarImagenArtista(DetallesArtistaActivity.this, this.identificador_artista,
                "ImagenArtista"));

        nombre_artista.setText(gestor_artista.buscarDatosArtista(DetallesArtistaActivity.this, this.identificador_artista,
                "NombreArtista"));

        inicializarDatos();

        mostarNotificacion();

    }

    private void inicializarDatos(){
        switch(this.identificador_artista){
            case "1":
                this.descripcion_artista.setText("All Time Low es un grupo estadounidense de pop punk.\n" +
                        "Está conformado por Alex Gaskarth (voz y guitarra rítmica),\n" +
                        "Jack Barakat (guitarra), Zack Merrick (bajo) y Rian Dawson (batería).\n" +
                        "El nombre de la banda fue tomado de la letra de una canción\n" +
                        "de New Found Glory.");
                break;
            case "2":
                this.descripcion_artista.setText("Tim Bergling más conocido por su nombre artístico Avicii,\n" +
                        "fue un DJ y compositor sueco, especializado en\n" +
                        "programación de audio, remezcla y producción de discos.");
                break;
            case "3":
                this.descripcion_artista.setText("Rebbeca Marie Gómez conocida artísticamente como\n" +
                        "Becky G, es una cantante, compositora y actriz\n" +
                        "estadounidense.");
                break;
            case "4":
                this.descripcion_artista.setText("Edurne García Almagro más conocida como Edurne,\n" +
                        "es una cantante, compositora, modelo, actriz y\n" +
                        "presentadora española de televisión.");
                break;
            case "5":
                this.descripcion_artista.setText("Fifth Harmony fue un grupo musical femenino\n" +
                        "estadounidense que tuvo sus inicios en la segunda\n" +
                        "temporada del programa The X Factor en el 2012,\n" +
                        "donde obtuvieron el tercer lugar de la competencia.");
                break;
            case "6":
                this.descripcion_artista.setText("Harry Edward Styles es un cantante, compositor y\n" +
                        "actor británico.");
                break;
            case "7":
                this.descripcion_artista.setText("Ramón Melendi Espina, conocido artísticamente\n" +
                        "como Melendi, es un cantautor y\n" +
                        "compositor español de música pop y rumba.");
                break;
            case "8":
                this.descripcion_artista.setText("Queen es una banda británica de rock formada\n" +
                        "en 1970 en Londres por el cantante y pianista\n" +
                        "Freddie Mercury, el guitarrista Brian May,\n" +
                        "el baterista Roger Taylor y el bajista John Deacon.");
                break;
            case "9":
                this.descripcion_artista.setText("Miguel Rafael Martos Sánchez, más conocido como\n" +
                        "Raphael, es un cantante y actor español, reconocido\n" +
                        "por ser uno de los precursores de la balada romántica\n" +
                        "en España y en los países de habla hispana.");
                break;
            default:
                this.descripcion_artista.setText("Ninguna descripción disponible.");
        }
    }
    private void mostarNotificacion(){

        notification = Toast.makeText(this, "Artista seleccionado: "
                + gestor_artista.buscarDatosArtista(DetallesArtistaActivity.this, this.identificador_artista,
                "NombreArtista"), Toast.LENGTH_LONG);
        notification.show();

    }
}