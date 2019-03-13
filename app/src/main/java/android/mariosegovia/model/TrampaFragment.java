package android.mariosegovia.model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mariosegovia.android.geoapp.GeoFragment;
import com.mariosegovia.android.geoapp.R;

public class TrampaFragment extends Fragment {
    private TextView mRespuesta;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trampa, container, false);

        mRespuesta = view.findViewById(R.id.respuesta_text_view);
        boolean respuestaEsCorrecta = getActivity().getIntent().getBooleanExtra
                (GeoFragment.EXTRA_RESPUESTA_ES_CORRECTA,false);
        mRespuesta.setText(respuestaEsCorrecta ? "Correcta" : "Incorrecta");

        return view;
    }
}
