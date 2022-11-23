package com.example.redsalud;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.redsalud.Adaptadores.AdaptadorP;
import com.example.redsalud.Modelo.Profesional;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AreaDental extends Fragment {

    private DatabaseReference database;
    private Profesional p;
    private ListView listV;
    private ArrayList <Profesional> listadoP;

    public AreaDental() {
    }

    public static AreaDental newInstance() {
        AreaDental fragment = new AreaDental();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area_dental, container, false);

        listadoP = new ArrayList<>();
        p = new Profesional();
        listV = view.findViewById(R.id.listaAreaDental);
        AdaptadorP adaptadorP = new AdaptadorP(getContext(), listadoP);

        database = FirebaseDatabase.getInstance().getReference("Profesional");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    listadoP.clear();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String key = ds.child("key").getValue(String.class);
                        String ruta = ds.child("ruta").getValue(String.class);
                        String nombre = ds.child("nombre").getValue(String.class);
                        String apellido = ds.child("apellido").getValue(String.class);
                        String especialidad = ds.child("especialidad").getValue(String.class);
                        String areaMedica = ds.child("areaMedica").getValue(String.class);
                        Profesional p = new Profesional(key, ruta, nombre, apellido, especialidad, areaMedica);
                        if (areaMedica.equals("Area Dental")) {
                            listadoP.add(p);
                            listV.setAdapter(adaptadorP);
                        }
                    }
                    adaptadorP.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Fallo de lectura: " + error.getCode());
            }
        });

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Profesional p = listadoP.get(position);
                Toast.makeText(getActivity(), "Selecciono a: "+p.getNombre()+" "+p.getApellido(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), DetalleProfesional.class);
                intent.putExtra("profesional",p);
                startActivity(intent);
            }
        });
        return view;
    }

}