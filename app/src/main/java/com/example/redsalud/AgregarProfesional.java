package com.example.redsalud;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.redsalud.Modelo.Profesional;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AgregarProfesional extends Fragment {

    private EditText etNombre, etApellido, etEspecialidad;
    private Spinner spnAreaMedica, spnFotoPerfil;
    private Button btnRegistrar;
    private DatabaseReference database;

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
        spnFotoPerfil = (Spinner) view.findViewById(R.id.spnFotoPerfil);
        btnRegistrar = (Button) view.findViewById(R.id.btnRegistroP);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString().trim();
                String apellido = etApellido.getText().toString().trim();
                String especialidad = etEspecialidad.getText().toString().trim();
                String areaMedica = spnAreaMedica.getSelectedItem().toString().trim();
                String fotoPerfil = spnFotoPerfil.getSelectedItem().toString().trim();
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
                String imgPerfil = null;
                if (fotoPerfil.equals("Hombre")) {
                    imgPerfil = "https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Personal%2FDr-400x400.jpg?alt=media&token=8ca09d8c-bf8a-4bf0-a97e-3258820bb6e3";
                }
                if (fotoPerfil.equals("Mujer")) {
                    imgPerfil = "https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Personal%2FDra-400x400.jpg?alt=media&token=0ea98d4c-161f-4630-b9b6-6fa97264f545";
                }
                final String randomKey = FirebaseDatabase.getInstance().getReference().push().getKey();
                Profesional p = new Profesional(randomKey, imgPerfil, nombre, apellido, especialidad, areaMedica);
                database.child("Profesional").child(randomKey).setValue(p);
                Toast.makeText(getActivity(), "Registro realizado con exito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Principal.class);
                startActivity(intent);
            }
        });
        return view;
    }

}