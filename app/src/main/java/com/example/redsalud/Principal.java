package com.example.redsalud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Inicio
        Inicio i = new Inicio();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,i).commit();

        //Toolbar
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        //Tablayout
        TabLayout tl = (TabLayout)  findViewById(R.id.tablayout);
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        Inicio i = new Inicio();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,i).commit();
                        Toast.makeText(getApplicationContext(), "Vas a inicio", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Perfil p = new Perfil();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,p).commit();
                        Toast.makeText(getApplicationContext(), "Vas a perfil", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        AgendarCita a = new AgendarCita();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,a).commit();
                        Toast.makeText(getApplicationContext(), "Vas a agendar cita", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        //Menu Lateral
        NavigationView nav = (NavigationView) findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.op01){
                    Perfil p = new Perfil();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,p).commit();
                    Toast.makeText(getApplicationContext(), "Vas a datos personales", Toast.LENGTH_SHORT).show();
                } else if(id==R.id.op02){
                    AgendarCita a = new AgendarCita();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,a).commit();
                    Toast.makeText(getApplicationContext(), "Vas a agendar cita", Toast.LENGTH_SHORT).show();
                } else if(id==R.id.op03){
                    EvaluarSalud e = new EvaluarSalud();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,e).commit();
                    Toast.makeText(getApplicationContext(), "Vas a evalua tu salud", Toast.LENGTH_SHORT).show();
                } else if(id==R.id.op04){
                    CentroMedicoLista c = new CentroMedicoLista();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,c).commit();
                    Toast.makeText(getApplicationContext(), "Vas a centros de salud", Toast.LENGTH_SHORT).show();
                } else if(id==R.id.op05){
                    SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = datos.edit();
                    editor.remove("correo");
                    editor.apply();
                    finish();
                }
                DrawerLayout dl = (DrawerLayout) findViewById(R.id.principal);
                dl.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        DrawerLayout dl = (DrawerLayout) findViewById(R.id.principal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                dl,
                R.string.abrirDrawer,
                R.string.cerrarDrawer
        );
        dl.addDrawerListener(toggle);
        toggle.syncState();
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dl.isDrawerOpen(GravityCompat.START)){
                    dl.closeDrawer(GravityCompat.START);
                }else{
                    dl.openDrawer((int) Gravity.START);
                }
            }
        });
    }

}