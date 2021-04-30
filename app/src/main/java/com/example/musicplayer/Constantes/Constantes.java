package com.example.musicplayer.Constantes;

import android.graphics.Bitmap;

/**
 *
 * Descripcion: Clase con las constante que representran los campos o registro de las tablas de la base de datos
 *
 */
public class Constantes {

    //Constantes campos tabla USUARIOS (7)

    public static final String NOMBRE_TABLA_USUARIO_SISTEMA = "Usuario";

    public static final String CAMPO_USUARIO_NOMBRE_USUARIO = "NombreUsuario";
    public static final String CAMPO_USUARIO_NOMBRE = "Nombre";
    public static final String CAMPO_USUSARIO_PASSWORD = "Password";
    public static final String CAMPO_USUARIO_TELEFONO = "Telefono";
    public static final String CAMPO_USUARIO_CORREO = "CorreoElectronico";
    public static final String CAMPO_USUARIO_FECHA_NACIMIENTO = "FechaNacimiento";
    public static final String CAMPO_USUARIO_NOMBRE_IMAGEN = "ImagenPerfil";

    //Constantes campos tabla ARTISTA (4)

    public static final String NOMBRE_TABLA_ARTISTA = "Artista";

    public static final String CAMPO_ARTISTA_ID = "IdArtista";
    public static final String CAMPO_ARTISTA_NOMBRE = "NombreArtista";
    public static final String CAMPO_ARTISTA_TIPO = "Tipo";
    public static final String CAMPO_USUARIO_IMAGEN_ARTISTA = "ImagenArtista";

    //Constantes campos tabla ALBUM (5)

    public static final String NOMBRE_TABLA_ALBUM = "Album";

    public static final String CAMPO_ALBUM_ID = "IdAlbum";
    public static final String CAMPO_ALBUM_ARTISTA_ID = "idAutor";
    public static final String CAMPO_ALBUM_NOMBRE = "NombreAlbum";
    public static final String CAMPO_ALBUM_DURACION = "DuracionAlbum";
    public static final String CAMPO_ALBUM_IMAGEN_PORTADA = "ImagenAlbum";

    //Constantes campos tabla CANCION (7)

    public static final String NOMBRE_TABLA_CANCION = "Cancion";

    public static final String CAMPO_CANCION_ID = "IdCancion";
    public static final String CAMPO_CANCION_ALBUM_ID = "IdAlbumCancion";
    public static final String CAMPO_CANCION_ARTISTA_ID = "IdArtistaCancion";
    public static final String CAMPO_CANCION_NOMBRE = "NombreCancion";
    public static final String CAMPO_CANCION_DURACION = "DuracioCancion";
    public static final String CAMPO_CANCION_IMAGEN = "ImagenCancion";
    public static final String CAMPO_CANCION_AUDIO = "AudioCancion";

    //Constantes campos tabla PlayListFavorita (3)

    public static final String NOMBRE_TABLA_PLAYLIST = "PlayListFavorita";

    public static final String CAMPO_PLAYLIST_ID = "IdCancionFavorita"; //Siempre sera 1 si el sistema tuviese mas playlist autoincremental
    public static final String CAMPO_PLAYLIST_NOMBRE_USUARIO ="PlayListNombreUsuario";
    public static final String CAMPO_PLAYLIST_ID_CANCION = "IdCancionPlayList";


    //Constantes TABLAS

    public static final String CREAR_TABLA_USUARIO_SISTEMA =
            "CREATE TABLE "+NOMBRE_TABLA_USUARIO_SISTEMA+" ("+CAMPO_USUARIO_NOMBRE_USUARIO+" TEXT, "+CAMPO_USUARIO_NOMBRE+" TEXT, "+CAMPO_USUSARIO_PASSWORD+
                    " TEXT, "+CAMPO_USUARIO_TELEFONO+" TEXT, "+CAMPO_USUARIO_CORREO+" TEXT, "+CAMPO_USUARIO_FECHA_NACIMIENTO+" TEXT, "+
                    CAMPO_USUARIO_NOMBRE_IMAGEN+" BLOB)";

    public static final String CREAR_TABLA_ARTISTA =
            "CREATE TABLE "+NOMBRE_TABLA_ARTISTA+" ("+CAMPO_ARTISTA_ID+" TEXT, "+CAMPO_ARTISTA_NOMBRE+" TEXT, "+CAMPO_ARTISTA_TIPO+" TEXT, "+
                    CAMPO_USUARIO_IMAGEN_ARTISTA+" BLOB)";

    public static final String CREAR_TABLA_ALBUM =
            "CREATE TABLE "+NOMBRE_TABLA_ALBUM+" ("+CAMPO_ALBUM_ID+" TEXT, "+CAMPO_ALBUM_ARTISTA_ID+" TEXT, "+CAMPO_ALBUM_NOMBRE+" TEXT, "
                    +CAMPO_ALBUM_DURACION+" TEXT, "+ CAMPO_ALBUM_IMAGEN_PORTADA+" BLOB)";

    public static final String CREAR_TABLA_CANCION =
            "CREATE TABLE "+NOMBRE_TABLA_CANCION+" ("+CAMPO_CANCION_ID+" TEXT, "+CAMPO_CANCION_ALBUM_ID+" TEXT, "+CAMPO_CANCION_ARTISTA_ID+" TEXT, "
                    +CAMPO_CANCION_NOMBRE+" TEXT, "+CAMPO_CANCION_DURACION+" TEXT, "+CAMPO_CANCION_AUDIO+" TEXT, "
                    +CAMPO_CANCION_IMAGEN+" BLOB)";

    public static final String CREAR_TABLA_PLAYLIST =
            "CREATE TABLE "+NOMBRE_TABLA_PLAYLIST+" ("+CAMPO_PLAYLIST_ID+" TEXT, "+CAMPO_PLAYLIST_NOMBRE_USUARIO+" TEXT, "
                    + CAMPO_PLAYLIST_ID_CANCION+" TEXT)";

}
