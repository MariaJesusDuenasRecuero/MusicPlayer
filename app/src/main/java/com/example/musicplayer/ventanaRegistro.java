package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.musicplayer.Dominio.Usuario;
import com.example.musicplayer.Persistencia.UsuarioDAO;

public class ventanaRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_registro);
    }

    /**
     *
     * Descripcion: Insertar los datos del usuario en la base de datos para registrarlos
     *
     * @param view
     */
    public void oyente_btnRealizarRegistro(View view){

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario("7", "User7","User7@gmail.com");

        int resInsert = usuarioDAO.insertarUsuario(ventanaRegistro.this, usuario);

        if(resInsert == 1){
            Toast.makeText(ventanaRegistro.this, "OK", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(ventanaRegistro.this, "NO OK", Toast.LENGTH_SHORT).show();
        }
    }

}