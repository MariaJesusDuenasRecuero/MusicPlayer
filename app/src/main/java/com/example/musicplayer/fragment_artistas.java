package com.example.musicplayer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_artistas#newInstance} factory method to
 * create an instance of this fragment.
 */

public class fragment_artistas extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    AdapterArtista adapterArtista;
    RecyclerView recyclerviewArtistas;
    ArrayList <Artista> listaArtistas;
    public fragment_artistas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_artistas.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_artistas newInstance(String param1, String param2) {
        fragment_artistas fragment = new fragment_artistas();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_artistas, container, false);
        recyclerviewArtistas = view.findViewById(R.id.recyclerViewArtistas);
        listaArtistas = new ArrayList<>();
        //cargar la lista
        cargarLista();
        
        //mostrar los datos
        mostrarDatos();
        return view;
    }

    private void mostrarDatos() {
        recyclerviewArtistas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterArtista = new AdapterArtista(getContext(), listaArtistas);
        recyclerviewArtistas.setAdapter(adapterArtista);

        adapterArtista.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String nombre = listaArtistas.get(recyclerviewArtistas.getChildAdapterPosition(v)).getNombre();
                Toast.makeText(getContext(), "Artista: " + nombre, Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void cargarLista() {
        listaArtistas.add(new Artista("Edurne","Edurne García Almagro  conocida como Edurne, es una cantante, compositora, modelo, actriz y presentadora española de televisión. Se dio a conocer como finalista de la cuarta edición del concurso Operación Triunfo emitido en Telecinco. Albums: Premiere, Nueva piel, Climax, Catarsis…",R.drawable.edurne));
        listaArtistas.add(new Artista("Melendi","Ramón Melendi Espina conocido artísticamente como Melendi, es un cantautor y compositor español de música pop y rumba.1Albumes: que el cielo espere sentao, sin noticias de Holanda , mientras no cueste trabajo volvamos a empezar , lagrimas desordenadas, un alumno más, quítate las gafas, ahora…",R.drawable.melendi));
        listaArtistas.add(new Artista("Avicci","Tim Bergling más conocido por su nombre artístico Avicii, fue un DJ y compositor sueco, especializado en programación de audio, remezcla y producción de discos.",R.drawable.avici));
    }
}