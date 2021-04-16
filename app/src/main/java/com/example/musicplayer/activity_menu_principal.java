package com.example.musicplayer;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.musicplayer.Dominio.Usuario;
import com.example.musicplayer.Persistencia.UsuarioDAO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class activity_menu_principal extends AppCompatActivity {

    private BottomNavigationView navigation;
    private String nombre_usuario_registrado;

    private UsuarioDAO gestor_perfil = new UsuarioDAO();
    private Usuario usuario_sistema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        Bundle bundle = getIntent().getExtras();
        this.nombre_usuario_registrado = bundle.getString("nombre_usuario_registrado");

        Toast notification;
        notification = Toast.makeText(this, "Bienvenido a Music Player BBDD Multimedia "
                + this.nombre_usuario_registrado, Toast.LENGTH_LONG);
        notification.show();

        //Inicializacion datos navegacion

        navigation = findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Muestra el Fragmento de inicio el primero

        openInitialFragment();

    }

    private void  openInitialFragment() {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framgment_layout, new fragment_inicio()).commit();

    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch(item.getItemId()){
                case R.id.inicio:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framgment_layout, new fragment_inicio()).commit();
                    break;
                case R.id.artistas:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framgment_layout, new fragment_artistas()).commit();
                    break;
                case R.id.albumes:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framgment_layout, new fragment_albumes()).commit();
                    break;
                case R.id.canciones:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framgment_layout, new fragment_canciones()).commit();
                    break;
                case R.id.perfil:

                    usuario_sistema = inicializarDatosPerfil();

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framgment_layout, new fragment_perfil(usuario_sistema)).commit();
                    break;
            }
            return false;
        }
    };

    private Usuario inicializarDatosPerfil(){

        Usuario usuario;

        String nombre_usuario = nombre_usuario_registrado;

        String nombre = gestor_perfil.buscarDatosUsuarioRegistrado(activity_menu_principal.this,
                nombre_usuario_registrado, "Nombre");
        String password = gestor_perfil.buscarDatosUsuarioRegistrado(activity_menu_principal.this,
                nombre_usuario_registrado,"Password");
        String telefono = gestor_perfil.buscarDatosUsuarioRegistrado(activity_menu_principal.this,
                nombre_usuario_registrado,"Telefono");
        String correo_electronico = gestor_perfil.buscarDatosUsuarioRegistrado(activity_menu_principal.this,
                nombre_usuario_registrado,"CorreoElectronico");
        String fecha_nacimiento = gestor_perfil.buscarDatosUsuarioRegistrado(activity_menu_principal.this,
                nombre_usuario_registrado,"FechaNacimiento");

        usuario = new Usuario(nombre_usuario, nombre, password, telefono, correo_electronico, fecha_nacimiento);

        return usuario;
    }
}