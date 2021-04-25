package com.example.musicplayer.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.Configuracion;
import com.example.musicplayer.Dominio.Artista;
import com.example.musicplayer.Dominio.Cancion;
import com.example.musicplayer.Persistencia.ArtistaDAO;
import com.example.musicplayer.R;
import com.example.musicplayer.ventanaArtistas;

import java.util.ArrayList;

public class AdaptadorListaArtista extends RecyclerView.Adapter<AdaptadorListaArtista.ViewHolder> {

    private ArrayList<Artista> artistas;

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNombreArtista;
        private TextView lblGenero;
        private ImageView imgArtista;

        ViewHolder(View view) {

            super(view);

            lblNombreArtista = view.findViewById(R.id.lblNombreArtista);
            lblGenero = view.findViewById(R.id.lblGenero);
            imgArtista = view.findViewById(R.id.imgArtista);

        }
    }

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

        Artista artista = artistas.get(position);

        ArtistaDAO gestor_artista = new ArtistaDAO();

        holder.lblNombreArtista.setText(artistas.get(position).getNombreArtista());
        holder.lblGenero.setText(artistas.get(position).getTipo());
        holder.imgArtista.setImageBitmap(artistas.get(position).getImagenArtista());

        /**
        switch (artistas.get(position).getIdArtista()){


             case "1":

                holder.imgArtista.setImageBitmap(artistas.get(position).getImagenArtista());

                break;

             case "2":
                 holder.imgArtista.setImageBitmap(artistas.get(position).getImagenArtista());
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
        return artistas.size();
    }
}