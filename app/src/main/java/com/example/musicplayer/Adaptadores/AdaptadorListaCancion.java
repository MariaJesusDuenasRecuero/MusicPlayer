package com.example.musicplayer.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.Dominio.Cancion;
import com.example.musicplayer.R;

import java.util.ArrayList;

public class AdaptadorListaCancion extends RecyclerView.Adapter<AdaptadorListaCancion.ViewHolder> {

    private ArrayList<Cancion> canciones;

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNombre;
        private TextView lblDuracion;
        private ImageView imagCancion;

        ViewHolder(View view) {

            super(view);

            lblNombre = view.findViewById(R.id.lblNombre);
            lblDuracion = view.findViewById(R.id.lblDuracion);
            imagCancion = view.findViewById(R.id.imagCancion);

        }
    }

    public AdaptadorListaCancion(ArrayList<Cancion> canciones){

        this.canciones = canciones;

    }

    @Override
    public AdaptadorListaCancion.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_canciones, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(AdaptadorListaCancion.ViewHolder holder, int position) {

        Cancion cancion = canciones.get(position);

        holder.lblNombre.setText(canciones.get(position).getNombreCancion());
        holder.lblDuracion.setText(canciones.get(position).getDuracionCancion());

        /**
        switch (contactos.get(position).getTipo()){
            case 0: //Cargar imagen de contactos tipo "familia"
                holder.imagContacto.setImageResource(R.drawable.familia);
                break;
            case 1: //Cargar imagen de los contactos tipo "amigos"
                holder.imagContacto.setImageResource(R.drawable.amigo);
                break;
            case 2: //Cargar imagen de los contactos tipo "trabajo"
                holder.imagContacto.setImageResource(R.drawable.trabajo);
        }
         */
    }

    @Override
    public int getItemCount() {
        return canciones.size();
    }
}
