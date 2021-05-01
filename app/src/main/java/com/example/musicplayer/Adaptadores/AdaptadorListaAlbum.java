package com.example.musicplayer.Adaptadores;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.Dominio.Album;
import com.example.musicplayer.Interfaz.OnItemSelectedListener;
import com.example.musicplayer.R;
import com.example.musicplayer.Presentacion.ventana_canciones;

import java.util.ArrayList;

public class AdaptadorListaAlbum extends RecyclerView.Adapter<AdaptadorListaAlbum.ViewHolder> {

    private ArrayList<Album> albumes;
    private OnItemSelectedListener itemSelectedListener;

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNombreAlbum;
        private TextView lblNombreArtistaAlbum;
        private ImageView imgAlbum;

        ViewHolder(View view) {

            super(view);

            lblNombreAlbum = view.findViewById(R.id.lblNombrePlayListCancion);
            lblNombreArtistaAlbum = view.findViewById(R.id.lblDuracionCancionPlayList);
            imgAlbum = view.findViewById(R.id.imgPlayList);

        }
    }

    /**
     *
     * Descripcion: Constructor clase AdaptadorListaAlbum
     *
     * @param albumes
     */
    public AdaptadorListaAlbum(ArrayList<Album> albumes){

        this.albumes = albumes;

    }

    @Override
    public AdaptadorListaAlbum.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_artistas, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(AdaptadorListaAlbum.ViewHolder holder, int position) {

        holder.lblNombreAlbum.setText(albumes.get(position).getNombreAlbum());
        holder.lblNombreArtistaAlbum.setText(albumes.get(position).getDuracionAlbum());
        holder.imgAlbum.setImageBitmap(albumes.get(position).getImagenAlbum());

        /**
         *
         * Descripcion: Oyente asociado al pulsar un item de la lista
         *
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.lblNombreAlbum.getText().toString() != "No disponible"){

                    Intent canciones_asociadas = new Intent(holder.itemView.getContext(), ventana_canciones.class);
                    canciones_asociadas.putExtra("identificador_album", albumes.get(position).getIdAlbum());
                    holder.itemView.getContext().startActivity(canciones_asociadas);

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
        return albumes.size();
    }
}