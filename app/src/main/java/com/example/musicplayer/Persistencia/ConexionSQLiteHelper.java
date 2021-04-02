package com.example.musicplayer.Persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    final String CREAR_TABLA_USUARIO = "CREATE TABLE Usuarios (NombreUsuario TEXT, Nombre TEXT, Password TEXT, Telefono TEXT, CorreoElectronico TEXT, FechaNacimiento TEXT)";

        /**
         *
         * Descripcion: Al llamar a este constructor creamos nuestra base de datos.
         * Automaticamente llma al metodo onCreate()
         *
         * @param context Contexto de nuestra aplicacion
         * @param name Nombre de nuestra base de datos
         * @param factory
         * @param version Version de nuestra base de datos
         */
    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     *
     * Descripcion: Metodo encargado de la generacion de tablas
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIO);
    }
    /**
     *
     * Descripcion: Metodo encargado de verificar si existe una version antigua
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Si instalamos la aplicacion que borre la tabla antigua y la vuelva a generar
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        onCreate(db);

    }
}
