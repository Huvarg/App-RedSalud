package com.example.redsalud;

import android.content.Intent;
import android.os.Bundle;

<<<<<<< HEAD
=======
import androidx.annotation.NonNull;
>>>>>>> 5f573b4 (Version 9: Commit 25/11/22)
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
=======
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
>>>>>>> 5f573b4 (Version 9: Commit 25/11/22)
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

<<<<<<< HEAD
import com.example.redsalud.Modelo.Profesional;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
=======
import com.example.redsalud.Modelo.CentroSalud;
import com.example.redsalud.Modelo.Profesional;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
>>>>>>> 5f573b4 (Version 9: Commit 25/11/22)

public class AgregarProfesional extends Fragment {

    private EditText etNombre, etApellido, etEspecialidad;
<<<<<<< HEAD
    private Spinner spnAreaMedica, spnFotoPerfil;
    private Button btnRegistrar;
    private DatabaseReference database;
=======
    private Spinner spnAreaMedica, spnGenero, spnCentroMedico;
    private Button btnRegistrar;
    private DatabaseReference database;
    String idCentroSalud = "";
>>>>>>> 5f573b4 (Version 9: Commit 25/11/22)

    public static AgregarProfesional newInstance() {
        AgregarProfesional fragment = new AgregarProfesional();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agregar_profesional, container, false);

        //Inicializamos el objeto firebaseDatabase
        database = FirebaseDatabase.getInstance().getReference();
        //Referenciamos los views
        etNombre = (EditText) view.findViewById(R.id.etNombre);
        etApellido = (EditText) view.findViewById(R.id.etApellido);
        etEspecialidad = (EditText) view.findViewById(R.id.etEspecialidad);
        spnAreaMedica = (Spinner) view.findViewById(R.id.spnAreaMedica);
<<<<<<< HEAD
        spnFotoPerfil = (Spinner) view.findViewById(R.id.spnFotoPerfil);
        btnRegistrar = (Button) view.findViewById(R.id.btnRegistroP);

=======
        spnGenero = (Spinner) view.findViewById(R.id.spnGenero);
        spnCentroMedico = (Spinner) view.findViewById(R.id.spnCentroMedico);
        btnRegistrar = (Button) view.findViewById(R.id.btnRegistroP);

        loadCentrosMedicos();

>>>>>>> 5f573b4 (Version 9: Commit 25/11/22)
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString().trim();
                String apellido = etApellido.getText().toString().trim();
                String especialidad = etEspecialidad.getText().toString().trim();
                String areaMedica = spnAreaMedica.getSelectedItem().toString().trim();
<<<<<<< HEAD
                String fotoPerfil = spnFotoPerfil.getSelectedItem().toString().trim();
=======
                String genero = spnGenero.getSelectedItem().toString().trim();
>>>>>>> 5f573b4 (Version 9: Commit 25/11/22)
                if (TextUtils.isEmpty(nombre)) {
                    Toast.makeText(getActivity(), "Se debe ingresar un nombre", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(apellido)) {
                    Toast.makeText(getActivity(), "Se debe ingresar un apellido", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(especialidad)) {
                    Toast.makeText(getActivity(), "Se debe ingresar una especialidad", Toast.LENGTH_LONG).show();
                    return;
                }
<<<<<<< HEAD
                String imgPerfil = null;
                if (fotoPerfil.equals("Hombre")) {
                    imgPerfil = "https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Personal%2FDr-400x400.jpg?alt=media&token=8ca09d8c-bf8a-4bf0-a97e-3258820bb6e3";
                }
                if (fotoPerfil.equals("Mujer")) {
                    imgPerfil = "https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Personal%2FDra-400x400.jpg?alt=media&token=0ea98d4c-161f-4630-b9b6-6fa97264f545";
                }
                final String randomKey = FirebaseDatabase.getInstance().getReference().push().getKey();
                Profesional p = new Profesional(randomKey, imgPerfil, nombre, apellido, especialidad, areaMedica);
=======
                String generoP = null;
                if (genero.equals("Hombre")) {
                    generoP = "https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Personal%2FDr-400x400.jpg?alt=media&token=8ca09d8c-bf8a-4bf0-a97e-3258820bb6e3";
                }
                if (genero.equals("Mujer")) {
                    generoP = "https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Personal%2FDra-400x400.jpg?alt=media&token=0ea98d4c-161f-4630-b9b6-6fa97264f545";
                }
                final String randomKey = FirebaseDatabase.getInstance().getReference().push().getKey();
                Profesional p = new Profesional(randomKey, generoP, nombre, apellido, especialidad, areaMedica, idCentroSalud);
>>>>>>> 5f573b4 (Version 9: Commit 25/11/22)
                database.child("Profesional").child(randomKey).setValue(p);
                Toast.makeText(getActivity(), "Registro realizado con exito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Principal.class);
                startActivity(intent);
            }
        });
        return view;
    }

<<<<<<< HEAD
=======
    public void loadCentrosMedicos() {
        List <CentroSalud> centroSalud = new ArrayList<>();
        database.child("CentroSalud").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String idCentroSalud = ds.child("idCentroSalud").getValue(String.class);
                        String nombre = ds.child("nombre").getValue(String.class);
                        centroSalud.add(new CentroSalud(idCentroSalud, nombre, null,null));
                    }

                    ArrayAdapter <CentroSalud> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, centroSalud);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spnCentroMedico.setAdapter(arrayAdapter);

                    spnCentroMedico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            idCentroSalud = centroSalud.get(i).getIdCentroSalud();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

>>>>>>> 5f573b4 (Version 9: Commit 25/11/22)
}