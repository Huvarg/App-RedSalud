package com.example.redsalud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Inicio extends Fragment {

    private Button icon1, icon2, icon3, icon4;

    public  static  Inicio newInstance() {
        return new Inicio();}

    public Inicio() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_inicio, container, false);

        TextView titleToolbar = getActivity().findViewById(R.id.toolbarName);
        titleToolbar.setText("Menu");

        icon1 = vista.findViewById(R.id.icon1);
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.contenedor, AgregarProfesional.newInstance());
                transaction.commit();
                Toast.makeText(getActivity().getApplication(), "Vas a agregar profesional", Toast.LENGTH_SHORT).show();
            }
        });

        icon2 = vista.findViewById(R.id.icon2);
        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.contenedor, ProfesionalArea.newInstance());
                transaction.commit();
                Toast.makeText(getActivity().getApplication(), "Vas a listado de profesionales", Toast.LENGTH_SHORT).show();
            }
        });

        icon3 = vista.findViewById(R.id.icon3);
        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.contenedor, AgregarCentroSalud.newInstance());
                transaction.commit();
                Toast.makeText(getActivity().getApplication(), "Vas a agregar centro salud", Toast.LENGTH_SHORT).show();
            }
        });

        icon4 = vista.findViewById(R.id.icon4);
        icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.contenedor, CentroMedicoLista.newInstance());
                transaction.commit();
                Toast.makeText(getActivity().getApplication(), "Vas a centros de salud", Toast.LENGTH_SHORT).show();
            }
        });
        return vista;
    }

}