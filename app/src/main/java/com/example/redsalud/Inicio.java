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

    Button icon1, icon2, icon3, icon4, icon5, icon6;

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
                transaction.replace(R.id.contenedor, AgendarCita.newInstance());
                transaction.commit();
                Toast.makeText(getActivity().getApplication(), "Vas a agendar cita", Toast.LENGTH_SHORT).show();
            }
        });

        icon2 = vista.findViewById(R.id.icon2);
        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.contenedor, EvaluarSalud.newInstance());
                transaction.commit();
                Toast.makeText(getActivity().getApplication(), "Vas a evaluar salud", Toast.LENGTH_SHORT).show();
            }
        });

        icon3 = vista.findViewById(R.id.icon3);
        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.contenedor, CentroMedicoLista.newInstance());
                transaction.commit();
                Toast.makeText(getActivity().getApplication(), "Vas a centros salud", Toast.LENGTH_SHORT).show();
            }
        });

        icon4 = vista.findViewById(R.id.icon4);
        icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.contenedor, Examenes.newInstance());
                transaction.commit();
                Toast.makeText(getActivity().getApplication(), "Vas a examenes", Toast.LENGTH_SHORT).show();
            }
        });

        icon5 = vista.findViewById(R.id.icon5);
        icon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.contenedor, Soporte.newInstance());
                transaction.commit();
                Toast.makeText(getActivity().getApplication(), "Vas a soporte", Toast.LENGTH_SHORT).show();
            }
        });

        icon6 = vista.findViewById(R.id.icon6);
        icon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.contenedor, ForoNoticia.newInstance());
                transaction.commit();
                Toast.makeText(getActivity().getApplication(), "Vas a noticia", Toast.LENGTH_SHORT).show();
            }
        });
        return vista;
    }

}