package com.example.musicplayer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.musicplayer.Dominio.Usuario;
import com.example.musicplayer.Persistencia.UsuarioDAO;

public class ventanaRegistro extends AppCompatActivity {

    private EditText txtNombreUsuario;
    private EditText txtNombre;
    private EditText txtPassword;
    private EditText txtConfirmarPassword;
    private EditText txtTelefono;
    private EditText txtCorreoElectronico;
    private EditText txtFechaNacimiento;

    private UsuarioDAO gestor_usuario = new UsuarioDAO();
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_registro);

        //Obtenemos las referencias a los elementos graficos de la GUI

        txtNombreUsuario = findViewById(R.id.txtNombreUsuario);
        txtNombre = findViewById(R.id.txtNombre);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmarPassword = findViewById(R.id.txtConfirmarPassword);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        txtFechaNacimiento = findViewById(R.id.txtFechaNacimiento);

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

            dialogoAviso("Registo Incompleto. Por favor rellene los campos que faltan.",ventanaRegistro.this);

            comprobarDatosFormalarioRegistro(view);

        }
        else{

            if(txtPassword.getText().toString().equals(txtConfirmarPassword.getText().toString())){

                txtNombre.setBackgroundColor(Color.rgb(0,255,0));
                txtNombreUsuario.setBackgroundColor(Color.rgb(0,255,0));

                txtPassword.setBackgroundColor(Color.rgb(0,255,0));
                txtConfirmarPassword.setBackgroundColor(Color.rgb(0,255,0));

                txtCorreoElectronico.setBackgroundColor(Color.rgb(0,255,0));

                validacion_registro_datos = insertarDatosUsuario();


                if(validacion_registro_datos == 1){
                    //Toast.makeText(ventanaRegistro.this, "OK Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                    dialogoAviso("El usuario ha sido registrado con exito.",ventanaRegistro.this);
                    Intent i = new Intent(this, MainActivity.class );
                    startActivity(i);
                }
                else {
                    Toast.makeText(ventanaRegistro.this, "NO OK", Toast.LENGTH_SHORT).show();
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
            cadena_formato_fecha = "Fecha Nacimiento no registrado";
        }
        else{
            cadena_formato_fecha = txtFechaNacimiento.getText().toString();
        }

        if(txtTelefono.getText().toString().equals("")){
            cadena_formato_telefono = "Telefono no registrado";
        }
        else{
            cadena_formato_telefono = txtTelefono.getText().toString();
        }

        usuario = new Usuario(txtNombreUsuario.getText().toString(), txtNombre.getText().toString(), txtPassword.getText().toString(),
                cadena_formato_telefono, txtCorreoElectronico.getText().toString(), cadena_formato_fecha);

        comprobacion =  gestor_usuario.insertarUsuario(ventanaRegistro.this, usuario);

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

            cadena_comprobacion = gestor_usuario.buscarDatosUsuarioRegistrado(ventanaRegistro.this, txtNombreUsuario.getText().toString(), "NombreUsuario");

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
            txtNombre.setBackgroundColor(Color.rgb(0,255,0));


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
                dialogoAviso("Registo Incompleto. Nombre de Usuario no disponible.",ventanaRegistro.this);
            }
            else{
                txtNombreUsuario.setBackgroundColor(Color.rgb(0,255,0));
            }
        }

        //Datos Contrasena

        if(txtPassword.getText().toString().equals("")) {
            //txtPassword.setBackgroundColor(Color.rgb(200,0,0));
            txtPassword.setError("Introduzca una contraseña" );
            txtPassword.setTextColor(Color.RED);
        }
        else{
            txtPassword.setBackgroundColor(Color.rgb(0,255,0));
        }

        //Datos confirmar contrasena

        if(txtConfirmarPassword.getText().toString().equals("")) {
           // txtConfirmarPassword.setBackgroundColor(Color.rgb(200,0,0));
            txtConfirmarPassword.setError("Repita la contraseña, por favor" );
            txtConfirmarPassword.setTextColor(Color.RED);
        }
        else{
            if(txtPassword.getText().toString().equals(txtConfirmarPassword.getText().toString())){
                txtConfirmarPassword.setBackgroundColor(Color.rgb(0,255,0));
            }
            else{
                txtConfirmarPassword.setError("Las contraseñas no coinciden" );
                txtConfirmarPassword.setTextColor(Color.RED);
               // txtConfirmarPassword.setBackgroundColor(Color.rgb(200,0,0));
                dialogoAviso("Las contraseñas no coinciden.",ventanaRegistro.this);
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
            txtCorreoElectronico.setBackgroundColor(Color.rgb(0,255,0));
        }
    }

    /**
     *
     * Descripcion: Metodo que muestra un aviso al usuario dependiendo de las acciones que este realice
     *
     * @param aviso Mensaje personalizado dependiendo del mensaje del aviso
     * @param context contexto en este caso es ventanaRegistro.this
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
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
    }
}