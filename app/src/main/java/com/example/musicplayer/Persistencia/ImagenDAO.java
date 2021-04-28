package com.example.musicplayer.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(context, "dbProyectoGSI", null, 9);
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

        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(context, "dbProyectoGSI", null, 9);
        SQLiteDatabase db = conexion.getReadableDatabase();

        return db;

    }

    /**
     *
     * Descripcion: Permite crear la tabla Imagen
     *
     * @param context
     */
    public void crearTablaImagen(Context context){

        SQLiteDatabase db = this.getConnWrite(context);
        db.execSQL(Constantes.CREAR_TABLA_IMAGEN);

    }

    /**
     *
     * Descripcion: Permite borrar la tabla Imagen
     *
     * @param context
     */
    public void eliminarTablaImagen(Context context){

        SQLiteDatabase db = this.getConnWrite(context);
        String borrar_tabla_usuario_sql = "DROP TABLE Imagen";

        try{

            db.execSQL(borrar_tabla_usuario_sql);

        } catch (Exception e) {
            Log.d("Debug_Excepcion","Se ha producido un error al realizar la consulta");
        }

    }

    /**
     *
     * Descripcion: Metodo que realiza una busqueda de la imagen por su clave primaria
     *
     * @param context
     * @param nombre_imagen clave primaria
     * @param parametro el contenido de la imagen
     * @return Bitmat para visualizar la imagen
     */
    public Bitmap buscarImagen(Context context, String nombre_imagen, String parametro){

        String [] clave_primaria = new String[1];
        String [] parametro_buscado = new String [1];

        clave_primaria [0] = nombre_imagen;
        parametro_buscado [0] = parametro;

        byte [] image = null;
        SQLiteDatabase db = this.getConnRead(context);

        try {

            Cursor cursor = db.query(Constantes.NOMBRE_TABLA_IMAGEN, parametro_buscado,
                    Constantes.CAMPO_PERFIL_NOMBRE_IMAGEN+"=?",clave_primaria, null,null,null);

            cursor.moveToFirst();
            image = cursor.getBlob(0);
            cursor.close();


        } catch (Exception e) {
            Log.d("Debug_Excepcion", "Se ha producido un error al realizar la consulta");
        }

        db.close();

        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    /**
     *
     * Descripcion: Metodo que permite insertar imagenes en la base de datos. Especial atencion a como
     * se insertan los datos ya que para que se almacenen en SQLite como BLOB se tiene que hacer de esta manera
     *
     * @param context
     * @param nombre_imagen clave primaria y permite identificar la imagen
     * @param imagen typo byte para almacenar como BLOB en la base de datos
     */
    public void insertarDatosTablaImagen(Context context, String nombre_imagen, byte [] imagen){

        SQLiteDatabase db = this.getConnWrite(context);

        byte[] data = imagen;

        String sql = "INSERT INTO Imagen VALUES (?,?)";

        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, nombre_imagen);
        statement.bindBlob(2, imagen);

        statement.executeInsert();

        db.close();

    }

    /**
     *
     * Descripcion: Metodo que permite buscar el nombre de una imagen. Usado por ejemplo para comprobar
     * si existe determinado nombre en la base de datos y no tener claves primarias repetidas
     *
     * @param context
     * @param nombre_imagen clave primaria y permite identificar la imagen
     * @param parametro typo byte para almacenar como BLOB en la base de datos
     * @return
     */
    public String buscarDatosImagen(Context context, String nombre_imagen, String parametro){

        String [] clave_primaria = new String[1];
        String [] parametro_buscado = new String [1];

        clave_primaria [0] = nombre_imagen;
        parametro_buscado [0] = parametro;

        String dato_buscado = null;
        SQLiteDatabase db = this.getConnRead(context);

        try {

            Cursor cursor = db.query(Constantes.NOMBRE_TABLA_IMAGEN, parametro_buscado,
                    Constantes.CAMPO_PERFIL_NOMBRE_IMAGEN+"=?",clave_primaria, null,null,null);

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
     * Descripcion: Metodo que devuelve un objeto de tipo cursor
     *
     * @param context
     * @param sql
     * @return
     */
    public Cursor getDatos(Context context, String sql) {

        SQLiteDatabase db = getConnRead(context);
        return db.rawQuery(sql, null);

    }

}
