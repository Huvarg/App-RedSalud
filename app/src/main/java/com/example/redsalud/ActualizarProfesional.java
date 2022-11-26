package com.example.redsalud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ActualizarProfesional extends AppCompatActivity {

    private ImageView imgProfesional;
    private TextView areaMedicaN;
    private EditText nombreN, apellidoN, especialidadN;
    private Button btnModificar;
    private Profesional p;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_profesional);

        //Inicializamos el objeto firebaseDatabase
        database = FirebaseDatabase.getInstance().getReference();
        //Referenciamos los views
        imgProfesional = (ImageView) findViewById(R.id.imgProfesional);
        areaMedicaN = (TextView) findViewById(R.id.tvAreaMedicaTitulo);
        nombreN = (EditText) findViewById(R.id.etNombreNuevo);
        apellidoN = (EditText) findViewById(R.id.etApellidoNuevo);
        especialidadN = (EditText) findViewById(R.id.etEspecialidadNuevo);
        btnModificar = (Button) findViewById(R.id.btnModificar);

        String idProfesional = getIntent().getExtras().getString("idprofesional");

        database.child("Profesional").child(idProfesional).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String areaMedica = snapshot.child("areaMedica").getValue().toString();
                    String urlImg = snapshot.child("ruta").getValue().toString();
                    String nombre = snapshot.child("nombre").getValue().toString();
                    String apellido = snapshot.child("apellido").getValue().toString();
                    String especialidad = snapshot.child("especialidad").getValue().toString();
                    Glide.with(getBaseContext())
                            .load(urlImg)
                            .centerCrop()
                            .fitCenter()
                            .into(imgProfesional);
                    areaMedicaN.setText(areaMedica);
                    nombreN.setText(nombre);
                    apellidoN.setText(apellido);
                    especialidadN.setText(especialidad);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Fallo de lectura: " + error.getCode());
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = nombreN.getText().toString().trim();
                String apellido = apellidoN.getText().toString().trim();
                String especialidad = especialidadN.getText().toString().trim();

                database.child("Profesional").child(idProfesional).child("nombre").setValue(nombre);
                database.child("Profesional").child(idProfesional).child("apellido").setValue(apellido);
                database.child("Profesional").child(idProfesional).child("especialidad").setValue(especialidad);

                Toast.makeText(ActualizarProfesional.this, "Datos actualizados con exito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ActualizarProfesional.this, Principal.class);
                startActivity(intent);
            }
        });
    }

    //Metodo volver atras
    public void volverAtras(View view) {
        onBackPressed();
    }
}