package com.apktasticos.pocketlife;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/** Fragmento IZQUIERDO: menú con las cinco opciones. */
public class MenuFragment extends Fragment {

    public static final int PERFIL = 0, FOTOS = 1, VIDEO = 2, WEB = 3, BOTONES = 4;

    /** Contrato para avisarle a la actividad qué opción se eligió. */
    public interface OnOpcionSeleccionadaListener {
        void onOpcionSeleccionada(int opcion);
    }

    private OnOpcionSeleccionadaListener listener;
    private TextView tvMenuPerfil, tvMenuFotos, tvMenuVideo, tvMenuWeb, tvMenuBotones;
    private TextView[] items;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnOpcionSeleccionadaListener) {
            listener = (OnOpcionSeleccionadaListener) context;
        } else {
            throw new RuntimeException(context + " debe implementar OnOpcionSeleccionadaListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Vínculo variable <-> identificador del XML
        tvMenuPerfil  = view.findViewById(R.id.tvMenuPerfil);
        tvMenuFotos   = view.findViewById(R.id.tvMenuFotos);
        tvMenuVideo   = view.findViewById(R.id.tvMenuVideo);
        tvMenuWeb     = view.findViewById(R.id.tvMenuWeb);
        tvMenuBotones = view.findViewById(R.id.tvMenuBotones);
        items = new TextView[]{tvMenuPerfil, tvMenuFotos, tvMenuVideo, tvMenuWeb, tvMenuBotones};

        // Eventos onClick de cada opción
        for (int i = 0; i < items.length; i++) {
            final int opcion = i;
            items[i].setOnClickListener(v -> seleccionar(opcion));
        }
        marcarActivo(PERFIL);
    }

    private void seleccionar(int opcion) {
        marcarActivo(opcion);
        listener.onOpcionSeleccionada(opcion);
    }

    private void marcarActivo(int opcion) {
        for (int i = 0; i < items.length; i++) {
            boolean activo = (i == opcion);
            items[i].setBackgroundColor(activo ? Color.parseColor("#0F6E56") : Color.TRANSPARENT);
            items[i].setTextColor(activo ? Color.WHITE : Color.parseColor("#5F5E5A"));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
