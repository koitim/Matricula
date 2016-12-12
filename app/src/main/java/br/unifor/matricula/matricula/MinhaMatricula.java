package br.unifor.matricula.matricula;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.unifor.matricula.R;
import br.unifor.matricula.adapter.DisciplinasAdapter;
import br.unifor.matricula.dao.MatriculaDAO;
import br.unifor.matricula.interfaces.OnLoginInteractionListener;
import br.unifor.matricula.interfaces.OnMatriculaInteractionListener;
import br.unifor.matricula.model.Disciplina;
import br.unifor.matricula.model.Matricula;
import br.unifor.matricula.util.SpacesItemDecoration;


/**
 * A simple {@link Fragment} subclass.
 */
public class MinhaMatricula extends Fragment implements View.OnClickListener {

  private RecyclerView rvMatriculas;
  private Button btVoltar;

  private List<Matricula> matriculas;
  private DisciplinasAdapter mAdapter;
  private OnMatriculaInteractionListener mListener;


  public MinhaMatricula() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    MatriculaDAO matriculaDAO = new MatriculaDAO(getActivity().getApplicationContext());
    matriculas = matriculaDAO.find(mListener.getUsuario());
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.matricula_minha_matricula_layout, container, false);
    btVoltar = (Button) view.findViewById(R.id.bt_matricula_voltar);
    btVoltar.setOnClickListener(this);

    rvMatriculas = (RecyclerView) view.findViewById(R.id.rv_minha_matricula_disciplinas);
    rvMatriculas.addItemDecoration(new SpacesItemDecoration(2));
    rvMatriculas.setHasFixedSize(true);

    int scrollPosition = 0;
    if (rvMatriculas.getLayoutManager() != null) {
      scrollPosition = ((LinearLayoutManager) rvMatriculas.getLayoutManager())
          .findFirstCompletelyVisibleItemPosition();
    }

    rvMatriculas.setLayoutManager(new LinearLayoutManager(getActivity()));
    rvMatriculas.scrollToPosition(scrollPosition);
    rvMatriculas.setItemAnimator(new DefaultItemAnimator());

    mAdapter = new DisciplinasAdapter(getDisciplinas(), mListener);
    rvMatriculas.setAdapter(mAdapter);

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

  private List<Disciplina> getDisciplinas() {
    List<Disciplina> disciplinas = new ArrayList<>();
    for (Matricula matricula:matriculas) {
      disciplinas.add(matricula.getDisciplina());
    }
    return disciplinas;
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }
}
