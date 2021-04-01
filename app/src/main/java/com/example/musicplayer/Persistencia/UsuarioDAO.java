package com.example.musicplayer.Persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.musicplayer.Dominio.Usuario;

public class UsuarioDAO {


    /**
     *
     * Descipcion: Sirve para obtener la conexion
     *
     * @param context
     * @return db
     */
    public SQLiteDatabase getConn(Context context) {

        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(context, "dbProyectoGSI", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        return db;

    }

    /**
     *
     * Descripcion: Metodo para insertar un usuario registrado en la base de datos de la aplicacion
     *
     * @param context
     * @param usuario
     * @return
     */
    public int insertarUsuario(Context context, Usuario usuario){

        int resultado_consulta = 0;
        String consulta_sql = "INSERT INTO Usuarios (Id, Nombre, Correo) VALUES ('"+usuario.getIdUsuario()+"', '"+usuario.getNombre()+"', '"+usuario.getCorreo()+"')";

        SQLiteDatabase db = this.getConn(context);

        try{

            db.execSQL(consulta_sql);
            resultado_consulta = 1;

        } catch (Exception e) {
            //TODO
        }

        return  resultado_consulta;
    }
}
