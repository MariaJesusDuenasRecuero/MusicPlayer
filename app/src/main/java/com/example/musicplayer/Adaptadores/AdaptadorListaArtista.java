package com.example.musicplayer.Adaptadores;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.Presentacion.ventana_detalles_artistas;
import com.example.musicplayer.Dominio.Artista;
import com.example.musicplayer.Interfaz.OnItemSelectedListener;
import com.example.musicplayer.Persistencia.ArtistaDAO;
import com.example.musicplayer.R;

import java.util.ArrayList;

public class AdaptadorListaArtista extends RecyclerView.Adapter<AdaptadorListaArtista.ViewHolder> {

    private ArrayList<Artista> artistas;
    private OnItemSelectedListener itemSelectedListener;

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNombreArtista;
        private TextView lblGenero;
        private ImageView imgArtista;

        ViewHolder(View view) {

            super(view);

            lblNombreArtista = view.findViewById(R.id.lblNombrePlayListCancion);
            lblGenero = view.findViewById(R.id.lblDuracionCancionPlayList);
            imgArtista = view.findViewById(R.id.imgPlayList);

        }
    }

    /**
     *
     * Descripcion: Constructor clase AdaptadorListaArtista
     *
     * @param artistas
     */
    public AdaptadorListaArtista(ArrayList<Artista> artistas){

        this.artistas = artistas;

    }

    @Override
    public AdaptadorListaArtista.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_artistas, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(AdaptadorListaArtista.ViewHolder holder, int position) {

        holder.lblNombreArtista.setText(artistas.get(position).getNombreArtista());
        holder.lblGenero.setText(artistas.get(position).getTipo());
        holder.imgArtista.setImageBitmap(artistas.get(position).getImagenArtista());

        /**
         *
         * Descripcion: Oyente asociado al pulsar un item de la lista
         *
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.lblNombreArtista.getText().toString() != "No disponible"){

                    Intent informacion_artista = new Intent(holder.itemView.getContext(), ventana_detalles_artistas.class);
                    informacion_artista.putExtra("identificador_artista", artistas.get(position).getIdArtista());
                    holder.itemView.getContext().startActivity(informacion_artista);

                }
            }
        });
    }

    /**
     *
     * @param itemSelectedListener
     */
    public void setItemSelectedListener(OnItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }

    @Override
    public int getItemCount() {
        return artistas.size();
    }
}