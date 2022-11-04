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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CentroMedicoLista extends Fragment {

    private FirebaseFirestore firestore;
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

        firestore = FirebaseFirestore.getInstance();
        firestore.collection("CentroSalud")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            listadoC.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String nombre = document.getString("nombre");
                                Double lat = Double.valueOf(document.getString("lat"));
                                Double log = Double.valueOf(document.getString("log"));
                                CentroSalud c = new CentroSalud(nombre, lat, log);
                                listadoC.add(c);
                                listV.setAdapter(adaptadorCS);
                            }
                        } else {
                            System.out.println("Error: " + task.getException());
                        }
                    }
                });

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CentroSalud c = listadoC.get(position);
                Bundle datosEnviar = new Bundle();
                datosEnviar.putString("nombre", c.getNombreCentro());
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