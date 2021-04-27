package com.example.musicplayer.Dominio;

import android.graphics.Bitmap;

public class Album {

    private String id_album;
    private String id_artista;
    private String nombre_album;
    private String duracion_album;
    private Bitmap imagen_album;

    /**
     *
     * Descripcion: Constructor con los parametros que debe tener cada album en la aplicacion
     *
     * @param id_album
     * @param id_artista
     * @param nombre_album
     * @param duracion_album
     * @param imagen_album
     */
    public Album(String id_album, String id_artista, String nombre_album, String duracion_album,
                 Bitmap imagen_album){

        this.id_album = id_album;
        this.id_artista = id_artista;
        this.nombre_album = nombre_album;
        this.duracion_album = duracion_album;
        this.imagen_album = imagen_album;

    }

    /**
     *
     * @return identificador del album en la aplicacion
     */
    public String getIdAlbum(){
        return this.id_album;
    }

    /**
     *
     * @param id modificacion y asignacion nuevo id al album
     */
    public void setIdAlbum(String id){
        this.id_album = id;
    }

    /**
     *
     * @return identificador del artista del album
     */
    public String getIdArtista(){
        return this.id_artista;
    }

    /**
     *
     * @param id modificacion y asignacion nuevo id al artista del album
     */
    public void setIdArtista(String id){
        this.id_artista = id;
    }

    /**
     *
     * @return nombre del album
     */
    public String getNombreAlbum(){
        return this.nombre_album;
    }

    /**
     *
     * @param nuevo_nombre modificacion y asignacion nuevo nombre del album
     */
    public void setNombreAlbum(String nuevo_nombre){
        this.nombre_album = nuevo_nombre;
    }

    /**
     *
     * @return duracion del album
     */
    public String getDuracionAlbum(){
        return this.duracion_album;
    }

    /**
     *
     * @param d modificacion y asignacion de la nueva duracion
     */
    public void setDuracionAlbum(String d){
        this.duracion_album = d;
    }

    /**
     *
     * @return imagen del album
     */
    public Bitmap getImagenAlbum(){

        return this.imagen_album;
    }

    /**
     *
     * @param ia modificacion y asignacion de la nueva imagen del album
     */
    public void setImagenAlbum(Bitmap ia){
        this.imagen_album = ia;
    }

}
