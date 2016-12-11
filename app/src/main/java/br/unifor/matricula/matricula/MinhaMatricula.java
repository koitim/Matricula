package br.unifor.matricula.matricula;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.unifor.matricula.R;
import br.unifor.matricula.interfaces.OnLoginInteractionListener;
import br.unifor.matricula.interfaces.OnMatriculaInteractionListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class MinhaMatricula extends Fragment implements View.OnClickListener {

    private Button btVoltar;

    private OnMatriculaInteractionListener mListener;


    public MinhaMatricula() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matricula_minha_matricula_layout, container, false);
        btVoltar = (Button) view.findViewById(R.id.bt_matricula_voltar);
        btVoltar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        mListener.exibirHome();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMatriculaInteractionListener) {
            mListener = (OnMatriculaInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
