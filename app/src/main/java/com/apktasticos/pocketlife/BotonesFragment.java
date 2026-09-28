package com.apktasticos.pocketlife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/** Fragmento DERECHO: ejemplo de botones, lista, eventos y widgets (gestor de tareas). */
public class BotonesFragment extends Fragment {

    private EditText etNuevaTarea;
    private Button btnAgregar, btnEliminar;
    private ListView lvTareas;
    private TextView tvContador;
    private SwitchCompat swRecordatorio;

    private ArrayList<Tarea> listaTareas;
    private ArrayList<String> titulos;
    private ArrayAdapter<String> adaptador;
    private int siguienteId = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_botones, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etNuevaTarea   = view.findViewById(R.id.etNuevaTarea);
        btnAgregar     = view.findViewById(R.id.btnAgregar);
        btnEliminar    = view.findViewById(R.id.btnEliminar);
        lvTareas       = view.findViewById(R.id.lvTareas);
        tvContador     = view.findViewById(R.id.tvContador);
        swRecordatorio = view.findViewById(R.id.swRecordatorio);

        listaTareas = new ArrayList<>();
        titulos = new ArrayList<>();
        adaptador = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_multiple_choice, titulos);
        lvTareas.setAdapter(adaptador);

        agregarTarea("Comprar víveres");
        agregarTarea("Enviar informe");
        agregarTarea("Llamar al cliente");

        // Eventos
        btnAgregar.setOnClickListener(v -> {
            agregarTarea(etNuevaTarea.getText().toString().trim());
            etNuevaTarea.setText("");
        });
        lvTareas.setOnItemClickListener((parent, v, position, id) -> {
            listaTareas.get(position).setCompletada(lvTareas.isItemChecked(position));
            actualizarContador();
        });
        btnEliminar.setOnClickListener(v -> eliminarCompletadas());
        swRecordatorio.setOnCheckedChangeListener((boton, activo) ->
                Toast.makeText(requireContext(),
                        activo ? "Recordatorio activado" : "Recordatorio desactivado",
                        Toast.LENGTH_SHORT).show());
    }

    private void agregarTarea(String titulo) {
        if (titulo.isEmpty()) {
            Toast.makeText(requireContext(), "Escribe el título de la tarea", Toast.LENGTH_SHORT).show();
            return;
        }
        listaTareas.add(new Tarea(siguienteId++, titulo));
        titulos.add(titulo);
        adaptador.notifyDataSetChanged();
        actualizarContador();
    }

    private void eliminarCompletadas() {
        for (int i = listaTareas.size() - 1; i >= 0; i--) {
            if (listaTareas.get(i).isCompletada()) {
                listaTareas.remove(i);
                titulos.remove(i);
            }
        }
        lvTareas.clearChoices();
        adaptador.notifyDataSetChanged();
        actualizarContador();
    }

    private void actualizarContador() {
        int pendientes = 0;
        for (Tarea t : listaTareas) {
            if (!t.isCompletada()) pendientes++;
        }
        tvContador.setText("Pendientes: " + pendientes);
    }
}
