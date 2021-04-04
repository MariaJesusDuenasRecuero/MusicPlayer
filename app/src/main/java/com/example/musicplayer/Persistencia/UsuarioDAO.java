package com.example.musicplayer.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.musicplayer.Constantes.Constantes;
import com.example.musicplayer.Dominio.Usuario;

public class UsuarioDAO {

    /**
     *
     * Descipcion: Sirve para obtener la conexion (escritura)
     *
     * @param context
     * @return db
     */
    public SQLiteDatabase getConnWrite(Context context) {

        //Cada vez que se borre una tabla se cambia la version

        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(context, "dbProyectoGSI", null, 6);
        SQLiteDatabase db = conexion.getWritableDatabase();

        return db;

    }

    /**
     *
     * Descipcion: Sirve para obtener la conexion (lectura)
     *
     * @param context
     * @return db
     */
    public SQLiteDatabase getConnRead(Context context) {

        //Cada vez que se borre una tabla se cambia la version

        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(context, "dbProyectoGSI", null, 6);
        SQLiteDatabase db = conexion.getReadableDatabase();

        return db;

    }

    /**
     *
     * Descripcion: Metodo para insertar un usuario registrado en la base de datos de la aplicacion
     *
     * @param context ventana
     * @param usuario contiene los datos de los campos
     * @return resultado_consulta comprueba si la consulta se ha realizado correctamente
     */
    public int insertarUsuario(Context context, Usuario usuario){

        int resultado_consulta = -1;
        SQLiteDatabase db = this.getConnWrite(context);

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
     * @return resultado_consulta comprueba si la consulta se ha realizado correctamente
     */
    public int eliminarUsuario(Context context, String nombre_usuario){

        int resultado_consulta = -1;
        SQLiteDatabase db = this.getConnWrite(context);

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
     * @param nombre_usuario nombre del usuario del que se quiere modificar el parametro (clave primaria)
     * @param nombre_campo_tabla nombre del campo de la tabla que se quiere modificar
     * @param parametro_nuevo nuevo valor para ese campo
     * @return resultado_consulta comprueba si la consulta se ha realizado correctamente
     */
    public int updateParametroUsuario(Context context, String nombre_usuario, String nombre_campo_tabla, String parametro_nuevo){

        int resultado_consulta = -1;
        SQLiteDatabase db = this.getConnWrite(context);

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

    /**
     *
     * Descripcion: Metodo para conocer datos de los usuarios. Se utiliza por ejemplo para saber si el usuario esta registrado en el sistema
     *
     * @param context ventana
     * @param nombre_usuario nombre del usuario del que se quiere comprobar algun dato si esta en el sistema (clave primaria)
     * @param parametro nombre del campo del dato que se quiere buscar
     * @return el dato del campo que queremos conocer
     */
    public String buscarDatosUsuarioRegistrado(Context context, String nombre_usuario, String parametro){

        String [] clave_primaria = new String[1];
        String [] parametro_buscado = new String [1];

        clave_primaria [0] = nombre_usuario;
        parametro_buscado [0] = parametro;

        String dato_buscado = null;
        SQLiteDatabase db = this.getConnRead(context);

        try {

            Cursor cursor = db.query(Constantes.NOMBRE_TABLA_USUARIO, parametro_buscado, Constantes.CAMPO_USUARIO_NOMBRE_USUARIO+"=?",clave_primaria, null,null,null);

            cursor.moveToFirst();
            dato_buscado = cursor.getString(0);
            cursor.close();

        } catch (Exception e) {
            Log.d("Debug_Excepcion", "Se ha producido un error al realizar la consulta");
        }

        db.close();

        return  dato_buscado;
    }

    /**
     *
     * Descripcion: Metodo que permite borrar la tabla de usuarios entera
     *
     * @param context ventana
     * @return resultado_resultado_consulta comprueba si la consulta se ha realizado correctamente
     */
    public int borrarTablaUsuario(Context context){

        int resultado_consulta = -1;
        SQLiteDatabase db = this.getConnWrite(context);

        String borrar_tabla_usuario_sql = "DROP TABLE Usuarios";

        try{

            db.execSQL(borrar_tabla_usuario_sql);
            resultado_consulta = 1;

        } catch (Exception e) {
            Log.d("Debug_Excepcion","Se ha producido un error al realizar la consulta");
        }

        db.close();

        return resultado_consulta;
    }
}
