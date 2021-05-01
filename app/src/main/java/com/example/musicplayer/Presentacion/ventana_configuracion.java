package com.example.musicplayer.Presentacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.musicplayer.Persistencia.PlayListDAO;
import com.example.musicplayer.Persistencia.UsuarioDAO;
import com.example.musicplayer.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ventana_configuracion extends AppCompatActivity {

    private String nombre_usuario_registrado;
    private String [] id_canciones;
    private UsuarioDAO gestor_usuario_configuracion = new UsuarioDAO();
    private PlayListDAO gestor_play_list = new PlayListDAO();

    private EditText txtNombre;
    private EditText txtCorreo;
    private EditText txtPassword;
    private EditText txtTelefono;
    private EditText txtFechaNacimiento;
    private Button btn_ModificarDatos;
    private Button btnBorrarCuenta;
    private Button btnCambiarFoto;
    private Button btnAdd;
    private ImageView imageView;
    private Toast notification;

    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        inicializarDatos();
        oyentesBotones();

    }

    /**
     *
     * Descripcion: Metodo que permite inicializar los datos de la ventana configuracion
     *
     */
    private void inicializarDatos(){

        this.txtNombre = findViewById(R.id.txtCambiar_nombre);
        this.txtCorreo = findViewById(R.id.txtCambiar_email);
        this.txtPassword = findViewById(R.id.txtCambiar_Password);
        this.txtTelefono = findViewById(R.id.txtCambiar_Telefono);
        this.txtFechaNacimiento = findViewById(R.id.txtCambiar_FechaNacimiento);
        this.imageView = (ImageView) findViewById(R.id.imageViewConfiguracion);
        this.btnCambiarFoto = findViewById(R.id.btnCambiarFoto);
        this.btnAdd = findViewById(R.id.btnAdd);
        this.btnBorrarCuenta = findViewById(R.id.btnBorrarCuenta);
        this.btn_ModificarDatos = findViewById(R.id.btnModificarDatos);

        Bundle bundle = getIntent().getExtras();
        this.nombre_usuario_registrado = bundle.getString("nombre_usuario_registrado");

    }

    /**
     *
     * Descripcion: Metodo que contiene los oyentes de los botones asociados a esta ventana
     *
     */
    private void oyentesBotones(){

        /**
         * Descripcion: Oyente que permite acceder a las la fotos de la galeria
         *
         */
        this.btnCambiarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityCompat.requestPermissions(
                        ventana_configuracion.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        /**
         *
         * Descripcion: Oyente que permite aniadir dicha foto a la base de datos y asociarla al
         * usuario
         *
         */
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    gestor_usuario_configuracion.updateDataImagen(ventana_configuracion.this,
                            nombre_usuario_registrado, imageViewToByte(imageView));

                    mostrarNotificacion("Nueva foto de perfil");
                    imageView.setImageResource(R.mipmap.ic_launcher);

                }
                catch (Exception e){

                    e.printStackTrace();

                }
            }
        });

        /**
         *
         * Descripcion: Oyente que permite elimianar un usuario y sus canciones asociadas
         *
         */
        this.btnBorrarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ventana_configuracion.this)
                        .setTitle("¿Está seguro que desea elimar su cuenta?")
                        .setMessage("Si elima la cuenta se perderan todos sus datos.")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                borrarDatosAsociados();

                                gestor_usuario_configuracion.eliminarUsuario(ventana_configuracion.this, nombre_usuario_registrado);
                                Intent ventana_login = new Intent(ventana_configuracion.this, main_activity.class);
                                startActivity(ventana_login );

                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });

        /**
         *
         * Descripcion: Oyente que modifica los datos de un usario registrado en el sistema
         *
         */
        btn_ModificarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtNombre.getText().toString().equals("")) {

                    mostrarNotificacion("Nombre no cambiado");

                }
                else {

                    gestor_usuario_configuracion.updateParametroUsuario(ventana_configuracion.this,
                            nombre_usuario_registrado, "Nombre", txtNombre.getText().toString());

                    mostrarNotificacion("Nuevo dato: " + gestor_usuario_configuracion.buscarDatosUsuarioRegistrado(
                            ventana_configuracion.this, nombre_usuario_registrado, "Nombre"));

                }
                if (txtCorreo.getText().toString().equals("")) {

                    mostrarNotificacion("Correo no cambiado");


                } else {

                    gestor_usuario_configuracion.updateParametroUsuario(ventana_configuracion.this,
                            nombre_usuario_registrado, "CorreoElectronico", txtCorreo.getText().toString());

                    mostrarNotificacion("Nuevo dato: "+ gestor_usuario_configuracion.buscarDatosUsuarioRegistrado(
                            ventana_configuracion.this, nombre_usuario_registrado, "CorreoElectronico"));

                }
                if (txtPassword.getText().toString().equals("")) {

                    mostrarNotificacion("Contrasena no cambiada");

                }
                else {

                    gestor_usuario_configuracion.updateParametroUsuario(ventana_configuracion.this,
                            nombre_usuario_registrado, "Password", txtPassword.getText().toString());

                    mostrarNotificacion("Nuevo dato: "+ gestor_usuario_configuracion.buscarDatosUsuarioRegistrado(
                            ventana_configuracion.this, nombre_usuario_registrado, "Password"));

                }
                if(txtTelefono.getText().toString().equals("")){

                    mostrarNotificacion("Telefono no cambiado");

                }
                else {

                    gestor_usuario_configuracion.updateParametroUsuario(ventana_configuracion.this,
                            nombre_usuario_registrado, "Telefono", txtTelefono.getText().toString());

                    mostrarNotificacion("Nuevo dato: "+ gestor_usuario_configuracion.buscarDatosUsuarioRegistrado(
                            ventana_configuracion.this, nombre_usuario_registrado, "Telefono"));

                }
                if(txtFechaNacimiento.getText().toString().equals("")){

                    mostrarNotificacion("Fecha de nacimiento no cambiado");

                }
                else {

                    gestor_usuario_configuracion.updateParametroUsuario(ventana_configuracion.this,
                            nombre_usuario_registrado, "FechaNacimiento", txtFechaNacimiento.getText().toString());

                    mostrarNotificacion("Nuevo dato: "+ gestor_usuario_configuracion.buscarDatosUsuarioRegistrado(
                            ventana_configuracion.this, nombre_usuario_registrado, "FechaNacimiento"));

                }
            }
        });

    }

    /**
     *
     * Descripcion: Solicitar permisos de galeria Android
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!",
                        Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     *
     * Descripcion: Obtener datos galeria
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    /**
     *
     * Descripcion: Metodo que obtiene las canciones asociadas al usaurio que quiere eliminar su
     * cuenta en el sistema y borra dichas canciones
     *
     */
    private void borrarDatosAsociados(){

        int numero_canciones_usuario = obtenerNumeroCancionesUsuario();

        if(numero_canciones_usuario != 0) {

            this.id_canciones = gestor_play_list.getListaCancionesFavoritas(ventana_configuracion.this,
                    menu_principal.usuario_sesion_iniciada, numero_canciones_usuario);

            for(int i = 0; i < this.id_canciones.length; i++) {

                gestor_play_list.eliminarCancionFavoritos(ventana_configuracion.this,
                        menu_principal.usuario_sesion_iniciada, id_canciones[i]);

            }
        }
        else{

            mostrarNotificacion("Ninguna cancion asociada que eliminar");

        }
    }

    /**
     *
     * Descripcion: Metodo que muestra un aviso al usuario dependiendo de las acciones que este realice
     *
     * @param aviso Mensaje personalizado dependiendo del mensaje del aviso
     * @param context contexto en este caso es ventana_registro.this
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
     * Descripcion: Metodo para obtener un arry de bits a partir de una imagen
     *
     * @param image
     * @return
     */
    public static byte[] imageViewToByte(ImageView image) {

        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;
    }

    /**
     *
     * Descripcion: Metodo que permite obtener el numero total de canciones favoritas de un
     * determinado usuario
     *
     * @return entero con la cantidad de canciones favoritas dado un nombre de usuario
     */
    private int obtenerNumeroCancionesUsuario(){

        return this.gestor_play_list.getNumeroCancionesUsuario(ventana_configuracion.this,
                menu_principal.usuario_sesion_iniciada);

    }

    /**
     *
     * Descripcion: Metodo que notifica al usuario de una accion
     *
     * @param cadena con el mensaje personaliszado dependiendo de la situacion
     */
    private void mostrarNotificacion(String cadena){

        this.notification = Toast.makeText(ventana_configuracion.this, cadena,
                Toast.LENGTH_LONG);

        this.notification.show();

    }
}