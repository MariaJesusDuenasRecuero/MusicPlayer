package com.example.musicplayer.Adaptadores;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.Dominio.Cancion;
import com.example.musicplayer.Interfaz.OnItemSelectedListener;
import com.example.musicplayer.Persistencia.PlayListDAO;
import com.example.musicplayer.R;
import com.example.musicplayer.menu_principal;

import java.util.ArrayList;

public class AdaptadorListaPlayList extends RecyclerView.Adapter<AdaptadorListaPlayList.ViewHolder> {

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

    /**
     *
     * Descripcion: Constructor clase AdaptadorListaPlayList
     *
     * @param canciones
     */
    public AdaptadorListaPlayList(ArrayList<Cancion> canciones){

        this.canciones = canciones;

    }

    @Override
    public AdaptadorListaPlayList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_canciones, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(AdaptadorListaPlayList.ViewHolder holder, int position) {

        holder.lblNombre.setText(canciones.get(position).getNombreCancion());
        holder.lblDuracion.setText(canciones.get(position).getDuracionCancion());
        holder.imagCancion.setImageBitmap(canciones.get(position).getImagenCancion());

        /**
         *
         * Descripcion: Oyente asociado al pulsar un item de la lista
         *
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.lblNombre.getText().toString() != "No disponible"){

                    new AlertDialog.Builder(holder.itemView.getContext())
                            .setTitle("¿Está seguro que desea elimar esta cancion de favoritos?")
                            .setMessage("La cancion se eliminara de esta playlist.")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    PlayListDAO gestor_playlist = new PlayListDAO();

                                    gestor_playlist.eliminarCancionFavoritos(holder.itemView.getContext(),
                                            menu_principal.usuario_sesion_iniciada, canciones.get(position).getIdCancion());

                                    Intent menu_inicio = new Intent(holder.itemView.getContext(), menu_principal.class);
                                    menu_inicio.putExtra("nombre_usuario_registrado",
                                            menu_principal.usuario_sesion_iniciada);

                                    holder.itemView.getContext().startActivity(menu_inicio);

                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
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
        return canciones.size();
    }
}
