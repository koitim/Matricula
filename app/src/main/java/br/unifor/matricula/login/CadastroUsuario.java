package br.unifor.matricula.login;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.unifor.matricula.R;
import br.unifor.matricula.interfaces.OnLoginInteractionListener;
import br.unifor.matricula.model.Usuario;


public class CadastroUsuario extends Fragment implements View.OnClickListener {

    private EditText etNome;
    private EditText etEmail;
    private EditText etSenha;
    private Button btCadastro;

    private OnLoginInteractionListener mListener;

    public CadastroUsuario() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_cadastro_usuario_layout, container, false);
        etNome = (EditText) view.findViewById(R.id.et_cadastro_nome);
        etEmail = (EditText) view.findViewById(R.id.et_cadastro_email);
        etSenha = (EditText) view.findViewById(R.id.et_cadastro_senha);
        btCadastro = (Button) view.findViewById(R.id.bt_cadastro);
        btCadastro.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginInteractionListener) {
            mListener = (OnLoginInteractionListener) context;
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

    @Override
    public void onClick(View view) {
      boolean ehValido = true;
      String nome = etNome.getText().toString();
      if (!mListener.validarNome(nome)) {
        etNome.setError("Nome inválido");
        ehValido = false;
      }
      String email = etEmail.getText().toString();
      if (!mListener.validarEmail(email)) {
        etNome.setError("Email inválido");
        ehValido = false;
      }
      String senha = etSenha.getText().toString();
      if (!mListener.validarSenha(senha)) {
        etNome.setError("Senha inválida");
        ehValido = false;
      }
      if (ehValido) {
        if (mListener.cadastrar(nome, email, senha)) {
          Toast.makeText(getActivity().getApplicationContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_LONG).show();
          mListener.exibirLogin();
        } else {
          Snackbar.make(view,
              "Email já cadastrado",
              Snackbar.LENGTH_LONG).show();
        }
      } else {
        Snackbar.make(view,
            "Dados inválidos",
            Snackbar.LENGTH_LONG).show();
      }
    }
}
