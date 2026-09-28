package com.apktasticos.pocketlife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/** Fragmento DERECHO: lista de imágenes con scroll; al seleccionar una se muestra su descripción. */
public class FotosFragment extends Fragment {

    private ListView lvFotos;
    private TextView tvDescripcionFoto;
    private ArrayList<Foto> listaFotos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fotos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvFotos = view.findViewById(R.id.lvFotos);
        tvDescripcionFoto = view.findViewById(R.id.tvDescripcionFoto);

        listaFotos = new ArrayList<>();
        listaFotos.add(new Foto(R.drawable.foto_1, "Playa", "Descripción de la foto de la playa."));
        listaFotos.add(new Foto(R.drawable.foto_2, "Montaña", "Descripción de la foto de la montaña."));
        listaFotos.add(new Foto(R.drawable.foto_3, "Ciudad", "Descripción de la foto de la ciudad."));
        listaFotos.add(new Foto(R.drawable.foto_4, "Bosque", "Descripción de la foto del bosque."));
        lvFotos.setAdapter(new FotoAdapter(requireContext(), listaFotos));

        // Evento: selección de un elemento de la lista
        lvFotos.setOnItemClickListener((parent, v, position, id) ->
                mostrarDescripcion(listaFotos.get(position)));
    }

    private void mostrarDescripcion(Foto foto) {
        tvDescripcionFoto.setText(foto.getTitulo() + ": " + foto.getDescripcion());
        Toast.makeText(requireContext(), foto.getTitulo(), Toast.LENGTH_SHORT).show();
    }
}
