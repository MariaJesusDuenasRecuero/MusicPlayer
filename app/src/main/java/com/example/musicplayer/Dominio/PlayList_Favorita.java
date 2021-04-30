package com.example.musicplayer.Dominio;

import android.graphics.Bitmap;

public class PlayList_Favorita {
    private String id_FavCancion;
    private String id_album;
    private String id_artista;
    private String nombre_FavCancion;
    private String duracion;
    private String audio_FavCancion;

    public PlayList_Favorita(String id_FavCancion, String id_album, String id_artista, String nombre_FavCancion, String duracion, String audio_FavCancion, Bitmap imagen_FavCancion) {
        this.id_FavCancion = id_FavCancion;
        this.id_album = id_album;
        this.id_artista = id_artista;
        this.nombre_FavCancion = nombre_FavCancion;
        this.duracion = duracion;
        this.audio_FavCancion = audio_FavCancion;
        this.imagen_FavCancion = imagen_FavCancion;
    }

    public String getId_FavCancion() {
        return id_FavCancion;
    }

    public void setId_FavCancion(String id_FavCancion) {
        this.id_FavCancion = id_FavCancion;
    }

    public String getId_album() {
        return id_album;
    }

    public void setId_album(String id_album) {
        this.id_album = id_album;
    }

    public String getId_artista() {
        return id_artista;
    }

    public void setId_artista(String id_artista) {
        this.id_artista = id_artista;
    }

    public String getNombre_FavCancion() {
        return nombre_FavCancion;
    }

    public void setNombre_FavCancion(String nombre_FavCancion) {
        this.nombre_FavCancion = nombre_FavCancion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getAudio_FavCancion() {
        return audio_FavCancion;
    }

    public void setAudio_FavCancion(String audio_FavCancion) {
        this.audio_FavCancion = audio_FavCancion;
    }

    public Bitmap getImagen_FavCancion() {
        return imagen_FavCancion;
    }

    public void setImagen_FavCancion(Bitmap imagen_FavCancion) {
        this.imagen_FavCancion = imagen_FavCancion;
    }

    private Bitmap imagen_FavCancion;

    /**
     *
     * Descripcion: Constructor con los parametros que debe tener cada cancion en la aplicacion
     *
     */

}