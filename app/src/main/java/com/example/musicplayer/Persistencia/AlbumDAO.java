package com.example.musicplayer.Persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.musicplayer.Constantes.Constantes;
import com.example.musicplayer.Dominio.Album;
import com.example.musicplayer.Dominio.Artista;

public class AlbumDAO {

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
     * @param context
     * @param id_album
     * @param parametro
     * @return
     */
    public String buscarDatosArtista(Context context, String id_album, String parametro){

        String [] clave_primaria = new String[1];
        String [] parametro_buscado = new String [1];

        clave_primaria [0] = id_album;
        parametro_buscado [0] = parametro;

        String dato_buscado = null;
        SQLiteDatabase db = this.getConnRead(context);

        try {

            Cursor cursor = db.query(Constantes.NOMBRE_TABLA_ALBUM, parametro_buscado, Constantes.CAMPO_ALBUM_ID+"=?",
                    clave_primaria, null,null,null);

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
     * @param context
     * @param id_album
     * @param parametro
     * @return
     */
    public Bitmap buscarImagenAlbum(Context context, String id_album, String parametro){

        String [] clave_primaria = new String[1];
        String [] parametro_buscado = new String [1];

        clave_primaria [0] = id_album;
        parametro_buscado [0] = parametro;

        byte [] image = null;
        SQLiteDatabase db = this.getConnRead(context);

        try {

            Cursor cursor = db.query(Constantes.NOMBRE_TABLA_ALBUM, parametro_buscado,
                    Constantes.CAMPO_ALBUM_ID+"=?",clave_primaria, null,null,null);

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
     * @param context
     * @param album
     * @param imagen
     */
    public void insertarDatosTablaArtista(Context context, Album album, byte [] imagen){

        SQLiteDatabase db = this.getConnWrite(context);

        byte[] data = imagen;

        String sql = "INSERT INTO Album VALUES (?,?,?,?,?)";

        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, album.getIdAlbum());
        statement.bindString(2, album.getIdArtista());
        statement.bindString(3, album.getNombreAlbum());
        statement.bindString(4, album.getDuracionAlbum());
        statement.bindBlob(5, imagen);

        statement.executeInsert();

        db.close();

    }

    /**
     *
     * @param context
     * @param id_album
     * @param image
     */
    public void updateDataImagenAlbum(Context context, String id_album, byte [] image) {

        SQLiteDatabase db = this.getConnWrite(context);

        String sql = "UPDATE Album SET ImagenAlbum = ? WHERE IdAlbum='"+id_album+"'";

        SQLiteStatement statement = db.compileStatement(sql);

        statement.bindBlob(1, image);


        statement.execute();
        db.close();
    }

    /**
     *
     * @param context
     */
    public void crearTablaAlbum(Context context){

        SQLiteDatabase db = this.getConnWrite(context);
        db.execSQL(Constantes.CREAR_TABLA_ALBUM);

    }

    /**
     *
     * @param context
     * @return
     */
    public int borrarTablaAlbum(Context context){

        int resultado_consulta = -1;
        SQLiteDatabase db = this.getConnWrite(context);

        String borrar_tabla_artista_sql = "DROP TABLE Album";

        try{

            db.execSQL(borrar_tabla_artista_sql);
            resultado_consulta = 1;

        } catch (Exception e) {
            Log.d("Debug_Excepcion","Se ha producido un error al realizar la consulta");
        }

        db.close();

        return resultado_consulta;
    }

    /**
     *
     * @param context
     * @return
     */
    public int getProfilesCountAlbum(Context context) {

        String countQuery = "SELECT  * FROM " + Constantes.NOMBRE_TABLA_ALBUM;
        SQLiteDatabase db = this.getConnRead(context);
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }
}
