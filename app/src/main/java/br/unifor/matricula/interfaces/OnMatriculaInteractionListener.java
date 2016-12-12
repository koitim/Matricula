package br.unifor.matricula.interfaces;

import br.unifor.matricula.model.Disciplina;
import br.unifor.matricula.model.Usuario;

/**
 * Created by koitim on 11/12/2016.
 */

public interface OnMatriculaInteractionListener {
  Usuario getUsuario();
  Disciplina getDisciplinaAMatricular();
  void exibirHome();
  void exibirMatricular(Disciplina disciplina);
  void exibirMinhaMatricula();
  void Matricular(Usuario usuario, Disciplina disciplina);
  int getTelaAtual();
}
