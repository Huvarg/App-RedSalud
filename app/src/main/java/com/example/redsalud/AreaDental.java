package com.example.redsalud;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.redsalud.Adaptadores.AdaptadorP;
import com.example.redsalud.Modelo.Persona;
import java.util.ArrayList;

public class AreaDental extends Fragment {
    private ArrayList<Persona> listado;
    Persona p;

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

        ListView l = (ListView) view.findViewById(R.id.listaAreaDental);
        AdaptadorP adaptadorP = new AdaptadorP(getContext(),cargarListado());
        l.setAdapter(adaptadorP);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Persona p = listado.get(position);
                Toast.makeText(getActivity(), "Selecciono a: "+p.getNombre()+" "+p.getApellido(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),DetallePersona.class);
                intent.putExtra("persona",p);
                startActivity(intent);
            }
        });
        return view;
    }

    public ArrayList<Persona> cargarListado() {
        listado = new ArrayList<>();
        listado.add(new Persona("https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Personal%2FDr-400x400.jpg?alt=media&token=8ca09d8c-bf8a-4bf0-a97e-3258820bb6e3","Cristian","Araya Araya","Odontologo","Area Dental"));
        listado.add(new Persona("https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Personal%2FDra-400x400.jpg?alt=media&token=0ea98d4c-161f-4630-b9b6-6fa97264f545","Sara","Lopez Fuentes","Odontologo","Area Dental"));
        listado.add(new Persona("https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Personal%2FDra-400x400.jpg?alt=media&token=0ea98d4c-161f-4630-b9b6-6fa97264f545","Cristina","Mamani","Odontologo","Area Dental"));
        return listado;
    }

}