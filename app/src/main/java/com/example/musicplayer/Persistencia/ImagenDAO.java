package com.example.musicplayer.Persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.musicplayer.Constantes.Constantes;

public class ImagenDAO {


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

    public void crearTablaImagen(Context context){

        SQLiteDatabase db = this.getConnWrite(context);
        db.execSQL(Constantes.CREAR_TABLA_IMAGEN);
        /**
        String borrar_tabla_usuario_sql = "DROP TABLE Imagen";

        try{

            db.execSQL(borrar_tabla_usuario_sql);


        } catch (Exception e) {
            Log.d("Debug_Excepcion","Se ha producido un error al realizar la consulta");
        }
         **/
    }

    public void insertarDatosTablaImagen(Context context, String nombre_imagen, byte [] imagen){

        SQLiteDatabase db = this.getConnWrite(context);

        String insertar_imagen_sql = "INSERT INTO "+Constantes.NOMBRE_TABLA_IMAGEN+
                " ("+Constantes.CAMPO_PERFIL_NOMBRE_IMAGEN+", "+Constantes.CAMPO_PERFIL_IMAGEN+")" +
                " VALUES ('"+nombre_imagen+"', '"+null+"')";
        try{

            db.execSQL(insertar_imagen_sql);
            //resultado_consulta = 1;

        } catch (Exception e) {
            Log.d("Debug_Excepcion","Se ha producido un error al realizar la consulta");
        }

        db.close();

    }

    public Cursor getDatos(Context context, String sql) {

        SQLiteDatabase db = getConnRead(context);
        return db.rawQuery(sql, null);

    }

}
