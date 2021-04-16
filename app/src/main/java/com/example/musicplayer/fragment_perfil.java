package com.example.musicplayer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

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