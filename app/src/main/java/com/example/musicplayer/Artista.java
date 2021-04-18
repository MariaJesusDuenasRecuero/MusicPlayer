package com.example.musicplayer;

import android.widget.ImageView;

public class Artista {
    private String nombre;
    private String descripcion;
    private int  ImagenId;

    public Artista(String nombre, String descripcion, int imageId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        ImagenId = imageId;
    }

    public Artista() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagenId() {
        return ImagenId;
    }

    public void setImageId(int imagenId) {
        ImagenId = imagenId;
    }
}
