package com.example.musicplayer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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


        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario("User3", "UsuarioPrueba3","321",
                "615822963", "usuario2@gmail.com","02/11/1999");

        //TODO comprobar_usuario_registrado_sistema, comprobar_correo
        //El telefono no es obligatorio para registrase en el sistema

        if(txtNombre.getText().equals("") || txtPassword.getText().equals("") ||
                txtConfirmarPassword.getText().equals("") || txtFechaNacimiento.getText().equals("")){



        }

        /**
        int resInsert = usuarioDAO.insertarUsuario(ventanaRegistro.this, usuario);

        //int resInsert = usuarioDAO.borrarTabla(ventanaRegistro.this);

        if(resInsert == 1){
            Toast.makeText(ventanaRegistro.this, "OK Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(ventanaRegistro.this, "NO OK", Toast.LENGTH_SHORT).show();
        }
        */
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Write your message here.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}