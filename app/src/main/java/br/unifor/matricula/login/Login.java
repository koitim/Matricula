package br.unifor.matricula.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        View view = inflater.inflate(R.layout.login_principal_layout, container, false);
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
                String email = etEmail.getText().toString();
                //TODO: Tratar os erros
                mListener.validarEmail(email);
                String senha = etSenha.getText().toString();
                //TODO: Tratar os erros
                mListener.validarSenha(senha);
                //TODO: Tratar os erros
                mListener.validarLogin(email, senha);
                mListener.login(etEmail.getText().toString(), etSenha.getText().toString());
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
