package com.example.musicplayer.Presentacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.musicplayer.Dominio.Usuario;
import com.example.musicplayer.Persistencia.UsuarioDAO;
import com.example.musicplayer.R;

import java.io.ByteArrayOutputStream;

public class ventana_registro extends AppCompatActivity {

    private EditText txtNombreUsuario;
    private EditText txtNombre;
    private EditText txtPassword;
    private EditText txtConfirmarPassword;
    private EditText txtTelefono;
    private EditText txtCorreoElectronico;
    private EditText txtFechaNacimiento;
    private ImageView imagen_perfil_estandar;

    private UsuarioDAO gestor_usuario = new UsuarioDAO();
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_registro);

        inicializarDatos();

    }

    /**
     *
     * Descripcion: Metodo que permite inicializar los datos de la ventana registro
     *
     */
    private void inicializarDatos(){

        //Obtenemos las referencias a los elementos graficos de la GUI

        txtNombreUsuario = findViewById(R.id.txtNombreUsuario);
        txtNombre = findViewById(R.id.txtCambiar_nombre);
        txtPassword = findViewById(R.id.txtCambiar_Password);
        txtConfirmarPassword = findViewById(R.id.txtConfirmarPassword);
        txtTelefono = findViewById(R.id.txtCambiar_Telefono);
        txtCorreoElectronico = findViewById(R.id.txtCambiar_email);
        txtFechaNacimiento = findViewById(R.id.txtCambiar_FechaNacimiento);

        imagen_perfil_estandar = findViewById(R.id.imageViewPerfil);

    }

    /**
     *
     * Descripcion: Metodo que permite convertir la imagen asociada al imageView a bytes
     *
     * @param image
     * @return array de bytes de la imagen
     */
    public static byte[] imageViewToByte(ImageView image) {

        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;
    }

    /**
     *
     * Descripcion: Insertar los datos del usuario en la base de datos para registrarlos
     *
     * @param view
     */
    public void oyente_btnRealizarRegistro(View view){

        boolean comprobar_correo = comprobarCorreoElectronico();
        int comprobar_usuario_registrado_sistema = comprobacionUsuarioRegistrado();
        int validacion_registro_datos = 0;

        if(txtNombre.getText().toString().equals("") || txtNombreUsuario.getText().toString().equals("") || comprobar_usuario_registrado_sistema == 0
                ||  txtPassword.toString().equals("")  || txtConfirmarPassword.toString().equals("") || comprobar_correo == false ){

            dialogoAviso("Registo Incompleto. Por favor rellene los campos que faltan.", ventana_registro.this);

            comprobarDatosFormalarioRegistro(view);

        }
        else{

            if(txtPassword.getText().toString().equals(txtConfirmarPassword.getText().toString())){

                txtNombre.setTextColor(Color.rgb(0,143,57));
                txtNombreUsuario.setTextColor(Color.rgb(0,143,57));

                txtPassword.setTextColor(Color.rgb(0,143,57));
                txtConfirmarPassword.setTextColor(Color.rgb(0,143,57));

                txtCorreoElectronico.setTextColor(Color.rgb(0,143,57));

                validacion_registro_datos = insertarDatosUsuario();


                if(validacion_registro_datos == 1){
                    //Toast.makeText(ventana_registro.this, "OK Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                    dialogoAviso("El usuario ha sido registrado con exito.", ventana_registro.this);
                    Intent i = new Intent(this, main_activity.class );
                    startActivity(i);
                }
                else {
                    Toast.makeText(ventana_registro.this, "NO OK", Toast.LENGTH_SHORT).show();
                }

            }
            else{

                comprobarDatosFormalarioRegistro(view);

            }
        }
    }

    /**
     *
     * Descripcion: Metodo que permite insertar un usuario en la aplicacion
     *
     * @return entero que permite conocer si la operacion se ha realizado correctamente
     */
    private int insertarDatosUsuario(){

        int comprobacion = 0;
        String cadena_formato_fecha = null;
        String cadena_formato_telefono = null;

        if(txtFechaNacimiento.getText().toString().equals("")){
            cadena_formato_fecha = "Ninguno";
        }
        else{
            cadena_formato_fecha = txtFechaNacimiento.getText().toString();
        }

        if(txtTelefono.getText().toString().equals("")){
            cadena_formato_telefono = "Ninguno";
        }
        else{
            cadena_formato_telefono = txtTelefono.getText().toString();
        }

        byte [] data = imageViewToByte(imagen_perfil_estandar);

        usuario = new Usuario(txtNombreUsuario.getText().toString(), txtNombre.getText().toString(), txtPassword.getText().toString(),
                cadena_formato_telefono, txtCorreoElectronico.getText().toString(), cadena_formato_fecha,
                BitmapFactory.decodeByteArray(data, 0, data.length));

        gestor_usuario.insertarDatosTablaUsuario(ventana_registro.this, usuario, data);

        comprobacion =  1;

        return comprobacion;

    }

    /**
     *
     * Descripcion: Metodo que permite saber si el nombre de usuario (que es la clave primaria del sistema) ya esta
     * en el sistema
     *
     * @return entero que permite conocer si la operacion se ha realizado correctamente
     */
    private int comprobacionUsuarioRegistrado(){

        int comprobacion = -1;
        String cadena_comprobacion = null;

        if(txtNombreUsuario.getText().toString() != null){

            cadena_comprobacion = gestor_usuario.buscarDatosUsuarioRegistrado(ventana_registro.this,
                    txtNombreUsuario.getText().toString(), "NombreUsuario");

            if(cadena_comprobacion != null){
                comprobacion = 0; //Usuario en uso
            }
            else{
                comprobacion = 1; //Usuario disponible
            }
        }
        return  comprobacion;
    }

    /**
     *
     * @return boolean que permite conocer si el dato es correcto
     */
    private boolean comprobarCorreoElectronico(){

        boolean correo_correcto = false;
        String comprobar_correo = txtCorreoElectronico.getText().toString();

        for (int i = 0; i<comprobar_correo.length(); i++){
            if(comprobar_correo.charAt(i) == '@'){
                correo_correcto = true;
            }
        }
        return correo_correcto;
    }

    /**
     *
     * Descripcion: Metodo que comprueba los datos que faltan por completar
     *
     */
    private void comprobarDatosFormalarioRegistro(View view){

        //Datos Nombre

        if(txtNombre.getText().toString().equals("")) {
            txtNombre.setError("Introduzca su nombre");
            txtNombre.setTextColor(Color.RED);
            //txtNombre.setBackgroundColor(Color.rgb(200,0,0));
        }
        else{
            txtNombre.setTextColor(Color.rgb(0,143,57));
        }

        //Datos Nombre usuario

        int usuario_disponible = comprobacionUsuarioRegistrado();

        if(txtNombreUsuario.getText().toString().equals("")) {
            txtNombreUsuario.setError("Introduzca un nombre de usuario" );
            txtNombreUsuario.setTextColor(Color.RED);
            //txtNombreUsuario.setBackgroundColor(Color.rgb(200,0,0));
        }
        else{
            if(usuario_disponible == 0){
                txtNombreUsuario.setError("Nombre no disponible" );
                txtNombreUsuario.setTextColor(Color.RED);
                dialogoAviso("Registo Incompleto. Nombre de Usuario no disponible.", ventana_registro.this);
            }
            else{
                txtNombreUsuario.setTextColor(Color.rgb(0,143,57));
            }
        }

        //Datos Contrasena

        if(txtPassword.getText().toString().equals("")) {
            //txtPassword.setBackgroundColor(Color.rgb(200,0,0));
            txtPassword.setError("Introduzca una contraseña" );
            txtPassword.setTextColor(Color.RED);
        }
        else{
            txtPassword.setTextColor(Color.rgb(0,143,57));
        }

        //Datos confirmar contrasena

        if(txtConfirmarPassword.getText().toString().equals("")) {
           // txtConfirmarPassword.setBackgroundColor(Color.rgb(200,0,0));
            txtConfirmarPassword.setError("Repita la contraseña, por favor" );
            txtConfirmarPassword.setTextColor(Color.RED);
        }
        else{
            if(txtPassword.getText().toString().equals(txtConfirmarPassword.getText().toString())){
                txtConfirmarPassword.setTextColor(Color.rgb(0,143,57));
            }
            else{
                txtConfirmarPassword.setError("Las contraseñas no coinciden" );
                txtConfirmarPassword.setTextColor(Color.RED);
               // txtConfirmarPassword.setBackgroundColor(Color.rgb(200,0,0));
                dialogoAviso("Las contraseñas no coinciden.", ventana_registro.this);
            }
        }

        //Datos correo electronico

        boolean correo_correcto_comprobacion = comprobarCorreoElectronico();

        if(txtCorreoElectronico.getText().toString().equals("") || correo_correcto_comprobacion == false){
            //txtCorreoElectronico.setBackgroundColor(Color.rgb(200,0,0));
            txtCorreoElectronico.setError("Introduzca su correo electrónico" );
            txtCorreoElectronico.setTextColor(Color.RED);

        }
        else{
            txtCorreoElectronico.setTextColor(Color.rgb(0,143,57));
        }
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
     * Descripcion: Metodo que permite volver a la actividad anterior
     *
     * @param view
     */
    public void oyente_btnVolver(View view){
        Intent i = new Intent(this, main_activity.class );
        startActivity(i);
    }
}