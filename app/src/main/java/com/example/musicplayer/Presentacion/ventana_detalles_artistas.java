package com.example.musicplayer.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicplayer.Persistencia.ArtistaDAO;
import com.example.musicplayer.R;

public class ventana_detalles_artistas extends AppCompatActivity {

    private ImageView image_artista;
    private TextView nombre_artista;
    private TextView descripcion_artista;
    private Toast notification;

    private String identificador_artista;

    private ArtistaDAO gestor_artista = new ArtistaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_artista);

        inicializarDatos();
        inicializarDatosBBDD();
        inicializarDescripciones();
        mostarNotificacion();

    }

    /**
     *
     * Descripcion: Metodo que permite inicializar los datos de la ventana detalles artistas
     *
     */
    private void inicializarDatos(){

        this.nombre_artista = findViewById(R.id.lblTituloAlbum);
        this.image_artista = findViewById(R.id.ivAlbum);
        this.descripcion_artista = findViewById(R.id.lblDescripcionAlbum);

        Bundle bundle = getIntent().getExtras();
        this.identificador_artista = bundle.getString("identificador_artista");

    }

    /**
     *
     * Descripcion: Metodo que inicializa los datos realizando consultas en la base de datos
     *
     */
    private void inicializarDatosBBDD(){

        this.image_artista.setImageBitmap(gestor_artista.buscarImagenArtista(ventana_detalles_artistas.this, this.identificador_artista,
                "ImagenArtista"));

        this.nombre_artista.setText(gestor_artista.buscarDatosArtista(ventana_detalles_artistas.this, this.identificador_artista,
                "NombreArtista"));

    }

    /**
     *
     * Descripcion: Metodo que inicializa las descripciones de cada artista
     *
     */
    private void inicializarDescripciones(){

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

    /**
     *
     * Descripcion: Metodo que muestra una notificacion
     *
     */
    private void mostarNotificacion(){

        notification = Toast.makeText(this, "Artista seleccionado: "
                + gestor_artista.buscarDatosArtista(ventana_detalles_artistas.this, this.identificador_artista,
                "NombreArtista"), Toast.LENGTH_LONG);
        notification.show();

    }
}