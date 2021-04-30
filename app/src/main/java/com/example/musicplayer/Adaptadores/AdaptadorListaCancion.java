package com.example.musicplayer.Adaptadores;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.Dominio.Cancion;
import com.example.musicplayer.Interfaz.OnItemSelectedListener;
import com.example.musicplayer.R;
import com.example.musicplayer.ventana_reproducir;

import java.util.ArrayList;

public class AdaptadorListaCancion extends RecyclerView.Adapter<AdaptadorListaCancion.ViewHolder> {

    private ArrayList<Cancion> canciones;
    private OnItemSelectedListener itemSelectedListener;

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNombre;
        private TextView lblDuracion;
        private ImageView imagCancion;

        ViewHolder(View view) {

            super(view);

            lblNombre = view.findViewById(R.id.lblNombrePlayListCancion);
            lblDuracion = view.findViewById(R.id.lblDuracionCancionPlayList);
            imagCancion = view.findViewById(R.id.imgPlayList);

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
        holder.imagCancion.setImageBitmap(canciones.get(position).getImagenCancion());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.lblNombre.getText().toString() != "No disponible"){
                    Intent intent = new Intent(holder.itemView.getContext(), ventana_reproducir.class);
                    intent.putExtra("identificador_cancion", canciones.get(position).getIdCancion());
                    holder.itemView.getContext().startActivity(intent);
                }

            }
        });
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

    public void setItemSelectedListener(OnItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }

    @Override
    public int getItemCount() {
        return canciones.size();
    }
}
