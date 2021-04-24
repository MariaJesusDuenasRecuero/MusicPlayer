package com.example.musicplayer.Dominio;

public class Cancion {

    private String nombre;
    private String duracion;

    public Cancion(String nombre, String duracion){

        this.nombre = nombre;
        this.duracion = duracion;

    }

    public String getNombreCancion(){
        return this.nombre;
    }

    public String getDuracionCancion(){
        return this.duracion;
    }

}
