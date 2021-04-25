package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.musicplayer.Adaptadores.AdaptadorListaCancion;
import com.example.musicplayer.Dominio.Cancion;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ventanaCanciones extends AppCompatActivity {

    private ArrayList<Cancion> canciones;
    private RecyclerView lstCanciones;
    private AdaptadorListaCancion adaptador_canciones;

    private String [] items;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_canciones);

        //ActivityCompat.requestPermissions(ventanaCanciones.this, new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE},111);

        MediaPlayer mp = MediaPlayer.create(this,R.raw.avicii);
        mp.start();
        // Obtenemos una referencia a la lista grafica
        //lstCanciones = findViewById(R.id.lstArtistas);
        listView = findViewById(R.id.listViewSong);
        /**
        // Crear la lista de contactos y anadir algunos datos de prueba
        canciones = new ArrayList<Cancion>();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        lstCanciones.setLayoutManager(mLayoutManager);

        adaptador_canciones = new AdaptadorListaCancion(canciones);
        lstCanciones.setAdapter(adaptador_canciones);
            */
        // Metodo que rellena el array con datos de prueba
        runTimePermission();
        //rellenarDatos();
    }

    public void runTimePermission(){

        Dexter.withContext(this).withPermission(READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        //rellenarDatos();

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

    }

    public ArrayList<File> findSong (File file){

        ArrayList<File> arrayList = new ArrayList<>();

        File[] files = file.listFiles();

        if(files != null){
            Log.d("Debug_Excepcion","HO");
            for(File singlefile: files){

                if(singlefile.isDirectory() && !singlefile.isHidden()){

                    arrayList.addAll(findSong(singlefile));

                }
                else{
                    if(singlefile.getName().endsWith(".mp3") || singlefile.getName().endsWith(".wav")){

                        arrayList.add(singlefile);

                    }
                }

            }
        }
        else{
            Log.d("Debug_Excepcion","VACIO");
        }


        return arrayList;
    }

     void rellenarDatos(){

        final ArrayList<File> mis_canciones = findSong(ventanaCanciones.this.getExternalFilesDir("/storage/emulated/0/Android/data"));

        items = new String[mis_canciones.size()];

        for(int i = 0; i<mis_canciones.size(); i++){

            items[i] = mis_canciones.get(i).getName().toString().replace(".mp3", "").replace(".wav","");

        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ventanaCanciones.this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(myAdapter);
    }
}