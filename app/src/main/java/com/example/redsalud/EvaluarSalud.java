package com.example.redsalud;


import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class EvaluarSalud extends Fragment {

    TextView btnMasaCorpotal;
    TextView btnGastoCalorico;
    private EvaluarSalud nViewModel;

    public  static  EvaluarSalud newInstance() {
        return new EvaluarSalud();}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_evaluar_salud, container, false);

        TextView titleToolbar = getActivity().findViewById(R.id.toolbarName);
        titleToolbar.setText("Evaluar Salud");
        btnMasaCorpotal = vista.findViewById(R.id.btnMasaCorporal);
        btnMasaCorpotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.contenedorCalculos, EvaluarIMC.newInstance());
                transaction.commit();
            }
        });
        btnGastoCalorico = vista.findViewById(R.id.btnGastoCalorico);
        btnGastoCalorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.contenedorCalculos, EvaluarGCB.newInstance());
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