package com.example.musicplayer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.musicplayer.Dominio.Usuario;
import com.example.musicplayer.Persistencia.UsuarioDAO;
import com.example.musicplayer.Presentacion.fragment_inicio;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class menu_principal extends AppCompatActivity {

    public static String usuario_sesion_iniciada;

    private BottomNavigationView navigation;
    private String nombre_usuario_registrado;
    private Toast notification;

    private UsuarioDAO gestor_perfil = new UsuarioDAO();
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
        usuario_sesion_iniciada = bundle.getString("nombre_usuario_registrado");

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

                    Intent ventana_artistas = new Intent(menu_principal.this, com.example.musicplayer.ventana_artistas.class );
                    startActivity(ventana_artistas);

                    break;
                case R.id.albumes:

                    Intent ventana_album = new Intent(menu_principal.this, com.example.musicplayer.ventana_album.class );
                    startActivity(ventana_album);

                    break;
                case R.id.canciones:

                     Intent ventana_canciones = new Intent(menu_principal.this, com.example.musicplayer.ventana_canciones.class );
                     ventana_canciones.putExtra("identificador_album", "");
                     startActivity(ventana_canciones);

                    break;
                case R.id.perfil:

                    usuario_sistema = inicializarDatosPerfil();
                    //Bitmap imagen_perfil = inicializarImagenPerfil();

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framgment_layout, new fragment_perfil(usuario_sistema)).commit();
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

        Bitmap bitmap = gestor_perfil.buscarImagen(menu_principal.this, nombre_usuario_registrado,
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

        String nombre = gestor_perfil.buscarDatosUsuarioRegistrado(menu_principal.this,
                nombre_usuario_registrado, "Nombre");
        String password = gestor_perfil.buscarDatosUsuarioRegistrado(menu_principal.this,
                nombre_usuario_registrado,"Password");
        String telefono = gestor_perfil.buscarDatosUsuarioRegistrado(menu_principal.this,
                nombre_usuario_registrado,"Telefono");
        String correo_electronico = gestor_perfil.buscarDatosUsuarioRegistrado(menu_principal.this,
                nombre_usuario_registrado,"CorreoElectronico");
        String fecha_nacimiento = gestor_perfil.buscarDatosUsuarioRegistrado(menu_principal.this,
                nombre_usuario_registrado,"FechaNacimiento");
        Bitmap foto_perfil = gestor_perfil.buscarImagen(menu_principal.this,
                nombre_usuario_registrado, "ImagenPerfil");

        usuario = new Usuario(nombre_usuario, nombre, password, telefono, correo_electronico, fecha_nacimiento, foto_perfil);

        return usuario;
    }
}