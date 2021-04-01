package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

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
        Usuario usuario = new Usuario("1", "User1","prueba@gmail.com");

        int resInsert = usuarioDAO.insertarUsuario(MainActivity.this, usuario);

        if(resInsert == 1){
            Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
        }
    }
}