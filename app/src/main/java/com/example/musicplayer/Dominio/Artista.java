package com.example.musicplayer.Dominio;

public class Artista {

    private String id_artista;
    private String nombre_artista;
    private String tipo;
    private byte [] imagen_artista;

    public Artista(String id_artista, String nombre_artista, String tipo){

        this.id_artista = id_artista;
        this.nombre_artista = nombre_artista;
        this.tipo = tipo;
        //this.imagen_artista = imagen_artista;

    }

    public String getIdArtista(){
        return this.id_artista;
    }

    public void setIdArtista(String id){
        this.id_artista = id;
    }

    public String getNombreArtista(){
        return this.nombre_artista;
    }

    public void setNombreArtista(String na){
        this.nombre_artista = na;
    }

    public String getTipo(){
        return this.tipo;
    }

    public void setTipo(String t){
        this.tipo = t;
    }

    public byte [] getImagenArtista(){
        return this.imagen_artista;
    }

    public void setImagenArtista(byte [] ia){
        this.imagen_artista = ia;
    }

}
