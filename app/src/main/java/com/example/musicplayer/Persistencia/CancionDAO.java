package com.example.musicplayer.Persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.musicplayer.Constantes.Constantes;
import com.example.musicplayer.Dominio.Cancion;
import com.example.musicplayer.Dominio.Usuario;

public class CancionDAO {

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
     * Descripcion: Metodo que permite buscar la imagen asociada a una cancion
     *
     * @param context
     * @param cancion_id
     * @param parametro
     * @return
     */
    public Bitmap buscarImagenCancion(Context context, String cancion_id, String parametro){

        String [] clave_primaria = new String[1];
        String [] parametro_buscado = new String [1];
        Bitmap bitmap = null;

        clave_primaria [0] = cancion_id;
        parametro_buscado [0] = parametro;

        byte [] image = null;
        SQLiteDatabase db = this.getConnRead(context);

        try {

            Cursor cursor = db.query(Constantes.NOMBRE_TABLA_CANCION, parametro_buscado,
                    Constantes.CAMPO_CANCION_ID+"=?",clave_primaria, null,null,null);

            cursor.moveToFirst();
            image = cursor.getBlob(0);
            cursor.close();
            bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        } catch (Exception e) {
            Log.d("Debug_Excepcion", "Se ha producido un error al realizar la consulta");
        }

        db.close();



        return bitmap;

    }

    /**
     *
     * Descripcion: Metodo que permite insertar una cancion en su respectiva tabla
     *
     * @param context
     * @param cancion
     * @param imagen
     */
    public void insertarDatosTablaCancion(Context context, Cancion cancion, byte [] imagen){

        SQLiteDatabase db = this.getConnWrite(context);

        String sql = "INSERT INTO Cancion VALUES (?,?,?,?,?,?,?)";

        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, cancion.getIdCancion());
        statement.bindString(2, cancion.getIdAlbum());
        statement.bindString(3, cancion.getIdArtista());
        statement.bindString(4, cancion.getNombreCancion());
        statement.bindString(5, cancion.getDuracionCancion());
        statement.bindString(6, cancion.getAudioCancion());
        statement.bindBlob(7, imagen);

        statement.executeInsert();

