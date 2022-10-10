package com.example.redsalud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redsalud.Modelo.Usuario;

public class RegistrarCuentaDetalle extends AppCompatActivity {

    Usuario u;
    private TextView nombre;
    private TextView apellido;
    private TextView sexo;
    private TextView fNac;
    private EditText correo;
    private EditText pass1;
    private EditText pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cuenta_detalle);

        nombre = (TextView) findViewById(R.id.r_nombre);
        apellido = (TextView) findViewById(R.id.r_apellido);
        sexo = (TextView) findViewById(R.id.r_sexo);
        fNac = (TextView) findViewById(R.id.r_fnac);
        Bundle paquete = getIntent().getExtras();
        if(paquete!=null){
            u = (Usuario) paquete.getSerializable("usuario");
            nombre.setText("Nombre: "+u.getNombre().toString());
            apellido.setText("Apellido: "+u.getApellido().toString());
            sexo.setText("Sexo: "+u.getSexo().toString());
            fNac.setText("Fecha nac: "+u.getfNac().toString());
        }
    }

    public void iniciarSesion(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    //Metodo para el boton registrar
    public void registrarCuenta(View view) {

        correo = (EditText) findViewById(R.id.r_email);
        String campo1 = correo.getText().toString();
        pass1 = (EditText) findViewById(R.id.r_pass1);
        String campo2 = pass1.getText().toString();
        pass2 = (EditText) findViewById(R.id.r_pass2);
        String campo3 = pass2.getText().toString();
        if (campo1.equals("") || campo2.equals("") || campo3.equals("")) {
            Toast.makeText(this, "Complete los campos vacios", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(this, MainActivity.class);
            Toast.makeText(this, "Cuenta registrada con exito", Toast.LENGTH_SHORT).show();
            startActivity(i);
        }
    }

}