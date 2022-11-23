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
import android.widget.Toast;

import com.example.redsalud.Modelo.CentroSalud;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AgregarCentroSalud extends Fragment {

    private EditText etNombreCentro, etLat, etLog;
    private Button btnRegistrarC;
    private DatabaseReference database;

    public static AgregarCentroSalud newInstance() {
        AgregarCentroSalud fragment = new AgregarCentroSalud();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agregar_centro_salud, container, false);

        //Inicializamos el objeto firebaseDatabase
        database = FirebaseDatabase.getInstance().getReference();
        //Referenciamos los views
        etNombreCentro = (EditText) view.findViewById(R.id.etNombreCentro);
        etLat = (EditText) view.findViewById(R.id.etLat);
        etLog = (EditText) view.findViewById(R.id.etLog);
        btnRegistrarC = (Button) view.findViewById(R.id.btnRegistrarC);

        btnRegistrarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombreCentro.getText().toString().trim();
                Double lat = Double.valueOf(etLat.getText().toString().trim());
                Double log = Double.valueOf(etLog.getText().toString().trim());
                if (TextUtils.isEmpty(nombre)) {
                    Toast.makeText(getActivity(), "Se debe ingresar un nombre de centro medico", Toast.LENGTH_LONG).show();
                    return;
                }
                final String randomKey = FirebaseDatabase.getInstance().getReference().push().getKey();
                CentroSalud c = new CentroSalud(randomKey, nombre, lat, log);
                database.child("CentroSalud").child(randomKey).setValue(c);
                Toast.makeText(getActivity(), "Registro realizado con exito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Principal.class);
                startActivity(intent);
            }
        });
        return view;
    }
}