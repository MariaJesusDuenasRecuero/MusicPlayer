package com.example.musicplayer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_perfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_perfil extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBERY
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String nombre_usuario_registrado;

    // TODO: Rename and change types of parameters
    private String nombre_usuario_registrado_BBDD;
    private String mParam2;

    public fragment_perfil(String nombre_usuario_registrado) {

        this.nombre_usuario_registrado_BBDD = nombre_usuario_registrado;
        Log.d("Debug_bienvenido","Valor recibido:"+this.nombre_usuario_registrado_BBDD);

        //this.nombre_usuario_registrado = nombre_usuario_registrado;

        //lblNombreUsuario = lblNombreUsuario.findViewById(R.id.NombreUsuarioPerfil);

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_perfil.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_perfil newInstance(String param1, String param2) {
        fragment_perfil fragment = new fragment_perfil(nombre_usuario_registrado);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombre_usuario_registrado = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    TextView lblNombreUsuario;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        lblNombreUsuario = (TextView) v.findViewById(R.id.lblNombreUsuario);

        lblNombreUsuario.setText(this.nombre_usuario_registrado_BBDD);

        return v;

    }

    public void setTitleText(String text) {
        lblNombreUsuario.setText(text);
    }
}