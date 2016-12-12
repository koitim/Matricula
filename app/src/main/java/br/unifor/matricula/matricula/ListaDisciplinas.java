package br.unifor.matricula.matricula;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.unifor.matricula.R;
import br.unifor.matricula.adapter.DisciplinasAdapter;
import br.unifor.matricula.dao.DisciplinaDAO;
import br.unifor.matricula.interfaces.OnLoginInteractionListener;
import br.unifor.matricula.interfaces.OnMatriculaInteractionListener;
import br.unifor.matricula.model.Disciplina;
import br.unifor.matricula.model.Usuario;
import br.unifor.matricula.util.SpacesItemDecoration;

public class ListaDisciplinas extends Fragment {

  private List<Disciplina> disciplinas;
  private RecyclerView rvDisciplinas;
  private DisciplinasAdapter mAdapter;
  private OnMatriculaInteractionListener mListener;

  public ListaDisciplinas() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    DisciplinaDAO disciplinaDAO = new DisciplinaDAO(getActivity().getApplicationContext());
    disciplinas = disciplinaDAO.findDisciplianasNaoMatriculadas(getActivity().getApplicationContext(), mListener.getUsuario());
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.matricula_lista_disciplinas_layout, container, false);

    rvDisciplinas = (RecyclerView) view.findViewById(R.id.rv_matricula_listar_disciplinas);
    rvDisciplinas.addItemDecoration(new SpacesItemDecoration(2));
    rvDisciplinas.setHasFixedSize(true);

    int scrollPosition = 0;
    if (rvDisciplinas.getLayoutManager() != null) {
      scrollPosition = ((LinearLayoutManager) rvDisciplinas.getLayoutManager())
          .findFirstCompletelyVisibleItemPosition();
    }

    rvDisciplinas.setLayoutManager(new LinearLayoutManager(getActivity()));
    rvDisciplinas.scrollToPosition(scrollPosition);
    rvDisciplinas.setItemAnimator(new DefaultItemAnimator());

    mAdapter = new DisciplinasAdapter(disciplinas, mListener);
    rvDisciplinas.setAdapter(mAdapter);

    return view;
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
