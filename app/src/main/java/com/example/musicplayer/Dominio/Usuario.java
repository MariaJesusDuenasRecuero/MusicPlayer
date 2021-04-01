package com.example.musicplayer.Dominio;

public class Usuario {

    private String id;
    private String nombre_usuario; //Identificador unico clave primaria
    private String nombre;
    //private String password;
    //private String telefono;
    private String correo_electronico;
    //private String fecha_nacimiento;

    public Usuario(String id, String nombre, String correo_electronico) {

        this.id = id;
        this.nombre_usuario = nombre_usuario;
        this.nombre = nombre;
        //this.password = password;
        //this.telefono = telefono;
        this.correo_electronico = correo_electronico;
        //this.fecha_nacimiento = fecha_nacimiento;

    }
    public String getIdUsuario(){
        return this.id;
    }

    public void setIdUsuario(String id){
        this.id = id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String n){
        this.nombre = n;
    }

    public String getCorreo(){
        return this.correo_electronico;
    }

    public void setCorreo(String c){
        this.correo_electronico = c;
    }

    public String getNombreUsuario(){
        return this.nombre_usuario;
    }

    public void setNombreUsuario(String nu){
        this.nombre_usuario = nu;
    }

}
