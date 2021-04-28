package com.example.musicplayer.Dominio;

import android.graphics.Bitmap;

import java.sql.Struct;

public class Cancion {

    private String id_cancion;
    private String id_album;
    private String id_artista;
    private String nombre_cancion;
    private String duracion;
    private String audio_cancion;
    private Bitmap imagen_cancion;

    /**
     *
     * Descripcion: Constructor con los parametros que debe tener cada cancion en la aplicacion
     *
    */

    public Cancion(String id_cancion, String id_album, String id_artista, String nombre_cancion,
                   String duracion, String audio_cancion, Bitmap imagen_cancion){

        this.id_cancion = id_cancion;
        this.id_album = id_album;
        this.id_artista = id_artista;
        this.nombre_cancion = nombre_cancion;
        this.duracion = duracion;
        this.audio_cancion = audio_cancion;
        this.imagen_cancion = imagen_cancion;

    }

    /**
     *
     * @return identificador de la cancion en la aplicacion
     */
    public String getIdCancion(){
        return this.id_cancion;
    }

    /**
     *
     * @param id_c modificacion y asignacion nuevo id de la cancion
     */
    public void setIdCancion(String id_c){
        this.id_cancion = id_c;
    }

    /**
     *
     * @return identificador del album al que pertenece la cancion en la aplicacion
     */
    public String getIdAlbum(){
        return this.id_album;
    }

    /**
     *
     * @param id_al modificacion y asignacion nuevo id del album de la cancion
     */
    public void setIdAlbum(String id_al){
        this.id_album = id_al;
    }

    /**
     *
     * @return identificador del autor al que pertenece la cancion en la aplicacion
     */
    public String getIdArtista(){
        return this.id_artista;
    }

    /**
     *
     * @param id_ar modificacion y asignacion nuevo id del artista de la cancion
     */
    public void setIdArtista(String id_ar){
        this.id_artista = id_ar;
    }

    /**
     *
     * @return identificador del nombre de la cancion
     */
    public String getNombreCancion(){
        return this.nombre_cancion;
    }

    /**
     *
     * @param n modificacion y asignacion nuevo nombre de la cancion
     */
    public void setNombreCancion(String n){
        this.nombre_cancion = n;
    }

    /**
     *
     * @return duracion de la cancion
     */
    public String getDuracionCancion(){
        return this.duracion;
    }

    /**
     *
     * @param d modificacion y asignacion de la nueva duracion de la cancion
     */
    public void setDuracion(String d){
        this.duracion = d;
    }

    /**
     *
     * @return audio que tiene asociada una determinada cancion
     */
    public String getAudioCancion(){
        return this.audio_cancion;
    }

    /**
     *
     * @param ac modificacion y asignacion del nuevo audio de la cancion
     */
    public void setAudioCancion(String ac){
        this.audio_cancion = ac;
    }

    /**
     *
     * @return portada (imagen) que tiene asociada una determinada cancion
     */
    public Bitmap getImagenCancion(){
        return this.imagen_cancion;
    }

    /**
     *
     * @param ic modificacion y asignacion de la nueva imagen de la cancion
     */
    public void setImagenCancion(Bitmap ic){
        this.imagen_cancion = ic;
    }
}
