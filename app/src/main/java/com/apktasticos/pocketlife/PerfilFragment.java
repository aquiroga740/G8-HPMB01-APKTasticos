package com.apktasticos.pocketlife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/** Fragmento DERECHO: perfil de una persona (dentro de un ScrollView). */
public class PerfilFragment extends Fragment {

    private ImageView ivFotoPerfil;
    private TextView tvNombre, tvCargo, tvEstudios, tvExperiencia, tvPendientes, tvBalance;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivFotoPerfil  = view.findViewById(R.id.ivFotoPerfil);
        tvNombre      = view.findViewById(R.id.tvNombre);
        tvCargo       = view.findViewById(R.id.tvCargo);
        tvEstudios    = view.findViewById(R.id.tvEstudios);
        tvExperiencia = view.findViewById(R.id.tvExperiencia);
        tvPendientes  = view.findViewById(R.id.tvPendientes);
        tvBalance     = view.findViewById(R.id.tvBalance);

        // Evento: clic sobre la foto de perfil
        ivFotoPerfil.setOnClickListener(v ->
                Toast.makeText(requireContext(), tvNombre.getText(), Toast.LENGTH_SHORT).show());
    }
}
