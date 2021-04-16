package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.musicplayer.Persistencia.UsuarioDAO;

public class Configuracion extends AppCompatActivity {

    private String nombre_usuario_registrado;
    private UsuarioDAO gestor_usuario_configuracion = new UsuarioDAO();

    private EditText txtNombre;
    private EditText txtCorreo;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        Bundle bundle = getIntent().getExtras();
        this.nombre_usuario_registrado = bundle.getString("nombre_usuario_registrado");

        /**
        Toast notification;
        notification = Toast.makeText(this, "Cambiar confi "
                + this.nombre_usuario_registrado, Toast.LENGTH_LONG);
        notification.show();
         */

        this.txtNombre = findViewById(R.id.txtCambiar_nombre);
        this.txtCorreo = findViewById(R.id.txtCambiar_email);
        this.txtPassword = findViewById(R.id.txtCambiar_Password);



        Button btn_ModificarDatos = findViewById(R.id.btnModificarDatos);
        btn_ModificarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNombre.getText().toString().equals("")){

                    Toast notification;
                    notification = Toast.makeText(Configuracion.this, "Nombre no cambiado", Toast.LENGTH_LONG);
                    notification.show();

                }
                else{
                    gestor_usuario_configuracion.updateParametroUsuario(Configuracion.this,
                            nombre_usuario_registrado, "Nombre", txtNombre.getText().toString());

                     Toast notification;
                     notification = Toast.makeText(Configuracion.this, "Nuevo dato"
                     + gestor_usuario_configuracion.buscarDatosUsuarioRegistrado(Configuracion.this, nombre_usuario_registrado, "Nombre"), Toast.LENGTH_LONG);
                     notification.show();


                }
                if(txtCorreo.getText().toString().equals("")){

                    Toast notification;
                    notification = Toast.makeText(Configuracion.this, "Correo no cambiado", Toast.LENGTH_LONG);
                    notification.show();

                }
                else{
                    gestor_usuario_configuracion.updateParametroUsuario(Configuracion.this,
                            nombre_usuario_registrado, "CorreoElectronico", txtCorreo.getText().toString());

                     Toast notification;
                     notification = Toast.makeText(Configuracion.this, "Nuevo dato"
                     + gestor_usuario_configuracion.buscarDatosUsuarioRegistrado(Configuracion.this, nombre_usuario_registrado, "CorreoElectronico"), Toast.LENGTH_LONG);
                     notification.show();


                }

                if(txtPassword.getText().toString().equals("")){

                    Toast notification;
                    notification = Toast.makeText(Configuracion.this, "Password no cambiado", Toast.LENGTH_LONG);
                    notification.show();

                }
                else{
                    gestor_usuario_configuracion.updateParametroUsuario(Configuracion.this,
                            nombre_usuario_registrado, "Password", txtPassword.getText().toString());

                    Toast notification;
                    notification = Toast.makeText(Configuracion.this, "Nuevo dato"
                            + gestor_usuario_configuracion.buscarDatosUsuarioRegistrado(Configuracion.this, nombre_usuario_registrado,
                            "Password"), Toast.LENGTH_LONG);
                    notification.show();


                }
            }
        });

    }


}