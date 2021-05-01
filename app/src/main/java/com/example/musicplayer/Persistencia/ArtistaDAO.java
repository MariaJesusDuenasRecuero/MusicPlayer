package com.example.musicplayer.Persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.musicplayer.Constantes.Constantes;
import com.example.musicplayer.Dominio.Artista;
import com.example.musicplayer.Dominio.Usuario;

import java.sql.PreparedStatement;

public class ArtistaDAO {

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
     * Descripcion: Metodo que permite realizar una consulta para obtener un determinado dato de un
     * artista
     *
     * @param context
     * @param id_usuario
     * @param parametro
     * @return
     */
    public String buscarDatosArtista(Context context, String id_usuario, String parametro){

        String [] clave_primaria = new String[1];
        String [] parametro_buscado = new String [1];

        clave_primaria [0] = id_usuario;
        parametro_buscado [0] = parametro;

        String dato_buscado = null;
        SQLiteDatabase db = this.getConnRead(context);

        try {

            Cursor cursor = db.query(Constantes.NOMBRE_TABLA_ARTISTA, parametro_buscado, Constantes.CAMPO_ARTISTA_ID+"=?",clave_primaria, null,null,null);

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
     * Descripcion: Metodo que permite realizar una consulta para obtener un determinado dato de un artista
     * dado su clave primaria y el nombre de la columna imagen en la base de datos
     *
     * @param context
     * @param id_artista
     * @param parametro
     * @return
     */
    public Bitmap buscarImagenArtista(Context context, String id_artista, String parametro){

        String [] clave_primaria = new String[1];
        String [] parametro_buscado = new String [1];
        Bitmap bitmap = null;

        clave_primaria [0] = id_artista;
        parametro_buscado [0] = parametro;

        byte [] image = null;
        SQLiteDatabase db = this.getConnRead(context);

        try {

            Cursor cursor = db.query(Constantes.NOMBRE_TABLA_ARTISTA, parametro_buscado,
                    Constantes.CAMPO_ARTISTA_ID+"=?",clave_primaria, null,null,null);

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
     * Descripcion:Metodo que permite insertar un artista en la tabla
     *
     * @param context
     * @param artista
     * @param imagen
     */
    public void insertarDatosTablaArtista(Context context, Artista artista, byte [] imagen){

        SQLiteDatabase db = this.getConnWrite(context);

        byte[] data = imagen;

        String sql = "INSERT INTO Artista VALUES (?,?,?,?)";

        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, artista.getIdArtista());
        statement.bindString(2, artista.getNombreArtista());
        statement.bindString(3, artista.getTipo());
        statement.bindBlob(4, imagen);

        statement.executeInsert();

        db.close();

    }

    /**
     *
     * Descripcion: Metodo que permite actualizar una imagen del artista
     *
     * @param context
     * @param id_artista
     * @param image
     */
    public void updateDataImagen(Context context, String id_artista, byte [] image) {

        SQLiteDatabase db = this.getConnWrite(context);

        String sql = "UPDATE Artista SET ImagenArtista = ? WHERE IdArtista='"+id_artista+"'";

        SQLiteStatement statement = db.compileStatement(sql);

        statement.bindBlob(1, image);


        statement.execute();
        db.close();
    }

    /**
     *
     * Descripcion: Metodo que permite crear la tabla Artista
     *
     * @param context
     */
    public void crearTablaArtista(Context context){

        SQLiteDatabase db = this.getConnWrite(context);
        db.execSQL(Constantes.CREAR_TABLA_ARTISTA);

    }

    /**
     *
     * Descripcion: Metodo que permite elimanar la tabla artista
     *
     * @param context
     * @return
     */
    public int borrarTablaArtista(Context context){

        int resultado_consulta = -1;
        SQLiteDatabase db = this.getConnWrite(context);

        String borrar_tabla_artista_sql = "DROP TABLE Artista";

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
     * Descripcion: Metodo que permite obtner el numero total de artistas
     *
     * @param context
     * @return
     */
    public int getNumeroTotalArtistas(Context context) {

        String countQuery = "SELECT  * FROM " + Constantes.NOMBRE_TABLA_ARTISTA;
        SQLiteDatabase db = this.getConnRead(context);
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    /**
     *
     * Descripcion: Metodo que permite obtner los identificadores de los artistas en el sitema
     *
     * @param context
     * @param index
     * @return
     */
    public String [] getListaArtistas(Context context, int index){

        String [] id_artistas = new String[index];
        int i = 0;

        String countQuery = "SELECT IdArtista FROM " + Constantes.NOMBRE_TABLA_ARTISTA;

        SQLiteDatabase db = this.getConnRead(context);
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){

            id_artistas[i] = cursor.getString(cursor.getColumnIndex("IdArtista"));
            i = i + 1;

            cursor.moveToNext();
        }
        db.close();

        return id_artistas;

    }

}
