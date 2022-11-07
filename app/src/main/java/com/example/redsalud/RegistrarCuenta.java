package com.example.redsalud;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.redsalud.Modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarCuenta extends AppCompatActivity implements View.OnClickListener {

    //Definir view objetos
    private EditText textNombre, textApellido, textEmail, textPassword;
    private Button btnRegistrar;
    private ProgressDialog progressDialog;
    //Declaramos un objeto firebaseAuth y databaseReference
    private FirebaseAuth auth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cuenta);

        //Inicializamos el objeto firebaseAuth y firebaseDatabase
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        //Referenciamos los views
        textNombre = (EditText) findViewById(R.id.editTextNombre);
        textApellido = (EditText) findViewById(R.id.editTextApellido);
        textEmail = (EditText) findViewById(R.id.editTextEmail);
        textPassword = (EditText) findViewById(R.id.editTextPass);
        btnRegistrar = (Button) findViewById(R.id.btnRegistro);
        progressDialog = new ProgressDialog(this);
        btnRegistrar.setOnClickListener(this);
    }

    //Metodo registrar usuario
    private void registrarUsuario() {
        String nombre = textNombre.getText().toString().trim();
        String apellido = textApellido.getText().toString().trim();
        String email = textEmail.getText().toString().trim();
        String password = textPassword.getText().toString().trim();
        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(this,"Se debe ingresar un nombre", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(apellido)) {
            Toast.makeText(this,"Se debe ingresar un apellido", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this,"Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this,"Se debe ingresar un password", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(password) || password.length() < 8) {
            Toast.makeText(this,"Se debe tener mas de 8 caracteres", Toast.LENGTH_LONG).show();
        }
        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String id = auth.getCurrentUser().getUid();
                            Usuario u = new Usuario("", nombre, apellido, email, password);
                            database.child("Usuarios").child(id).setValue(u);
                            Toast.makeText(RegistrarCuenta.this, "Se ha registrado con exito, ya puede iniciar sesion", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RegistrarCuenta.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(RegistrarCuenta.this, "Este usuario ya existe", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegistrarCuenta.this, "No se pudo registrar el usuario", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    //Metodo para los botones
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegistro:
                registrarUsuario();
                break;
            case R.id.btnIniciarSesion:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
        }
    }

}