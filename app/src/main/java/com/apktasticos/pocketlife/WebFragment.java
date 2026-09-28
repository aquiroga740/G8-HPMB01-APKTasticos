package com.apktasticos.pocketlife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/** Fragmento DERECHO: carga la página web cuya URL se escribe en una caja de texto. */
public class WebFragment extends Fragment {

    private EditText etUrl;
    private Button btnIr;
    private WebView wvPagina;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etUrl = view.findViewById(R.id.etUrl);
        btnIr = view.findViewById(R.id.btnIr);
        wvPagina = view.findViewById(R.id.wvPagina);

        wvPagina.setWebViewClient(new WebViewClient()); // abre los enlaces dentro del WebView
        wvPagina.getSettings().setJavaScriptEnabled(true);

        // Eventos: botón "Ir" y tecla "Go" del teclado
        btnIr.setOnClickListener(v -> cargarUrl());
        etUrl.setOnEditorActionListener((tv, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                cargarUrl();
                return true;
            }
            return false;
        });
    }

    private void cargarUrl() {
        String url = etUrl.getText().toString().trim();
        if (url.isEmpty()) {
            Toast.makeText(requireContext(), "Escribe una URL", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        wvPagina.loadUrl(url);
    }
}
