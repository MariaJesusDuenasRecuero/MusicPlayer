package com.example.musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterArtista extends RecyclerView.Adapter<AdapterArtista.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Artista> model;

    //Listener
    private View.OnClickListener listener;

    public AdapterArtista(Context context , ArrayList<Artista> model ){
        this.inflater =LayoutInflater.from(context);
        this.model= model;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.lista_artistas,parent);
        view.setOnClickListener(this);
        return  new ViewHolder(view);
    }
     public void setOnClickListener(View.OnClickListener listener){
       this.listener = listener;
     }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombre= model.get(position).getNombre();
        String descripcion=model.get(position).getDescripcion();
        int  imagen=model.get(position).getImagenId();

        holder.nombre.setText(nombre);
        holder.descripcion.setText(descripcion);
        holder.imagen.setImageResource(imagen);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, descripcion;
        ImageView imagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.nombreArtista);
            descripcion=itemView.findViewById(R.id.descripcionArtista);
            imagen=itemView.findViewById(R.id.imagenArtista);
        }
    }
}
