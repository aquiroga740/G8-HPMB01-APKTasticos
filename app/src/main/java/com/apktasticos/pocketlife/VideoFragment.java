package com.apktasticos.pocketlife;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/** Fragmento DERECHO: video con controles de reproducción (MediaController). */
public class VideoFragment extends Fragment {

    // Video de ejemplo en línea. Para uno local: "android.resource://" + paquete + "/" + R.raw.mi_video
    private static final String URL_VIDEO =
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4";

    private VideoView vvVideo;
    private TextView tvTituloVideo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vvVideo = view.findViewById(R.id.vvVideo);
        tvTituloVideo = view.findViewById(R.id.tvTituloVideo);

        MediaController controles = new MediaController(requireContext());
        controles.setAnchorView(vvVideo);
        vvVideo.setMediaController(controles);
        vvVideo.setVideoURI(Uri.parse(URL_VIDEO));

        // Eventos del reproductor
        vvVideo.setOnPreparedListener(mp -> tvTituloVideo.setText("Video listo. Toca la pantalla para ver los controles."));
        vvVideo.setOnErrorListener((mp, what, extra) -> {
            Toast.makeText(requireContext(), "No se pudo reproducir el video", Toast.LENGTH_LONG).show();
            return true;
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (vvVideo != null && vvVideo.isPlaying()) {
            vvVideo.pause();
        }
    }
}
