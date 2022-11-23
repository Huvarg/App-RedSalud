package com.example.redsalud;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Perfil extends Fragment {

    private ImageView imgUser;
    private TextView emailUser, nombreUser;
    private Button subirFoto;
    private FirebaseAuth auth;
    private DatabaseReference database;
    private StorageReference storage;
    private String idUser;
    private static final int GALLERY_INTENT = 1;

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
        storage = FirebaseStorage.getInstance().getReference();
        idUser = auth.getCurrentUser().getUid();

        imgUser = view.findViewById(R.id.imgPerfilUser);
        nombreUser = view.findViewById(R.id.textNombreUser);
        emailUser = view.findViewById(R.id.textEmailUser);
        subirFoto = view.findViewById(R.id.imgAddFoto);

        subirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);
            }
        });

        database.child("Usuario").child(idUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String urlImg = snapshot.child("urlImg").getValue().toString();
                    String email = snapshot.child("correo").getValue().toString();
                    String nombre = snapshot.child("nombre").getValue().toString();
                    String apellido = snapshot.child("apellido").getValue().toString();
                    Glide.with(getContext())
                            .load(urlImg)
                            .centerCrop()
                            .fitCenter()
                            .into(imgUser);
                    emailUser.setText(email);
                    nombreUser.setText("Bienvenido: "+nombre+" "+apellido);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Fallo de lectura: " + error.getCode());
            }
        });
    return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            final StorageReference filePath = storage.child("Foto-Perfil").child(idUser);
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Cambios realizados con exito", Toast.LENGTH_SHORT).show();
                    filePath.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri downloadUrl = task.getResult();
                                database.child("Usuario").child(idUser).child("urlImg").setValue(downloadUrl.toString());
                            }
                        }
                    });
                }
            });
        }
    }

}