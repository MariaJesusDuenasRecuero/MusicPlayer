package com.example.musicplayer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.musicplayer.Dominio.Album;
import com.example.musicplayer.Dominio.Usuario;
import com.example.musicplayer.Persistencia.CancionDAO;
import com.example.musicplayer.Persistencia.PlayListDAO;
import com.example.musicplayer.Persistencia.UsuarioDAO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class main_activity extends AppCompatActivity {

    private EditText txtNombreUsuarioLogin;
    private EditText txtPasswordLogin;
    private ImageView i;

    private Usuario usuario = null;

    private UsuarioDAO gestor_usuario_login = new UsuarioDAO();
    private Album album;

    private CancionDAO gestor_cancion = new CancionDAO();
    //private PlayListDAO p = new PlayListDAO();
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenemos las referencias a los elementos graficos de la GUI

        txtNombreUsuarioLogin = findViewById(R.id.txtNombreUsuarioLogin);
        txtPasswordLogin = findViewById(R.id.txtPasswordLogin);
        i = findViewById(R.id.imageView3);

        //p.crearTablaPlayList(main_activity.this);
        //gestor_cancion.borrarTablaCancion(main_activity.this);
        //gestor_cancion.crearTablaCancion(main_activity.this);
        //Cancion cancion = new Cancion("111","11","1", "a","a","kl");
        //gestor_cancion.insertarDatosTablaUsuario(main_activity.this, cancion, imageViewToByte(i));
        //i.setImageBitmap(gestor_cancion.buscarImagenCancion(main_activity.this, "a","AudioCancion"));
        //byte [] audio = gestor_usuario_login.buscarAudio(main_activity.this,"User1", "AudioCancion");
        //playMp3(audio);

    }

    private void playMp3(byte[] mp3SoundByteArray) {
        try {
            // create temp file that will hold byte array
            File tempMp3 = File.createTempFile("kurchina", "mp3", getCacheDir());
            tempMp3.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tempMp3);
            fos.write(gestor_usuario_login.buscarAudio(main_activity.this, txtNombreUsuarioLogin.getText().toString(), "ImagenPerfil"));
            fos.close();

            // resetting mediaplayer instance to evade problems
            mediaPlayer.reset();

            // In case you run into issues with threading consider new instance like:
            //MediaPlayer mediaPlayer = new MediaPlayer();

            // Tried passing path directly, but kept getting
            // "Prepare failed.: status=0x1"
            // so using file descriptor instead
            FileInputStream fis = new FileInputStream(tempMp3);
            mediaPlayer.setDataSource(fis.getFD());

            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException ex) {
            String s = ex.toString();
            ex.printStackTrace();
        }
    }

    public static byte[] imageViewToByte(ImageView image) {

        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;
    }

    /**
     *
     * Descripcion: Oyente asociado al boton inicio sesion. Permite acceder al sistema si los datos son correctos
     *
     * @param view
     */
    public void oyente_btnIniciarAplicacion(View view){

        //byte [] audio = gestor_usuario_login.buscarAudio(main_activity.this, txtNombreUsuarioLogin.getText().toString(), "ImagenPerfil");
        //playMp3(audio);

        if(txtNombreUsuarioLogin.getText().toString().equals("") || txtPasswordLogin.getText().toString().equals("")){

            dialogoAviso("Datos Imcompletos.", main_activity.this);
            comprobarDatosFormalarioLogin(view);

        }
        else{

            int comprobar_password = comprobacionPasswordRegistrado();
            int usuario_disponible = comprobacionUsuarioRegistrado();

            if(comprobar_password == 0 && usuario_disponible == 0){

                Intent actividad_principal = new Intent(this, menu_principal.class );
                actividad_principal.putExtra("nombre_usuario_registrado", txtNombreUsuarioLogin.getText().toString());
                startActivity(actividad_principal);

            }
            else{
                dialogoAviso("Este usuario no existe en el sistema", main_activity.this);
                txtNombreUsuarioLogin.setError("Introduzca un usuario existente" );

            }

        }

    }

    /**
     *
     * Descripcion: Metodo que comprueba los datos que faltan por completar
     *
     * @param view
     */
    private void comprobarDatosFormalarioLogin(View view){

        //Datos Nombre Usuario

        int usuario_disponible = comprobacionUsuarioRegistrado();

        if(txtNombreUsuarioLogin.getText().toString().equals("")) {
            txtNombreUsuarioLogin.setError("Introduzca un usuario por favor" );

        }
        else{
            if(usuario_disponible == 1){
                txtNombreUsuarioLogin.setError("Nombre usuario no existe" );
                dialogoAviso("Nombre de Usuario no existe.", main_activity.this);
            }
            else{
                txtNombreUsuarioLogin.setBackgroundColor(Color.rgb(0,255,0));
            }
        }

        //Datos Password Usuario

        int comprobar_password = comprobacionPasswordRegistrado();

        if(txtPasswordLogin.getText().toString().equals("")) {

            txtPasswordLogin.setError("Introduzca una contraseña porfavor" );
        }
        else{
            if(usuario_disponible == 1){
               txtPasswordLogin.setError("Nombre usuario no disponible" );
            }
            else{
                txtPasswordLogin.setBackgroundColor(Color.rgb(0,255,0));
            }
        }

    }

    /**
     *
     * Descripcion: Metodo que comprueba si el nombre de usuaruo introducido se corresponde con la contrasena
     *
     * @return entero que permite conocer si la operacion se ha realizado correctamente
     */
    private int comprobacionPasswordRegistrado(){

        int comprobacion = -1;
        String cadena_comprobacion = null;

        if(txtPasswordLogin.getText().toString() != null){

            cadena_comprobacion = gestor_usuario_login.buscarDatosUsuarioRegistrado(main_activity.this, txtNombreUsuarioLogin.getText().toString(), "Password");

            if(cadena_comprobacion != null){
                if(txtPasswordLogin.getText().toString().equals(cadena_comprobacion.toString())){
                    comprobacion = 0; //Usuario existe sistema
                }
            }
            else{
                comprobacion = 1; //Usuario no existe sistema
            }
        }
        return  comprobacion;
    }

    /**
     *
     * Descripcion: Metodo que comprueba si el nombre de usuario existe en el sistema
     *
     * @return entero que permite conocer si la operacion se ha realizado correctamente
     */
    private int comprobacionUsuarioRegistrado(){

        int comprobacion = -1;
        String cadena_comprobacion = null;

        if(txtNombreUsuarioLogin.getText().toString() != null){

            cadena_comprobacion = gestor_usuario_login.buscarDatosUsuarioRegistrado(main_activity.this,
                    txtNombreUsuarioLogin.getText().toString(), "NombreUsuario");

            if(cadena_comprobacion != null){
                comprobacion = 0; //Usuario existe
            }
            else{
                comprobacion = 1; //Usuario no existe
            }

        }
        return  comprobacion;
    }

    /**
     *
     * Descripcion: Metodo que muestra un aviso al usuario dependiendo de las acciones que este realice
     *
     * @param aviso Mensaje personalizado dependiendo del mensaje del aviso
     * @param context contexto en este caso es ventana_registro.this
     */
    private void dialogoAviso(String aviso, Context context){

        AlertDialog.Builder dialogo_builder = new AlertDialog.Builder(context);
        dialogo_builder .setMessage(aviso);
        dialogo_builder.setCancelable(true);

        dialogo_builder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog dialogo_alert = dialogo_builder.create();
        dialogo_alert.show();

    }

    /**
     *
     * Descripcion: Oyente asociado al boton Registro que te permite ir a la actividad para Registrarse
     *
     * @param view
     */
    public void oyente_btnRegristroAplicacion(View view){

        Intent i = new Intent(this, ventana_registro.class );
        startActivity(i);

    }
}