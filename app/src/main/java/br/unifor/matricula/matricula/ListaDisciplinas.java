package br.unifor.matricula.matricula;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.unifor.matricula.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaDisciplinas extends Fragment {


    public ListaDisciplinas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.matricula_lista_disciplinas_layout, container, false);
    }

}
