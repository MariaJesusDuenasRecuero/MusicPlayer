package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicplayer.Persistencia.AlbumDAO;
import com.example.musicplayer.Persistencia.ArtistaDAO;

public class detallesAlbumes extends AppCompatActivity {

    private String identificador_album;
    private Toast notification;


    private AlbumDAO gestor_album = new AlbumDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalles_albumes);

        Bundle bundle = getIntent().getExtras();
        this.identificador_album = bundle.getString("identificador_album");

        mostarNotificacion();

    }
    private void mostarNotificacion(){

        notification = Toast.makeText(this, "Album seleccionado: "
                + gestor_album.buscarDatosArtista(detallesAlbumes.this, this.identificador_album,
                "NombreAlbum"), Toast.LENGTH_LONG);
        notification.show();

    }


}