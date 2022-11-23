package com.example.redsalud;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetalleProfesional extends AppCompatActivity {

    private ImageView url;
    private TextView nombre, especialidad, areaMedica;
    private Button btnEliminar;
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
        }

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.child("Profesional").child(p.getKey()).removeValue();
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

}