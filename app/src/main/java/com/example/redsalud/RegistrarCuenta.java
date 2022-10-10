package com.example.redsalud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redsalud.Modelo.Usuario;

public class RegistrarCuenta extends AppCompatActivity {

    private EditText nombre;
    private EditText apellido;
    private EditText sexo;
    private EditText fNac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cuenta);
    }

    //Metodo para el boton enviar
    public void enviarDetalles(View view){

        nombre = (EditText) findViewById(R.id.textv1);
        String campo1 = nombre.getText().toString();
        apellido = (EditText) findViewById(R.id.textv2);
        String campo2 = apellido.getText().toString();
        sexo = (EditText) findViewById(R.id.textv3);
        String campo3 = sexo.getText().toString();
        fNac = (EditText) findViewById(R.id.textv4);
        String campo4 = fNac.getText().toString();
        if (campo1.equals("") || campo2.equals("") || campo3.equals("") || campo4.equals("")) {
            Toast.makeText(this,"Complete los campos vacios", Toast.LENGTH_SHORT).show();
        } else {
            Usuario u = new Usuario(null,campo1,campo2,campo3,campo4,null,null,null);
            Intent intent = new Intent(this,RegistrarCuentaDetalle.class);
            intent.putExtra("usuario",u);
            startActivity(intent);
        }
    }

}