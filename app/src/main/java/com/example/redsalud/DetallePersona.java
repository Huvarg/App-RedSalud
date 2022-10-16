package com.example.redsalud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.redsalud.Modelo.Persona;

public class DetallePersona extends AppCompatActivity {

    private Persona p;
    private ImageView url;
    private TextView nombre;
    private TextView especialidad;
    private TextView areamedica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_persona);

        url = (ImageView) findViewById(R.id.p_img);
        nombre = (TextView) findViewById(R.id.p_nombre_apellido);
        especialidad = (TextView) findViewById(R.id.p_especialidad);
        areamedica = (TextView) findViewById(R.id.p_area);

        Bundle paquete = getIntent().getExtras();
        if(paquete!=null){
            p = (Persona) paquete.getSerializable("persona");
            Glide.with(getApplicationContext())
                    .load(p.getRuta())
                    .centerCrop()
                    .fitCenter()
                    .into(url);
            nombre.setText(p.getNombre().toString()+" "+p.getApellido().toString());
            especialidad.setText(p.getEspecialidad().toString());
            areamedica.setText(p.getAreamedica().toString());
        }
    }
    //Metodo volver atras
    public void volverAtras(View view) {
        onBackPressed();
    }

}