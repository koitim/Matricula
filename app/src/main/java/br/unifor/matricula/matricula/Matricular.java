package br.unifor.matricula.matricula;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import br.unifor.matricula.R;
import br.unifor.matricula.interfaces.OnMatriculaInteractionListener;
import br.unifor.matricula.model.Disciplina;
import br.unifor.matricula.model.Usuario;


public class Matricular extends Fragment implements View.OnClickListener {

  private Usuario usuario;
  private Disciplina disciplina;

  private TextView tvDisciplina;
  private Button btMatricular;
  private OnMatriculaInteractionListener mListener;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    usuario = mListener.getUsuario();
    disciplina = mListener.getDisciplinaAMatricular();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.matricula_matricular_layout, container, false);
    tvDisciplina = (TextView) rootView.findViewById(R.id.tv_matricular_disciplina);
    tvDisciplina.setText(disciplina.toString());
    btMatricular = (Button) rootView.findViewById(R.id.bt_matricular);
    btMatricular.setOnClickListener(this);
    return rootView;
  }

    @Override
    public void onClick(View view) {
      mListener.Matricular(usuario, disciplina);
      mListener.exibirMinhaMatricula();
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
