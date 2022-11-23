package com.example.redsalud;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.redsalud.Adaptadores.AdaptadorCS;
import com.example.redsalud.Modelo.CentroSalud;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CentroMedicoLista extends Fragment {

    private DatabaseReference database;
    private ListView listV;
    private ArrayList<CentroSalud> listadoC;

    public static CentroMedicoLista newInstance() {
        CentroMedicoLista fragment = new CentroMedicoLista();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_centro_medico_lista, container, false);

        TextView titleToolbar = getActivity().findViewById(R.id.toolbarName);
        titleToolbar.setText("Centro Medicos");

        listadoC = new ArrayList<>();
        CentroSalud c = new CentroSalud();
        listV = view.findViewById(R.id.lvCentroMedico);
        AdaptadorCS adaptadorCS = new AdaptadorCS(getContext(), listadoC);

        database = FirebaseDatabase.getInstance().getReference("CentroSalud");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    listadoC.clear();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String key = ds.child("key").getValue(String.class);
                        String nombre = ds.child("nombre").getValue(String.class);
                        Double lat = ds.child("lat").getValue(Double.class);
                        Double log = ds.child("log").getValue(Double.class);
                        CentroSalud c = new CentroSalud (key, nombre, lat, log);
                        listadoC.add(c);
                        listV.setAdapter(adaptadorCS);
                    }
                    adaptadorCS.notifyDataSetChanged();
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
                CentroSalud c = listadoC.get(position);
                Bundle datosEnviar = new Bundle();
                datosEnviar.putString("nombre", c.getNombre());
                datosEnviar.putDouble("lat", c.getLat());
                datosEnviar.putDouble("log", c.getLog());
                Fragment fragmento = new CentroSaludMap();
                fragmento.setArguments(datosEnviar);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenedor, fragmento);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

}