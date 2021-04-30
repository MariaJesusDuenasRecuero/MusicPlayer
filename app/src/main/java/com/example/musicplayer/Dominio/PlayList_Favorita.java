package com.example.musicplayer.Dominio;

import android.graphics.Bitmap;

public class PlayList_Favorita {

    private String id_play_list;
    private String id_cancion;
    private String id_usuario;

    /**
     *
     * Descripcion: Constructor con los parametros que debe tener cada playlist favoritos en la aplicacion
     *
     * @param id_play_list
     * @param id_cancion
     * @param id_usuario
     */
    public PlayList_Favorita(String id_play_list, String id_cancion, String id_usuario){

        this.id_play_list = id_play_list;
        this.id_cancion = id_cancion;
        this.id_usuario = id_usuario;

    }

    /**
     *
     * @return identificador de la playlist en la aplicacion
     */
    public String getIdPlayList(){
        return id_play_list;
    }

    /**
     *
     * @param id_p modificacion y asignacion nuevo id a la playlist
     */
    public void setIdPlayList(String id_p){
        this.id_play_list = id_p;
    }

    /**
     *
     * @return identificador de la cancion de la playlist en la aplicacion
     */
    public String getIdCancion(){
        return id_cancion;
    }

    /**
     *
     * @param id_c modificacion y asignacion nuevo id de la cancion a la playlist
     */
    public void setIdCancion(String id_c){
        this.id_cancion = id_c;
    }

    /**
     *
     * @return identificador del usuario al que pertenece la playlist en la aplicacion
     */
    public String getIdUsuario(){
        return this.id_usuario;
    }

    /**
     *
     * @param id_u modificacion y asignacion nuevo id del usuario a la playlist
     */
    public void setIdUsuario(String id_u){
        this.id_usuario = id_u;
    }
}