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

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario usuario;

        //TODO comprobar_usuario_registrado_sistema
        //El telefono no es obligatorio para registrase en el sistema



        if(txtNombre.getText().toString().equals("") || txtPassword.toString().equals("")
                || txtConfirmarPassword.toString().equals("") || comprobar_correo == false){

            //Comprobar si el nombre de usuario esta en uso

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


                usuario = new Usuario(txtNombreUsuario.getText().toString(), txtNombre.getText().toString(),txtPassword.getText().toString(),
                        "", txtCorreoElectronico.getText().toString(),"");

                int resInsert = usuarioDAO.usuarioRegistrado(ventanaRegistro.this, "User3");
                //int resInsert = usuarioDAO.updateParametroUsuario(ventanaRegistro.this, "User1", "Password", "555");
                //int resInsert = usuarioDAO.eliminarUsuario(ventanaRegistro.this, "Guada");
                //int resInsert = usuarioDAO.insertarUsuario(ventanaRegistro.this, usuario);

                //int resInsert = usuarioDAO.borrarTablaUsuario(ventanaRegistro.this);

                if(resInsert == 1){
                    Toast.makeText(ventanaRegistro.this, "OK Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                    txtNombreUsuario.setText("Usuario NO esta en el sistema");
                }
                else {
                    Toast.makeText(ventanaRegistro.this, "NO OK", Toast.LENGTH_SHORT).show();
                    txtNombreUsuario.setText("Usuario Esta en el sistema");
                }

            }
            else{

                txtPassword.setBackgroundColor(Color.rgb(255,0,0));
                txtConfirmarPassword.setBackgroundColor(Color.rgb(255,0,0));

                dialogoAviso("Las contraseñas no coinciden.",ventanaRegistro.this);

                comprobarDatosFormalarioRegistro(view);

            }
        }
    }

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
            txtNombre.setBackgroundColor(Color.rgb(200,0,0));
        }
        else{
            txtNombre.setBackgroundColor(Color.rgb(0,255,0));
        }

        //Datos Contrasena

        if(txtPassword.getText().toString().equals("")) {
            txtPassword.setBackgroundColor(Color.rgb(200,0,0));
        }
        else{
            txtPassword.setBackgroundColor(Color.rgb(0,255,0));
        }

        //Datos confirmar contrasena

        if(txtConfirmarPassword.getText().toString().equals("")) {
            txtConfirmarPassword.setBackgroundColor(Color.rgb(200,0,0));
        }
        else{
            txtConfirmarPassword.setBackgroundColor(Color.rgb(0,255,0));
        }

        //Datos correo electronico

        boolean correo_correcto_comprobacion = comprobarCorreoElectronico();

        if(txtCorreoElectronico.getText().toString().equals("") || correo_correcto_comprobacion == false){
            txtConfirmarPassword.setBackgroundColor(Color.rgb(200,0,0));
        }
        else{
            txtConfirmarPassword.setBackgroundColor(Color.rgb(0,255,0));
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
    public void oyente_btnVolver(View view){
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
    }
}