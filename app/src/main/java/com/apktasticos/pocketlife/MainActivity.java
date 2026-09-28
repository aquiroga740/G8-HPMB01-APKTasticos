package com.apktasticos.pocketlife;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * Actividad principal. Aloja el fragmento izquierdo (MenuFragment, declarado en el XML)
 * y reemplaza dinámicamente el fragmento derecho según la opción elegida.
 */
public class MainActivity extends AppCompatActivity
        implements MenuFragment.OnOpcionSeleccionadaListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            mostrarFragmento(new PerfilFragment()); // pantalla inicial
        }
    }

    // Evento: el usuario eligió una opción del menú
    @Override
    public void onOpcionSeleccionada(int opcion) {
        Fragment fragmento;
        switch (opcion) {
            case MenuFragment.FOTOS:    fragmento = new FotosFragment();   break;
            case MenuFragment.VIDEO:    fragmento = new VideoFragment();   break;
            case MenuFragment.WEB:      fragmento = new WebFragment();     break;
            case MenuFragment.BOTONES:  fragmento = new BotonesFragment(); break;
            default:                    fragmento = new PerfilFragment();  break;
        }
        mostrarFragmento(fragmento);
    }

    private void mostrarFragmento(Fragment fragmento) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor_derecho, fragmento)
                .commit();
    }
}
