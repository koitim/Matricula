package br.unifor.matricula.matricula;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.unifor.matricula.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

  public Home() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.matricula_home_layout, container, false);
  }

}
