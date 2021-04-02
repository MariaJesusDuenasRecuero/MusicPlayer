package com.example.musicplayer.Dominio;

public class Usuario {

    private String nombre_usuario; //Identificador unico clave primaria
    private String nombre;
    private String password;
    private String telefono;
    private String correo_electronico;
    private String fecha_nacimiento;

    /**
     *
     * Descripcion: Constructor con los parametros que debe tener cada usuario de la aplicacion
     *
     * @param nombre_usuario
     * @param nombre
     * @param password
     * @param telefono
     * @param correo_electronico
     * @param fecha_nacimiento
     */
    public Usuario(String nombre_usuario, String nombre, String password, String telefono,
                   String correo_electronico, String fecha_nacimiento){

        this.nombre_usuario = nombre_usuario;
        this.nombre = nombre;
        this.password = password;
        this.telefono = telefono;
        this.correo_electronico = correo_electronico;
        this.fecha_nacimiento = fecha_nacimiento;

    }

    /**
     *
     * @return nombre de usuario en la aplicacion
     */
    public String getNombreUsuario(){
        return this.nombre_usuario;
    }

    /**
     *
     * @param nu modificacion y asignacion nuevo nombre de usuario en la aplicacion
     */
    public void setNombreUsuario(String nu){
        this.nombre_usuario = nu;
    }

    /**
     *
     * @return nombre del usuario
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     *
     * @param n modificacion y asignacion del parametro nombre
     */
    public void setNombre(String n){
        this.nombre = n;
    }

    /**
     *
     * @return contrasena del usuario
     */
    public String getPassword(){
        return this.password;
    }

    /**
     *
     * @param pss modificacion y asignacion del parametro contrasena
     */
    public void setPassword(String pss){
        this.password = pss;
    }

    /**
     *
     * @return telefono del usuario
     */
    public String getTelefono(){
        return this.telefono;
    }

    /**
     *
     * @param t modificacion y asignacion del parametro telefono
     */
    public void setTelefono(String t){
        this.telefono = t;
    }

    /**
     *
     * @return correo electronico del usuario
     */
    public String getCorreo(){
        return this.correo_electronico;
    }

    /**
     *
     * @param c modificacion y asignacion del parametro correo electronico
     */
    public void setCorreo(String c){
        this.correo_electronico = c;
    }

    /**
     *
     * @return fecha de nacimiento del usuario
     */
    public String getFechaNacimiento(){
        return this.fecha_nacimiento;
    }

    /**
     *
     * @param fn modificacion y asignacion del parametro fecha nacimiento
     */
    public void setFechaNacimiento(String fn){
        this.fecha_nacimiento = fn;
    }

}
