package br.unifor.matricula;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by koitim on 11/12/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Fragment fragmento = null;

    protected void exibeTela(int activity, int tela) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (fragmento != null) {
            ft.remove(fragmento);
        }
        carregaFragmento(tela);
        ft.add(activity, fragmento);
        ft.commit();
    }

    protected abstract void carregaFragmento(int tela);
}
