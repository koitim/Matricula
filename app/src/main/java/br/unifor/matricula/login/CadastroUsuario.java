package br.unifor.matricula.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.unifor.matricula.R;
import br.unifor.matricula.interfaces.OnLoginInteractionListener;


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
        String nome = etNome.getText().toString();
        //TODO: Tratar os erros
        mListener.validarNome(nome);
        String email = etEmail.getText().toString();
        //TODO: Tratar os erros
        mListener.validarEmail(email);
        String senha = etSenha.getText().toString();
        //TODO: Tratar os erros
        mListener.validarSenha(senha);
        //TODO: Tratar os erros
        mListener.cadastrar(nome, email, senha);
        mListener.exibirLogin();

    }
}
