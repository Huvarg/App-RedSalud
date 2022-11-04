package com.example.redsalud;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.redsalud.Adaptadores.AdaptadorN;
import com.example.redsalud.Modelo.Noticia;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ForoNoticia extends Fragment {

    private DatabaseReference database;
    private Noticia n;
    private ListView listV;
    private ArrayList <Noticia> listadoN;

    public static ForoNoticia newInstance() {
        ForoNoticia fragment = new ForoNoticia();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foro_noticia, container, false);

        TextView titleToolbar = getActivity().findViewById(R.id.toolbarName);
        titleToolbar.setText("Noticias");

        listadoN = new ArrayList<>();
        n = new Noticia();
        listV = view.findViewById(R.id.lvNoticia);
        AdaptadorN adaptadorN = new AdaptadorN(getContext(), listadoN);

        database = FirebaseDatabase.getInstance().getReference("Noticias");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    listadoN.clear();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String tipo = ds.child("tipo").getValue(String.class);
                        String rutaimg = ds.child("ruta_img").getValue(String.class);
                        String titulo = ds.child("titulo").getValue(String.class);
                        String contenido = ds.child("contenido").getValue(String.class);
                        Noticia n = new Noticia(tipo, rutaimg, titulo, contenido);
                        listadoN.add(n);
                        listV.setAdapter(adaptadorN);
                    }
                    adaptadorN.notifyDataSetChanged();
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
                Noticia n = listadoN.get(position);
                Intent intent = new Intent(getActivity(), DetalleNoticia.class);
                intent.putExtra("noticia",n);
                startActivity(intent);
            }
        });
        return view;
    }

}