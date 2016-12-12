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
import android.widget.TextView;
import android.widget.Toast;

import br.unifor.matricula.R;
import br.unifor.matricula.interfaces.OnLoginInteractionListener;


public class Login extends Fragment implements View.OnClickListener {

    private EditText etEmail;
    private EditText etSenha;
    private TextView tvCadastreSe;
    private Button btLogin;

    private OnLoginInteractionListener mListener;

    public Login() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_layout, container, false);
        etEmail = (EditText) view.findViewById(R.id.et_login_email);
        etSenha = (EditText) view.findViewById(R.id.et_login_senha);
        tvCadastreSe = (TextView) view.findViewById(R.id.tv_login_cadastro);
        btLogin = (Button) view.findViewById(R.id.bt_login_Entrar);
        tvCadastreSe.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
      switch (view.getId()) {
        case R.id.tv_login_cadastro:
          mListener.exibirCadastro();
          break;
        case R.id.bt_login_Entrar:
          boolean ehValido = true;
          String email = etEmail.getText().toString();
          if (!mListener.validarEmail(email)) {
            etEmail.setError("Email inválido");
            ehValido = false;
          }
          String senha = etSenha.getText().toString();
          if (!mListener.validarSenha(senha)) {
            etSenha.setError("Senha inválida");
            ehValido = false;
          }
          if (ehValido) {
            int idUsuario = mListener.validarLogin(email, senha);
            if (idUsuario == 0) {
              Snackbar.make(view,
                  "Usuário ou senha errados",
                  Snackbar.LENGTH_SHORT).show();
            } else {
              mListener.login(idUsuario);
            }
          } else {
            Snackbar.make(view,
                "Usuário ou senha inválidos",
                Snackbar.LENGTH_SHORT).show();
          }
          break;
      }
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
}
