package com.example.musicplayer;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class activity_menu_principal extends AppCompatActivity {

    fragment_inicio inicio = new fragment_inicio();
    fragment_artistas artistas = new fragment_artistas();
    fragment_albumes albumes = new fragment_albumes();
    fragment_canciones canciones = new fragment_canciones();
    fragment_perfil perfil = new fragment_perfil();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemReselectedListener((BottomNavigationView.OnNavigationItemReselectedListener) mOnNavigationItemSelectedListener);

        loadFragment(inicio);

    }
    private final  BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()){
                case R.id.inicio:
                    loadFragment(inicio);
                    return true;
                case R.id.artistas:
                    loadFragment(artistas);
                    return true;
                case R.id.albumes:
                    loadFragment(albumes);
                    return true;
                case R.id.canciones:
                    loadFragment(canciones);
                    return true;
                case R.id.perfil:
                    loadFragment(perfil);
                    return true;
            }
            return false;
        }
    };
    public void loadFragment (Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}