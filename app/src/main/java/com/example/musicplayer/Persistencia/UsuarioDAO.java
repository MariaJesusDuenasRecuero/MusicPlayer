package com.example.musicplayer.Persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

        //Cada vez que se borre una tabla se cambia la version

        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(context, "dbProyectoGSI", null, 5);
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

        String consulta_sql = "INSERT INTO Usuarios (NombreUsuario, Nombre, Password, Telefono, CorreoElectronico, FechaNacimiento) VALUES ('"+usuario.getNombreUsuario()+"', '"+usuario.getNombre()+"', '"+usuario.getPassword()+"', '"+usuario.getTelefono()+"', '"+usuario.getCorreo()+"', '"+usuario.getFechaNacimiento()+"')";

        SQLiteDatabase db = this.getConn(context);

        try{

            db.execSQL(consulta_sql);
            resultado_consulta = 1;

        } catch (Exception e) {
            Log.d("Debug_Excepcion","Se ha producido un error al realizar la consulta");
        }

        return  resultado_consulta;
    }

    /**
     *
     * Descripcion: Metodo que permite borrar la tabla de usuarios entera
     *
     * @param context
     * @return
     */
    public int borrarTabla(Context context){

        int resultado_consulta = 0;

        String consulta_sql = "DROP TABLE Usuarios";
        SQLiteDatabase db = this.getConn(context);

        try{

            db.execSQL(consulta_sql);
            resultado_consulta = 1;

        } catch (Exception e) {
            Log.d("Debug_Excepcion","Se ha producido un error al realizar la consulta");
        }

        return resultado_consulta;
    }
}
