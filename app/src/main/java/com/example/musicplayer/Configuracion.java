package com.example.musicplayer;

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

import com.example.musicplayer.Persistencia.ImagenDAO;
import com.example.musicplayer.Persistencia.UsuarioDAO;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;

public class Configuracion extends AppCompatActivity {

    private String nombre_usuario_registrado;
    private UsuarioDAO gestor_usuario_configuracion = new UsuarioDAO();
    private ImagenDAO gestor_imagenes_perfil = new ImagenDAO();
    private ImagenDAO gestor_imagen = new ImagenDAO();

    private EditText txtNombre;
    private EditText txtCorreo;
    private EditText txtPassword;

    private EditText txtTelefono;
    private EditText txtFechaNacimiento;

    private ImageView imageView;

    private Random r = new Random();
    private int valor = 0;
    private String nombre_foto = "Imagen";
    private Toast notification;

    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        Bundle bundle = getIntent().getExtras();
        this.nombre_usuario_registrado = bundle.getString("nombre_usuario_registrado");


        this.txtNombre = findViewById(R.id.txtCambiar_nombre);
        this.txtCorreo = findViewById(R.id.txtCambiar_email);
        this.txtPassword = findViewById(R.id.txtCambiar_Password);

        this.txtTelefono = findViewById(R.id.txtCambiar_Telefono);
        this.txtFechaNacimiento = findViewById(R.id.txtCambiar_FechaNacimiento);

        this.imageView = (ImageView) findViewById(R.id.imageViewConfiguracion);

        this.valor = r.nextInt(500)+1;

        this.nombre_foto = this.nombre_foto+this.valor;

        Button btnCambiarFoto = findViewById(R.id.btnCambiarFoto);
        btnCambiarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityCompat.requestPermissions(
                        Configuracion.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );


            }
        });

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    //String comprobar_nombre_imagen = gestor_imagenes_perfil.buscarDatosImagen(Configuracion.this,nombre_foto, "NombreImagen");

                    //Log.d("Nombre", comprobar_nombre_imagen);


                    gestor_usuario_configuracion.updateDataImagen(Configuracion.this, nombre_usuario_registrado, imageViewToByte(imageView));


                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();

                    imageView.setImageResource(R.mipmap.ic_launcher);


                    //imageView.setImageBitmap(gestor_imagen.buscarImagen(Configuracion.this,"Imagen3", "ContenidoImagen"));

                }

                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        Button btnBorrarCuenta = findViewById(R.id.btnBorrarCuenta);
        btnBorrarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(Configuracion.this)
                        .setTitle("¿Está seguro que desea elimar su cuenta?")
                        .setMessage("Si elima la cuenta se perderan todos sus datos.")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                gestor_usuario_configuracion.eliminarUsuario(Configuracion.this, nombre_usuario_registrado);
                                Intent i = new Intent(Configuracion.this, MainActivity.class);
                                startActivity(i);
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


        Button btn_ModificarDatos = findViewById(R.id.btnModificarDatos);
        btn_ModificarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtNombre.getText().toString().equals("")) {

                    Toast notification;
                    notification = Toast.makeText(Configuracion.this, "Nombre no cambiado", Toast.LENGTH_LONG);
                    notification.show();

                } else {
                    gestor_usuario_configuracion.updateParametroUsuario(Configuracion.this,
                            nombre_usuario_registrado, "Nombre", txtNombre.getText().toString());

                    Toast notification;
                    notification = Toast.makeText(Configuracion.this, "Nuevo dato: "
                            + gestor_usuario_configuracion.buscarDatosUsuarioRegistrado(Configuracion.this, nombre_usuario_registrado, "Nombre"), Toast.LENGTH_LONG);
                    notification.show();


                }
                if (txtCorreo.getText().toString().equals("")) {

                    Toast notification;
                    notification = Toast.makeText(Configuracion.this, "Correo no cambiado", Toast.LENGTH_LONG);
                    notification.show();

                } else {
                    gestor_usuario_configuracion.updateParametroUsuario(Configuracion.this,
                            nombre_usuario_registrado, "CorreoElectronico", txtCorreo.getText().toString());

                    Toast notification;
                    notification = Toast.makeText(Configuracion.this, "Nuevo dato: "
                            + gestor_usuario_configuracion.buscarDatosUsuarioRegistrado(Configuracion.this, nombre_usuario_registrado, "CorreoElectronico"), Toast.LENGTH_LONG);
                    notification.show();


                }
                if (txtPassword.getText().toString().equals("")) {

                    Toast notification;
                    notification = Toast.makeText(Configuracion.this, "Password no cambiado", Toast.LENGTH_LONG);
                    notification.show();

                } else {
                    gestor_usuario_configuracion.updateParametroUsuario(Configuracion.this,
                            nombre_usuario_registrado, "Password", txtPassword.getText().toString());

                    Toast notification;
                    notification = Toast.makeText(Configuracion.this, "Nuevo dato: "
                            + gestor_usuario_configuracion.buscarDatosUsuarioRegistrado(Configuracion.this, nombre_usuario_registrado,
                            "Password"), Toast.LENGTH_LONG);
                    notification.show();


                }
                if(txtTelefono.getText().toString().equals("")){

                    Toast notification;
                    notification = Toast.makeText(Configuracion.this, "Telefono no cambiado", Toast.LENGTH_LONG);
                    notification.show();

                }
                else {
                    gestor_usuario_configuracion.updateParametroUsuario(Configuracion.this,
                            nombre_usuario_registrado, "Telefono", txtTelefono.getText().toString());

                    Toast notification;
                    notification = Toast.makeText(Configuracion.this, "Nuevo dato: "
                            + gestor_usuario_configuracion.buscarDatosUsuarioRegistrado(Configuracion.this, nombre_usuario_registrado,
                            "Telefono"), Toast.LENGTH_LONG);
                    notification.show();

                }

                if(txtFechaNacimiento.getText().toString().equals("")){

                    Toast notification;
                    notification = Toast.makeText(Configuracion.this, "Fecha Nacimeinto no cambiado", Toast.LENGTH_LONG);
                    notification.show();

                }
                else {
                    gestor_usuario_configuracion.updateParametroUsuario(Configuracion.this,
                            nombre_usuario_registrado, "FechaNacimiento", txtFechaNacimiento.getText().toString());

                    Toast notification;
                    notification = Toast.makeText(Configuracion.this, "Nuevo dato: "
                            + gestor_usuario_configuracion.buscarDatosUsuarioRegistrado(Configuracion.this, nombre_usuario_registrado,
                            "FechaNacimiento"), Toast.LENGTH_LONG);
                    notification.show();

                }
            }
        });

    }

    public void oyente_eliminarUsuario(View view) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

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
}