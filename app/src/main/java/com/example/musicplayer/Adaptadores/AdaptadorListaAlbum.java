package com.example.musicplayer.Adaptadores;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.DetallesArtistaActivity;
import com.example.musicplayer.Dominio.Album;
import com.example.musicplayer.Interfaz.OnItemSelectedListener;
import com.example.musicplayer.Persistencia.AlbumDAO;
import com.example.musicplayer.R;

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

            lblNombreAlbum = view.findViewById(R.id.lblNombreAlbum);
            lblNombreArtistaAlbum = view.findViewById(R.id.lblNombreArtistaAlbum);
            imgAlbum = view.findViewById(R.id.imgAlbum);

        }
    }

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

        Album album = albumes.get(position);

        AlbumDAO gestor_album = new AlbumDAO();

        holder.lblNombreAlbum.setText(albumes.get(position).getNombreAlbum());
        holder.lblNombreArtistaAlbum.setText(albumes.get(position).getDuracionAlbum());
        holder.imgAlbum.setImageBitmap(albumes.get(position).getImagenAlbum());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(holder.itemView.getContext(), DetallesArtistaActivity.class);
                //intent.putExtra("identificador_artista", artistas.get(position).getIdArtista());
                //holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    public void setItemSelectedListener(OnItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }

    @Override
    public int getItemCount() {
        return albumes.size();
    }
}