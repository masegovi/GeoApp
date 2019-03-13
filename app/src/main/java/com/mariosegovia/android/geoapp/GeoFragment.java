package com.mariosegovia.android.geoapp;

import android.content.Intent;
import android.mariosegovia.model.BancoDePreguntas;
import android.mariosegovia.model.Pregunta;
import android.mariosegovia.model.TrampaActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GeoFragment extends Fragment {
    public static final String EXTRA_RESPUESTA_ES_CORRECTA = "com.mariosegovia.android.geoapp.respuestaEsCorrecta";
    private TextView mTextoPregunta;
    private Button mBotonCierto;
    private Button mBotonFalso;
    private Button mBotonAnterior;
    private Button mBotonSiguiente;
    private Button mBotonVerRespuesta;

    private BancoDePreguntas mBanco;
    private Pregunta mPreguntaActual;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_geo, container, false);

        mTextoPregunta = view.findViewById(R.id.texto_pregunta);
        mBotonSiguiente = view.findViewById(R.id.boton_siguiente);
        mBotonCierto = view.findViewById(R.id.boton_cierto);
        mBotonFalso = view.findViewById(R.id.boton_falso);
        mBotonAnterior = view.findViewById(R.id.boton_anterior);
        mBotonVerRespuesta = view.findViewById(R.id.boton_ver_respuesta);

        crearBancoDePreguntas();
        actualizarPregunta();

        mBotonCierto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarRespuesta(true);
            }
        });
        mBotonFalso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarRespuesta(false);
            }
        });
        mBotonAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPreguntaActual = mBanco.previous();
                actualizarPregunta();
            }
        });
        mBotonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPreguntaActual = mBanco.next();
                actualizarPregunta();
            }
        });
        mBotonVerRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean respuestaEsCorrecta =
                        mPreguntaActual.isRespuestaVerdadera();
                Intent intent = new Intent(getActivity(), TrampaActivity.class);
                intent.putExtra(EXTRA_RESPUESTA_ES_CORRECTA, respuestaEsCorrecta);
                startActivity(intent);
            }
        });
        return view;
    }

    private void crearBancoDePreguntas() {
        mBanco = new BancoDePreguntas();
        mBanco.add(new Pregunta(R.string.texto_pregunta_1, false));
        mBanco.add(new Pregunta(R.string.texto_pregunta_2, true));
        mBanco.add(new Pregunta(R.string.texto_pregunta_3, true));
        mBanco.add(new Pregunta(R.string.texto_pregunta_4, true));
        mBanco.add(new Pregunta(R.string.texto_pregunta_5, false));
        mPreguntaActual = mBanco.get(0);
    }

    private void actualizarPregunta() {
        mTextoPregunta.setText(mPreguntaActual.getIdResTexto());
    }

    private void verificarRespuesta(boolean botonOprimido) {
        boolean respuesta = mPreguntaActual.isRespuestaVerdadera();
        if (botonOprimido == respuesta) {
            Toast.makeText(getActivity(),
                    R.string.texto_respuesta_correcta,
                    Toast.LENGTH_SHORT)
                    .show();
        }
        else {
            Toast.makeText(getActivity(),
                    R.string.texto_respuesta_incorrecta,
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }
    }

