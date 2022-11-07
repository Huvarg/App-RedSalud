package com.example.redsalud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Definir view objetos
    private EditText textEmail, textPassword;
    //Declaramos un objeto firebaseAuth
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializamos el objeto firebaseAuth
        auth = FirebaseAuth.getInstance();
        //Referenciamos los views
        textEmail = (EditText) findViewById(R.id.correo);
        textPassword = (EditText) findViewById(R.id.clave);
    }

    //Metodo login usuario
    private void iniciarSesion() {
        String email = textEmail.getText().toString().trim();
        String password = textPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this,"Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }
        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this,"Se debe ingresar un password", Toast.LENGTH_LONG).show();
            return;
        }
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            CheckBox cbrecuerdame = (CheckBox) findViewById(R.id.cbRecuerdame);
                            boolean chequeado = cbrecuerdame.isChecked();
                            if (chequeado==true) {
                                SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editor = datos.edit();
                                editor.putString("idUser", email);
                                editor.commit();
                            }
                            Toast.makeText(MainActivity.this,"Bienvenido", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplication(), Principal.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this,"No se pudo iniciar sesion", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    //Metodo para sesion activa
    protected void onResume() {
        super.onResume();
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        String idUser = datos.getString("idUser", "");
        if (!idUser.equals("")) {
            Toast.makeText(MainActivity.this,"Bienvenido", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplication(), Principal.class);
            startActivity(intent);
            finish();
        }
    }

    //Metodo para los botones
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIngresar:
                iniciarSesion();
                break;
            case R.id.btnCrearUsuario:
                Intent i = new Intent(this,RegistrarCuenta.class);
                startActivity(i);
            }
    }

}