        db.close();

    }

    /**
     *
     * Descripcion: Metodo que permite actualizar los datos que sean de tipo multimedia
     *
     * @param context
     * @param id_cancion
     * @param campo_modificado
     * @param multimedia
     */
    public void updateDataMultimedia(Context context, String id_cancion, String campo_modificado, byte [] multimedia) {

        SQLiteDatabase db = this.getConnWrite(context);

        String sql = "UPDATE Cancion SET "+campo_modificado+" = ? WHERE NombreCancion='"+id_cancion+"'";

        SQLiteStatement statement = db.compileStatement(sql);

        statement.bindBlob(1, multimedia);


        statement.execute();
        db.close();
    }

    /**
     *
     * Descripcion: Metodo que permite elimar una cancion de la base de datos
     *
     * @param context
     * @param id_cancion
     * @return
     */
    public int eliminarCancion(Context context, String id_cancion){

        int resultado_consulta = -1;
        SQLiteDatabase db = this.getConnWrite(context);

        String eliminar_cancion_sql = "DELETE FROM "+Constantes.NOMBRE_TABLA_CANCION+" WHERE IdCancion='"+id_cancion+"'";

        try {

            db.execSQL(eliminar_cancion_sql);
            resultado_consulta = 1;

        } catch (Exception e) {
            Log.d("Debug_Excepcion", "Se ha producido un error al realizar la consulta");
        }

        db.close();

        return resultado_consulta;
    }

    /**
     *
     * Descripcion: Metodo que permite actualizar un determinado dato
     *
     * @param context
     * @param nombre_cancion
     * @param nombre_campo_tabla
     * @param parametro_nuevo
     * @return
     */
    public int updateParametroCancion(Context context, String nombre_cancion, String nombre_campo_tabla, String parametro_nuevo){

        int resultado_consulta = -1;
        SQLiteDatabase db = this.getConnWrite(context);

        String update_usuario_sql = "UPDATE "+Constantes.NOMBRE_TABLA_CANCION+" SET "+nombre_campo_tabla+" = '"+parametro_nuevo+"' WHERE "+
                Constantes.CAMPO_CANCION_NOMBRE+"= '"+nombre_cancion+"'";

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
     * Descripcion: Metodo que permite obtener un determinado parametro al realizar una consulta
     * proporcionando la clave primaria y el nombre de la columna con el parametro que se desa encontrar
     *
     * @param context
     * @param id_cancion
     * @param parametro
     * @return
     */
    public String buscarDatosCancion(Context context, String id_cancion, String parametro){

        String [] clave_primaria = new String[1];
        String [] parametro_buscado = new String [1];

        clave_primaria [0] = id_cancion;
        parametro_buscado [0] = parametro;

        String dato_buscado = null;
        SQLiteDatabase db = this.getConnRead(context);

        try {

            Cursor cursor = db.query(Constantes.NOMBRE_TABLA_CANCION, parametro_buscado,
                    Constantes.CAMPO_CANCION_ID+"=?",clave_primaria, null,null,null);

            cursor.moveToFirst();
            dato_buscado = cursor.getString(0);
            cursor.close();

        } catch (Exception e) {
            Log.d("Debug_Excepcion", "Se ha producido un error al realizar la consulta");
        }

        db.close();

        return dato_buscado;
    }

    /**
     *
     * Descripcion: Metodo que permite obtener el identificador
     *
     * @param context
     * @param id_album
     * @param parametro
     * @return
     */
    public String buscarIdentificadoCancion(Context context, String id_album, String parametro){

        String [] clave_primaria = new String[1];
        String [] parametro_buscado = new String [1];

        clave_primaria [0] = id_album;
        parametro_buscado [0] = parametro;

        String dato_buscado = null;
        SQLiteDatabase db = this.getConnRead(context);

        try {

            Cursor cursor = db.query(Constantes.NOMBRE_TABLA_CANCION, parametro_buscado,
                    Constantes.CAMPO_CANCION_ALBUM_ID+"=?",clave_primaria, null,null,null);

            cursor.moveToFirst();
            dato_buscado = cursor.getString(0);
            cursor.close();

        } catch (Exception e) {
            Log.d("Debug_Excepcion", "Se ha producido un error al realizar la consulta");
        }

        db.close();

        return  dato_buscado;
    }

    public void crearTablaCancion(Context context){

        SQLiteDatabase db = this.getConnWrite(context);
        db.execSQL(Constantes.CREAR_TABLA_CANCION);

    }

    /**
     *
     * Descripcion: Metodo para eliminar la tabla cancion de la base de datos
     *
     * @param context
     * @return
     */
    public int borrarTablaCancion(Context context){

        int resultado_consulta = -1;
        SQLiteDatabase db = this.getConnWrite(context);

        String borrar_tabla_usuario_sql = "DROP TABLE Cancion";

        try{

            db.execSQL(borrar_tabla_usuario_sql);
            resultado_consulta = 1;

        } catch (Exception e) {
            Log.d("Debug_Excepcion","Se ha producido un error al realizar la consulta");
        }

        db.close();

        return resultado_consulta;
    }

    /**
     *
     * Descripcion: Metodo para obtener las filas de la tabla cancion
     *
     * @param context
     * @return
     */
    public int getProfilesCount(Context context) {

        String countQuery = "SELECT  * FROM " + Constantes.NOMBRE_TABLA_CANCION;
        SQLiteDatabase db = this.getConnRead(context);
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    /**
     *
     * Descripcion: Metodo para obtener el identificador de las canciones pertenecientes a un
     * album
     *
     * @param context
     * @param id_album
     * @param index
     * @return
     */
    public String [] getListaCanciones(Context context, String id_album, int index){

        String [] id_canciones = new String[index];
        int i = 0;

        String countQuery = "SELECT IdCancion FROM " + Constantes.NOMBRE_TABLA_CANCION+ " WHERE IdAlbumCancion='"+id_album+"'";
        SQLiteDatabase db = this.getConnRead(context);
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){

            id_canciones[i] = cursor.getString(cursor.getColumnIndex("IdCancion"));
            i = i + 1;

            cursor.moveToNext();
        }
        db.close();

        return id_canciones;
    }

    /**
     *
     * Descripcion: Metodo para obtener un determinado numeo de calciones de un album
     *
     * @param context
     * @param id_album
     * @return
     */
    public int getNumeroCanciones(Context context, String id_album) {

        String countQuery = "SELECT IdCancion FROM " + Constantes.NOMBRE_TABLA_CANCION+ " WHERE IdAlbumCancion='"+id_album+"'";
        SQLiteDatabase db = this.getConnRead(context);
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }
}
