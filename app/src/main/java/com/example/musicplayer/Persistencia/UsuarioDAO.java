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
     * @param context ventana
     * @param usuario contiene los datos de los campos
     * @return resultado_resultado_consulta comprueba si la consulta se ha realizado correctamente
     */
    public int insertarUsuario(Context context, Usuario usuario){

        int resultado_consulta = -1;
        SQLiteDatabase db = this.getConn(context);

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

    /**
     *
     * Descripcion: Metodo que permite eliminar un usuario de la base de datos
     *
     * @param context ventana
     * @param nombre_usuario clave primaria
     * @return resultado_resultado_consulta comprueba si la consulta se ha realizado correctamente
     */
    public int eliminarUsuario(Context context, String nombre_usuario){

        int resultado_consulta = -1;
        SQLiteDatabase db = this.getConn(context);

        String eliminar_usuario_sql = "DELETE FROM "+Constantes.NOMBRE_TABLA_USUARIO+" WHERE NombreUsuario='"+nombre_usuario+"'";

        try {

            db.execSQL(eliminar_usuario_sql);
            resultado_consulta = 1;

        } catch (Exception e) {
            Log.d("Debug_Excepcion", "Se ha producido un error al realizar la consulta");
        }

        db.close();

        return resultado_consulta;
    }

    /**
     *
     * Descripcion: Metodo que permite modificar los campos de la tabla Usuario en la base de datos
     *
     * @param context ventana
     * @param nombre_usuario nombre del usuario del que se quiere modificar el parametro (clave primaria=
     * @param nombre_campo_tabla nombre del campo de la tabla que se quiere modificar
     * @param parametro_nuevo nuevo valor para ese campo
     * @return resultado_resultado_consulta comprueba si la consulta se ha realizado correctamente
     */
    public int updateParametroUsuario(Context context, String nombre_usuario, String nombre_campo_tabla, String parametro_nuevo){

        int resultado_consulta = -1;
        SQLiteDatabase db = this.getConn(context);

        String update_usuario_sql = "UPDATE "+Constantes.NOMBRE_TABLA_USUARIO+" SET "+nombre_campo_tabla+" = '"+parametro_nuevo+"' WHERE "+Constantes.CAMPO_USUARIO_NOMBRE_USUARIO+"= '"+nombre_usuario+"'";

        try {

            db.execSQL(update_usuario_sql);
            resultado_consulta = 1;

        } catch (Exception e) {
            Log.d("Debug_Excepcion", "Se ha producido un error al realizar la consulta");
        }

        db.close();

        return resultado_consulta;
    }

    //TODO buscar usuarioRegistrado
    //TODO buscar datoUsuario
    //TODO metodo iniciar sesion Sistema
    //TODO Revisar borrar tabla
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
