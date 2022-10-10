package com.example.redsalud;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AgendarCita extends Fragment {

    Button btnAreaMedica;
    Button btnAreaDental;
    private AgendarCita nViewModel;

    public  static  AgendarCita newInstance() {
        return new AgendarCita();}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_agendar_cita, container, false);

        btnAreaMedica = vista.findViewById(R.id.btnAreaMedica);
        btnAreaMedica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.contenedorFragment, AreaMedica.newInstance());
                transaction.commit();
            }
        });
        btnAreaDental = vista.findViewById(R.id.btnAreaDental);
        btnAreaDental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.contenedorFragment, AreaDental.newInstance());
                transaction.commit();
            }
        });
        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}