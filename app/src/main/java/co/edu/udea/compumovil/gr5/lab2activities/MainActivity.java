package co.edu.udea.compumovil.gr5.lab2activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    //TODO: Implementar el guardado de datos con saveInstance, para los datos personales. Aunque creo que es en el Login.
    //TODO: Generar el listview desde la db.
    Fragment fragmentoGenerico;
    FragmentManager fragmentManager;
    SharedPreferences sharedPref ;
    SharedPreferences.Editor editor;

    Intent logInIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logInIntent= getIntent();
        sharedPref = this.getSharedPreferences(user.PREF_FILE_NAME,MODE_PRIVATE);
        editor = sharedPref.edit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(fab!=null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newPlace = new Intent(MainActivity.this, create_place.class);
                    startActivity(newPlace);

                }
            });
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentoGenerico = null;
        fragmentManager = getSupportFragmentManager();
        fragmentoGenerico=new lugaresFragment();
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content_main, fragmentoGenerico)
                    .commit();
        }
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




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
         fragmentoGenerico = null;
         fragmentManager = getSupportFragmentManager();

        int id = item.getItemId();

        if (id == R.id.nav_places) {
            // Handle the camera action
            fragmentoGenerico=new lugaresFragment();
        } else if (id == R.id.nav_profile) {
            Bundle bundle = new Bundle();
            bundle.putString(user.Column.USER, logInIntent.getExtras().getString(user.Column.USER));
            bundle.putString(user.Column.EMAIL, logInIntent.getExtras().getString(user.Column.EMAIL));
            fragmentoGenerico= new perfilFragment();
            fragmentoGenerico.setArguments(bundle);
            if(logInIntent!=null) {
                Log.d("Profile",logInIntent.getExtras().getString(user.Column.USER));
                Log.d("Profile",logInIntent.getExtras().getString(user.Column.EMAIL));

                /*
                tvUsernameProfile.setText(logInIntent.getExtras().getString(user.Column.USER));
                tvEmailProfile.setText(logInIntent.getExtras().getString(user.Column.EMAIL));
                */
            }
        } else if (id == R.id.nav_about) {
            fragmentoGenerico = new acercaDeFragment();
        } else if (id == R.id.nav_log_oout) {
            // false on logout
            Log.d("Logging - Log out",Boolean.toString(sharedPref.getBoolean(getString(R.string.userlogged),true)));
            editor.putBoolean(getString(R.string.userlogged),false);
            Log.d("Logging - Log out",Boolean.toString(sharedPref.getBoolean(getString(R.string.userlogged),true)));
            editor.commit();
            editor.apply();

            finish();
        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content_main, fragmentoGenerico)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
