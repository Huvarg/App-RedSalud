package com.example.redsalud;

import android.content.Intent;
import android.os.Bundle;
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
import java.util.ArrayList;

public class CentroMedicoLista extends Fragment {
    private ArrayList<CentroSalud> listado;
    CentroSalud c;

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

        ListView l = (ListView) view.findViewById(R.id.lvCentroMedico);
        AdaptadorCS adaptadorCS = new AdaptadorCS(getContext(),cargar_datos());
        l.setAdapter(adaptadorCS);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //CentroSalud c = listado.get(position);
                //Intent intent = new Intent(getActivity(),CentrosSalud.class);
                //intent.putExtra("centro",c);
                //startActivity(intent);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.contenedor, CentrosSalud.newInstance());
                transaction.commit();
            }
        });
        return view;
    }

    public ArrayList<CentroSalud> cargar_datos() {
        listado = new ArrayList<>();
        listado.add(new CentroSalud("Hospital Regional Copiapó San José",-27.37353333,-70.32269167));
        listado.add(new CentroSalud("Centro de Salud Familiar Pedro León Gallo",-27.35991145,-70.32681842));
        return listado;
    }

}