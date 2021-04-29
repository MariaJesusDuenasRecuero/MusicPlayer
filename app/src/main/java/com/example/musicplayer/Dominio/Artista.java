package com.example.musicplayer.Dominio;

import android.graphics.Bitmap;

public class Artista {

    private String id_artista;
    private String nombre_artista;
    private String tipo;
    private Bitmap imagen_artista;

    /**
     *
     * Descripcion: Constructor con los parametros que debe tener cada artista en la aplicacion
     *
     * @param id_artista
     * @param nombre_artista
     * @param tipo
     * @param imagen_artista
     */
    public Artista(String id_artista, String nombre_artista, String tipo, Bitmap imagen_artista){

        this.id_artista = id_artista;
        this.nombre_artista = nombre_artista;
        this.tipo = tipo;
        this.imagen_artista = imagen_artista;

    }

    /**
     *
     * @return identificador del artista en la aplicacion
     */
    public String getIdArtista(){
        return this.id_artista;
    }

    /**
     *
     * @param id modificacion y asignacion nuevo id al artista
     */
    public void setIdArtista(String id){
        this.id_artista = id;
    }

    /**
     *
     * @return identificador del nombre del artita en la aplicacion
     */
    public String getNombreArtista(){
        return this.nombre_artista;
    }

    /**
     *
     * @param na modificacion y asignacion nuevo nombre al artista
     */
    public void setNombreArtista(String na){
        this.nombre_artista = na;
    }

    /**
     *
     * @return identificador del tipo de canciones que realiza el artista en la aplicacion
     */
    public String getTipo(){
        return this.tipo;
    }

    /**
     *
     * @param t modificacion y asignacion del tipo de canciones que realiza el artista
     */
    public void setTipo(String t){
        this.tipo = t;
    }

    /**
     *
     * @return imagen del artista en la aplicacion
     */
    public Bitmap getImagenArtista(){
        return this.imagen_artista;
    }

    /**
     *
     * @param ia modificacion y asignacion de la nueva imagen del artista
     */
    public void setImagenArtista(Bitmap ia){
        this.imagen_artista = ia;
    }

}
