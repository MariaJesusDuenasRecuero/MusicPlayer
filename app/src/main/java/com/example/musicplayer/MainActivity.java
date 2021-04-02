package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.musicplayer.Dominio.Usuario;
import com.example.musicplayer.Persistencia.ConexionSQLiteHelper;
import com.example.musicplayer.Persistencia.UsuarioDAO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInsertar = findViewById(R.id.btnInsertarRegistro);

        //ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "nombre_base_de_datos",null,1);

    }

    public void oyente_btnRealizarRegistro(View view){

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario("6", "User6","User6@gmail.com");

        int resInsert = usuarioDAO.insertarUsuario(MainActivity.this, usuario);

        if(resInsert == 1){
            Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "NO OK", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     *
     * Descripcion: Oyente asociado al boton Registro que te permite ir a la actividad para Registrarse
     *
     * @param view
     */
    public void oyente_btnRegristroAplicacion(View view){

        Intent i = new Intent(this, ventanaRegistro.class );
        startActivity(i);

    }
}