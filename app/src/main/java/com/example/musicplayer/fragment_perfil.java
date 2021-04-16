package com.example.musicplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.musicplayer.Dominio.Usuario;
import com.example.musicplayer.Persistencia.ConexionSQLiteHelper;
import com.example.musicplayer.Persistencia.UsuarioDAO;

/**
 *
 */
public class fragment_perfil extends Fragment {

    //Variables para la BBDD

    private Usuario usuario;

    private TextView lblNombre_usuario_perfil_BBDD;
    private TextView lblNombre_perfil_BBDD;
    private TextView lblTelefono_perfil_BBDD;
    private TextView lblCorreoElectronico_perfil_BBDD;
    private TextView lblFechaNacimiento_perfil_BBDD;

    public fragment_perfil(Usuario usuario) {

        this.usuario = usuario;

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

        Button btn_Configuracion_Avanzada = v.findViewById(R.id.btn_Configuracion_Avanzada);
        btn_Configuracion_Avanzada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Debug_bienvenido","Se pulsó el botón");

                Intent i = new Intent(getActivity(), Configuracion.class );
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
        lblNombre_perfil_BBDD = (TextView) v.findViewById(R.id.lblNombre_perfil_BBDD);
        lblTelefono_perfil_BBDD = (TextView) v.findViewById(R.id.lblTelefono_perfil_BBDD);
        lblCorreoElectronico_perfil_BBDD = (TextView) v.findViewById(R.id.lblCorreo_perfil_BBDD);
        lblFechaNacimiento_perfil_BBDD = (TextView) v.findViewById(R.id.lblFechaNacimiento_perfil_BBDD);

        lblNombre_usuario_perfil_BBDD.setText(this.usuario.getNombreUsuario());
        lblNombre_perfil_BBDD.setText(" "+this.usuario.getNombre());
        lblTelefono_perfil_BBDD.setText(" "+this.usuario.getTelefono());
        lblCorreoElectronico_perfil_BBDD.setText(" "+this.usuario.getCorreo());
        lblFechaNacimiento_perfil_BBDD.setText(" "+this.usuario.getFechaNacimiento());

        return v;

    }


}