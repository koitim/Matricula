package br.unifor.matricula.matricula;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import br.unifor.matricula.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Matricular extends Fragment implements View.OnClickListener {

    private TextView tvDisciplina;
    private Button btMatricular;


    public Matricular() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.matricula_matricular_layout, container, false);
        tvDisciplina = (TextView) rootView.findViewById(R.id.tv_matricular_disciplina);
        btMatricular = (Button) rootView.findViewById(R.id.bt_matricular);
        btMatricular.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        //TODO: Efetivar matricula chamando algum método na activity e ir para a lista de disciplinas matriculadas do aluno
    }
}
