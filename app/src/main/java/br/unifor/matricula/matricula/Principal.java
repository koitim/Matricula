package br.unifor.matricula.matricula;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.unifor.matricula.BaseActivity;
import br.unifor.matricula.R;
import br.unifor.matricula.interfaces.OnMatriculaInteractionListener;

public class Principal extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMatriculaInteractionListener {

    private static final int HOME = 1;
    private static final int LISTA_DISCIPLINAS = 2;
    private static final int MINHA_MATRICULA = 3;
    private static final int MATRICULAR = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        exibeTela(HOME);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                exibeTela(HOME);
                break;
            case R.id.nav_lista_disciplinas:
                exibeTela(LISTA_DISCIPLINAS);
                break;
            case R.id.nav_minha_matricula:
                exibeTela(MINHA_MATRICULA);
                break;
            case R.id.nav_logout:
                //TODO:  Confirmar saída
                Intent it = new Intent(this, br.unifor.matricula.login.Principal.class);
                startActivity(it);
                finish();
        };
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void carregaFragmento(int tela) {
        switch (tela) {
            case HOME:
                fragmento = new Home();
                break;
            case LISTA_DISCIPLINAS:
                fragmento = new ListaDisciplinas();
                break;
            case MINHA_MATRICULA:
                fragmento = new MinhaMatricula();
                break;
            case MATRICULAR:
                fragmento = new Matricular();
                break;
        }
    }

    @Override
    public void exibirHome() {
        exibeTela(HOME);
    }
}
