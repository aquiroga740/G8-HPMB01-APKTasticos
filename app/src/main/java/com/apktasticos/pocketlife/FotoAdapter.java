package com.apktasticos.pocketlife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/** Adaptador que dibuja cada fila (miniatura + título) de la lista de fotos. */
public class FotoAdapter extends ArrayAdapter<Foto> {

    public FotoAdapter(@NonNull Context context, @NonNull List<Foto> fotos) {
        super(context, 0, fotos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_foto, parent, false);
        }
        Foto foto = getItem(position);
        ImageView ivMiniatura = convertView.findViewById(R.id.ivMiniatura);
        TextView tvTituloFoto = convertView.findViewById(R.id.tvTituloFoto);

        ivMiniatura.setImageResource(foto.getRecurso());
        tvTituloFoto.setText(foto.getTitulo());
        return convertView;
    }
}
