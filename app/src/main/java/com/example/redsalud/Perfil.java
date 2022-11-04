package com.example.redsalud;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Perfil extends Fragment {

    private TextView emailUser, nombreUser;
    private FirebaseAuth auth;
    private DatabaseReference database;
    private String idUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView titleToolbar = getActivity().findViewById(R.id.toolbarName);
        titleToolbar.setText("Perfil");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        idUser = auth.getCurrentUser().getUid();

        nombreUser = view.findViewById(R.id.textNombreUser);
        emailUser = view.findViewById(R.id.textEmailUser);

        database.child("Usuarios").child(idUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String nombre = snapshot.child("nombre").getValue().toString();
                    String apellido = snapshot.child("apellido").getValue().toString();
                    String email = snapshot.child("correo").getValue().toString();
                    nombreUser.setText("Bienvenido: "+nombre+" "+apellido);
                    emailUser.setText(email);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    return view;
    }

}