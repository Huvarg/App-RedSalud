package com.example.redsalud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Principal extends AppCompatActivity {

    private TextView nombreHeader, emailHeader;
    private ImageView urlHeader;
    private FirebaseAuth auth;
    private DatabaseReference database;
    private String idUser;

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
                        ProfesionalArea a = new ProfesionalArea();
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
                    ProfesionalArea a = new ProfesionalArea();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,a).commit();
                    Toast.makeText(getApplicationContext(), "Vas a agendar cita", Toast.LENGTH_SHORT).show();
                } else if(id==R.id.op03){
                    CentroMedicoLista c = new CentroMedicoLista();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,c).commit();
                    Toast.makeText(getApplicationContext(), "Vas a centros de salud", Toast.LENGTH_SHORT).show();
                } else if(id==R.id.op04){
                    SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = datos.edit();
                    editor.remove("idUser");
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
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

        //Header de Usuario
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        idUser = auth.getCurrentUser().getUid();

        //View header = nav.inflateHeaderView(R.layout.header);
        View header = nav.getHeaderView(0);
        urlHeader = header.findViewById(R.id.imgUrlHeader);
        nombreHeader = header.findViewById(R.id.textNombreHeader);
        emailHeader = header.findViewById(R.id.textEmailHeader);

        database.child("Usuario").child(idUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String urlImg = snapshot.child("urlImg").getValue().toString();
                    String nombre = snapshot.child("nombre").getValue().toString();
                    String apellido = snapshot.child("apellido").getValue().toString();
                    String email = snapshot.child("correo").getValue().toString();
                    Glide.with(getBaseContext())
                            .load(urlImg)
                            .centerCrop()
                            .fitCenter()
                            .into(urlHeader);
                    nombreHeader.setText(nombre+" "+apellido);
                    emailHeader.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Fallo de lectura: " + error.getCode());
            }
        });
    }

}