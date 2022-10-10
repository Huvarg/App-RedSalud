package com.example.redsalud;

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

public class AreaMedica extends Fragment {
    private ArrayList<Persona> listado;
    Persona p;

    public AreaMedica() {
    }

    public static AreaMedica newInstance() {
        AreaMedica fragment = new AreaMedica();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area_medica, container, false);

        ListView l = (ListView) view.findViewById(R.id.listaAreaMedica);
        AdaptadorP adaptadorP = new AdaptadorP(getContext(),cargarListado());
        l.setAdapter(adaptadorP);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Selecciono el item: "+position, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public ArrayList<Persona> cargarListado() {
        listado = new ArrayList<>();
        listado.add(new Persona("https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Personal%2FDr-400x400.jpg?alt=media&token=8ca09d8c-bf8a-4bf0-a97e-3258820bb6e3","Juan","Jara Lopez","Medicina General Adulto"));
        listado.add(new Persona("https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Personal%2FDra-400x400.jpg?alt=media&token=0ea98d4c-161f-4630-b9b6-6fa97264f545","Maria","Alquinta Araya","Medicina General Infantil"));
        listado.add(new Persona("https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Personal%2FDra-400x400.jpg?alt=media&token=0ea98d4c-161f-4630-b9b6-6fa97264f545","Catalina","Godoy Fuentes","Medicina General Adulto"));
        listado.add(new Persona("https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Personal%2FDr-400x400.jpg?alt=media&token=8ca09d8c-bf8a-4bf0-a97e-3258820bb6e3","Roberto","Diaz Paz","Medicina General Infantil"));
        listado.add(new Persona("https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Personal%2FDra-400x400.jpg?alt=media&token=0ea98d4c-161f-4630-b9b6-6fa97264f545","Cristina","Fuentes Diaz","Medicina General Infantil"));
        listado.add(new Persona("https://firebasestorage.googleapis.com/v0/b/redsalud-f48e3.appspot.com/o/Personal%2FDra-400x400.jpg?alt=media&token=0ea98d4c-161f-4630-b9b6-6fa97264f545","Iris","Olivares Paz","Medicina General Infantil"));
        return listado;
    }

}