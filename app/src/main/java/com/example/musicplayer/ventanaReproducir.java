package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicplayer.Dominio.Cancion;
import com.example.musicplayer.Persistencia.CancionDAO;

public class ventanaReproducir extends AppCompatActivity {

    private Button btnReproducir;
    private Button btnDetener;

    private ImageView imageCover;
    private TextView nombre_cancion;

    private MediaPlayer media_player = new MediaPlayer();
    private CancionDAO gestor_cancion = new CancionDAO();

    private String identificador_cancion;

    //public Cancion[] cancion = new Cancion[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_reproducir);

        this.btnReproducir = (Button) findViewById(R.id.btnPlay);
        this.btnDetener = (Button) findViewById(R.id.btnDetener);
        this.imageCover = (ImageView) findViewById(R.id.imageViewCover);
        this.nombre_cancion = (TextView) findViewById(R.id.txtNombreCancionMusica);

        Bundle bundle = getIntent().getExtras();
        this.identificador_cancion = bundle.getString("identificador_cancion");


        Bitmap bitmap = gestor_cancion.buscarImagenCancion(ventanaReproducir.this, identificador_cancion,
                "ImagenCancion");

        String nombre_cancion_BBDD = gestor_cancion.buscarDatosCancion(ventanaReproducir.this,
                identificador_cancion, "NombreCancion");

        nombre_cancion.setText(nombre_cancion_BBDD);

        this.imageCover.setImageBitmap(bitmap);

        String codigo = gestor_cancion.buscarDatosCancion(ventanaReproducir.this, this.identificador_cancion,
                "AudioCancion");
        int codigo_bueno = Integer.parseInt(codigo);
        media_player = MediaPlayer.create(this, codigo_bueno);
        media_player.start();

        //IMPORTANTE
        /**
         Log.d("Debug_Excepcion", "INTEGER"+R.raw.avicii);

         String codigo = gestor_cancion.buscarDatosCancion(ventanaCanciones.this, "111","AudioCancion");
         int codigo_bueno = Integer.parseInt(codigo);
         MediaPlayer mp = MediaPlayer.create(this, codigo_bueno);
         mp.start();
         */
        /**IMPORTANTE
         *
         MediaPlayer mp = MediaPlayer.create(this, R.raw.avicii);
         mp.start();

         */
    }

    /*public void oyente_guardarCancion(View view) {


        String songID= view.getContentDescription().toString();

        Cancion cancion = cancionFavCollection.searchById(songID);
        favList.add(cancion);
        //Toast.makeText(this, "Canción añadida a favoritos", Toast.LENGTH_SHORT).show();

    }*/

}