package com.example.musicplayer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.musicplayer.Dominio.Usuario;
import com.example.musicplayer.Persistencia.ImagenDAO;
import com.example.musicplayer.Persistencia.UsuarioDAO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class activity_menu_principal extends AppCompatActivity {

    private BottomNavigationView navigation;
    private String nombre_usuario_registrado;
    private Toast notification;

    private UsuarioDAO gestor_perfil = new UsuarioDAO();
    private ImagenDAO gestor_imagen = new ImagenDAO();
    private Usuario usuario_sistema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        inicializarPasoDatos();

        //Inicializacion datos navegacion

        inicializarDatosNavegacion();

        //Muestra el Fragmento de inicio el primero

        openInitialFragment();

    }

    /**
     *
     * Descripcion: Metodo que permite identificar el usuario en el sistema gracias al parametro que
     * se pasa de la ventana anterior
     *
     */
    private void inicializarPasoDatos(){

        Bundle bundle = getIntent().getExtras();
        this.nombre_usuario_registrado = bundle.getString("nombre_usuario_registrado");


        notification = Toast.makeText(this, "Bienvenido a Music Player BBDD Multimedia "
                + this.nombre_usuario_registrado, Toast.LENGTH_LONG);
        notification.show();

    }

    /**
     *
     * Descripcion: Metodo para inicializar los datos respectivos a la navegacion del sistema
     *
     */
    private void inicializarDatosNavegacion(){

        navigation = findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    /**
     *
     * Descripcion: Inicializa con el fragmento inicial del sistema
     *
     */
    private void  openInitialFragment() {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framgment_layout, new fragment_inicio()).commit();

    }

    /**
     *
     * Descripcion: Permite la navegacion entre paneles
     *
     */
    private final BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
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

                     Intent i = new Intent(activity_menu_principal.this, ventanaCanciones.class );
                     startActivity(i);
                     /**
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framgment_layout, new fragment_canciones()).commit();
                    **/
                    break;
                case R.id.perfil:

                    usuario_sistema = inicializarDatosPerfil();
                    Bitmap imagen_perfil = inicializarImagenPerfil();

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framgment_layout, new fragment_perfil(usuario_sistema, imagen_perfil)).commit();
                    break;
            }
            return false;
        }
    };

    /**
     *
     * Descripcion: Obtener los datos tipo BLOB de la base de datos
     *
     * @return bitmap
     */
    private Bitmap inicializarImagenPerfil(){

        Bitmap bitmap = gestor_perfil.buscarImagen(activity_menu_principal.this, nombre_usuario_registrado,
             "ImagenPerfil");

        return bitmap;

    }

    /**
     *
     * Descripcion: Metodo para inicializar el usuario que ha iniciado sesion del sistema
     *
     * @return objeto tipo usuario
     */
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
        String nombre_foto_perfil = gestor_perfil.buscarDatosUsuarioRegistrado(activity_menu_principal.this,
                nombre_usuario_registrado, "ImagenPerfil");

        usuario = new Usuario(nombre_usuario, nombre, password, telefono, correo_electronico, fecha_nacimiento, nombre_foto_perfil);

        return usuario;
    }
}