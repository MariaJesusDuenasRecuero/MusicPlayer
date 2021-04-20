package com.example.musicplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musicplayer.Dominio.Usuario;

/**
 *
 */
public class fragment_perfil extends Fragment {

    private Usuario usuario;
    private Bitmap bitmap;

    private TextView lblNombre_usuario_perfil_BBDD;
    private TextView nombrePerfilTitulo;
    private TextView CorreoPerfilTitulo;
    private TextView TelefonoPerfilTitulo;
    private TextView FechaNacimientoPerfilTitulo;
    private ImageView image_foto_perfil;

    public fragment_perfil(Usuario usuario, Bitmap bitmap) {

        this.usuario = usuario;
        this.bitmap = bitmap;

    }
    /**
     * Called when a fragment is first attached to its context.
     * {@link #onCreate(Bundle)} will be called after this.
     */

    boolean mCalled;
    android.app.Fragment mHost;

    public void onAttach(Context context) {
        super.onAttach(context);
        mCalled = true;
        final Activity hostActivity = mHost == null ? null : mHost.getActivity();
        if (hostActivity != null) {
            mCalled = false;
            onAttach(hostActivity);
        }
    }

    /**
     * @deprecated Use {@link #onAttach(Context)} instead.
     */
    @Deprecated
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCalled = true;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        lblNombre_usuario_perfil_BBDD = (TextView) v.findViewById(R.id.lblNombreUsuario);

        Button btn_Configuracion_Avanzada = v.findViewById(R.id.btn_Configuracion_Avanzada);
        btn_Configuracion_Avanzada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Configuracion.class );
                i.putExtra("nombre_usuario_registrado", lblNombre_usuario_perfil_BBDD.getText().toString());
                startActivity(i);

            }
        });

        Button btn_Cerrar_Sesion = v.findViewById(R.id.btn_Cerrar_Sesion);
        btn_Cerrar_Sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), MainActivity.class );
                startActivity(i);

            }
        });

        lblNombre_usuario_perfil_BBDD = (TextView) v.findViewById(R.id.lblNombreUsuario);
        nombrePerfilTitulo = (TextView) v.findViewById(R.id.nombrePerfilTitulo);
        CorreoPerfilTitulo = (TextView) v.findViewById(R.id.CorreoPerfilTitulo);
        TelefonoPerfilTitulo = (TextView) v.findViewById(R.id.TelefonoPerfilTitulo);
        FechaNacimientoPerfilTitulo = (TextView) v.findViewById(R.id.FechaNacimientoPerfilTitulo);
        image_foto_perfil =  (ImageView) v.findViewById(R.id.imageView_foto_perfil);

        lblNombre_usuario_perfil_BBDD.setText(this.usuario.getNombreUsuario());
        nombrePerfilTitulo.setText("Nombre: "+this.usuario.getNombre());
        TelefonoPerfilTitulo.setText("Telefono: "+this.usuario.getTelefono());
        CorreoPerfilTitulo.setText("Correo: "+this.usuario.getCorreo());
        FechaNacimientoPerfilTitulo.setText("F. Nacimiento: "+this.usuario.getFechaNacimiento());

        image_foto_perfil.setImageBitmap(this.bitmap);

        return v;

    }


}