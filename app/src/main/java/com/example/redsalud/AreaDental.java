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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AreaDental extends Fragment {

    private FirebaseFirestore firestore;
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

        firestore = FirebaseFirestore.getInstance();
        firestore.collection("Profesional")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            listadoP.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String ruta = document.getString("ruta");
                                String nombre = document.getString("nombre");
                                String apellido = document.getString("apellido");
                                String especialidad = document.getString("especialidad");
                                String areaMedica = document.getString("areaMedica");
                                Profesional p = new Profesional(ruta, nombre, apellido, especialidad, areaMedica);
                                if (areaMedica.equals("Area Dental")) {
                                    listadoP.add(p);
                                    listV.setAdapter(adaptadorP);
                                }
                            }
                        } else {
                            System.out.println("Error: " + task.getException());
                        }
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