package com.example.musicplayer.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.musicplayer.Constantes.Constantes;
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

        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(context, "dbProyectoGSI", null, 6);
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
        SQLiteDatabase db = this.getConn(context);
        ContentValues values = new ContentValues();

        String insertar_usuario_sql = "INSERT INTO "+Constantes.NOMBRE_TABLA_USUARIO+
                " ("+Constantes.CAMPO_USUARIO_NOMBRE_USUARIO+", "+Constantes.CAMPO_USUARIO_NOMBRE+", "+Constantes.CAMPO_USUSARIO_PASSWORD+", "+Constantes.CAMPO_USUARIO_TELEFONO+", "+
                    Constantes.CAMPO_USUARIO_CORREO+", "+Constantes.CAMPO_USUARIO_FECHA_NACIMIENTO+") VALUES ('"+usuario.getNombreUsuario()+"', '"+usuario.getNombre()+"', '"+usuario.getPassword()+
                        "', '"+usuario.getTelefono()+"', '"+usuario.getCorreo()+"', '"+usuario.getFechaNacimiento()+"')";

        try{

            db.execSQL(insertar_usuario_sql);
            resultado_consulta = 1;

        } catch (Exception e) {
            Log.d("Debug_Excepcion","Se ha producido un error al realizar la consulta");
        }

        db.close();

        return  resultado_consulta;
    }

    public int eliminarUsuario(Context context, String nombre_usuario){

        int resultado_eliminar_usuario = -1;
        String consulta_sql = "DELETE FROM Usuarios WHERE NombreUsuario = ?";

        SQLiteDatabase db = this.getConn(context);

        try {

            db.execSQL(consulta_sql);
            resultado_eliminar_usuario = 1;

        } catch (Exception e) {
            Log.d("Debug_Excepcion", "Se ha producido un error al realizar la consulta");
        }

        return resultado_eliminar_usuario;
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
