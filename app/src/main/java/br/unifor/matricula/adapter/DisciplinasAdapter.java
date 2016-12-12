package br.unifor.matricula.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.unifor.matricula.R;
import br.unifor.matricula.interfaces.OnMatriculaInteractionListener;
import br.unifor.matricula.matricula.Principal;
import br.unifor.matricula.model.Disciplina;

/**
 * Created by koitim on 11/12/16.
 */

public class DisciplinasAdapter extends RecyclerView.Adapter<DisciplinasAdapter.ViewHolder>{

  private List<Disciplina> disciplinas;

  private OnMatriculaInteractionListener mListener;

  public DisciplinasAdapter(List<Disciplina> disciplinas, OnMatriculaInteractionListener mListener) {
    this.disciplinas = disciplinas;
    this.mListener = mListener;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    View v = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.matricula_disciplina_row_item, viewGroup, false);
    return new ViewHolder(v, mListener, disciplinas);
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int position) {
    viewHolder.setTextView(disciplinas.get(position));
  }

  @Override
  public int getItemCount() {
    return disciplinas.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvDisciplina;

    public ViewHolder(View v, final OnMatriculaInteractionListener mListener, final List<Disciplina> disciplinas) {
      super(v);
      if (mListener.getTelaAtual() == Principal.LISTA_DISCIPLINAS) {
        v.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            mListener.exibirMatricular(disciplinas.get(getAdapterPosition()));
          }
        });
      }
      tvDisciplina = (TextView) v.findViewById(R.id.tv_matricula_disciplina_item);
    }

    public void setTextView(Disciplina disciplina) {
      tvDisciplina.setText(disciplina.toString());
    }
  }
}
