package com.example.redsalud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.redsalud.Modelo.Profesional;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetalleProfesional extends AppCompatActivity {

    private ImageView url;
    private TextView nombre, especialidad, areaMedica, centroSalud;
    private Button btnEliminar, btnIr, btnModificarP;
    private Profesional p;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_profesional);

        //Inicializamos el objeto firebaseDatabase
        database = FirebaseDatabase.getInstance().getReference();
        //Referenciamos los views
        url = (ImageView) findViewById(R.id.p_img);
        nombre = (TextView) findViewById(R.id.p_nombre_apellido);
        especialidad = (TextView) findViewById(R.id.p_especialidad);
        areaMedica = (TextView) findViewById(R.id.p_area);
        centroSalud = (TextView) findViewById(R.id.p_CentroSalud);
        btnIr = (Button) findViewById(R.id.btnIrCS);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        Bundle paquete = getIntent().getExtras();
        if(paquete!=null){
            p = (Profesional) paquete.getSerializable("profesional");
            Glide.with(getApplicationContext())
                    .load(p.getRuta())
                    .centerCrop()
                    .fitCenter()
                    .into(url);
            nombre.setText(p.getNombre().toString()+" "+p.getApellido().toString());
            especialidad.setText(p.getEspecialidad().toString());
            areaMedica.setText(p.getAreaMedica().toString());

            database = FirebaseDatabase.getInstance().getReference("CentroSalud");
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String idCentroSalud = ds.getKey();
                            if (idCentroSalud.equals(p.getIdCentroMedico())) {
                                String nombre = ds.child("nombre").getValue(String.class);
                                centroSalud.setText(nombre);
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println("Fallo de lectura: " + error.getCode());
                }
            });
        }

        btnIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance().getReference("CentroSalud");
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                String idCentroSalud = ds.getKey();
                                if (idCentroSalud.equals(p.getIdCentroMedico())) {
                                    String nombre = ds.child("nombre").getValue(String.class);
                                    Double lat = ds.child("lat").getValue(Double.class);
                                    Double log = ds.child("log").getValue(Double.class);

                                    Intent intent = new Intent(DetalleProfesional.this, CentroSaludGoogleMap.class);
                                    intent.putExtra("centro-salud-nombre", nombre);
                                    intent.putExtra("centro-salud-lat", lat);
                                    intent.putExtra("centro-salud-log", log);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println("Fallo de lectura: " + error.getCode());
                    }
                });
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance().getReference("Profesional");
                database.child(p.getIdProfesional()).removeValue();
                Toast.makeText(DetalleProfesional.this.getApplication(), "Se ha eliminado con exito a " +p.getNombre()+ " " +p.getApellido(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetalleProfesional.this, Principal.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //Metodo volver atras
    public void volverAtras(View view) {
        onBackPressed();
    }

    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ActualizarProfesional.class);
        intent.putExtra("idprofesional", p.getIdProfesional());
        startActivity(intent);
    }

}