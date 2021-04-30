package com.example.musicplayer.Presentacion;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.musicplayer.R;


public class fragment_inicio extends Fragment {

    private Button btn_play_list;

    /**
     *
     * Descripcion: Constructor vacio
     *
     */
    public fragment_inicio() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        this.btn_play_list = view.findViewById(R.id.btnPlayListFavoritos);

        oyentesBotones();

        return view;

    }

    /**
     *
     * Descripcion: Metodo que contiene los oyentes de los botones del fragment
     *
     */
    private void oyentesBotones(){

        this.btn_play_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ventana_playlist = new Intent(getActivity(), ventana_playlist_favoritos.class);
                startActivity(ventana_playlist);

            }
        });

    }

}