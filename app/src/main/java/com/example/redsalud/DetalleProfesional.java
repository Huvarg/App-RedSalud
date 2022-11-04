package com.example.redsalud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.redsalud.Modelo.Profesional;

public class DetalleProfesional extends AppCompatActivity {

    private ImageView url;
    private TextView nombre, especialidad, areaMedica;
    private Profesional p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_profesional);

        url = (ImageView) findViewById(R.id.p_img);
        nombre = (TextView) findViewById(R.id.p_nombre_apellido);
        especialidad = (TextView) findViewById(R.id.p_especialidad);
        areaMedica = (TextView) findViewById(R.id.p_area);

        Bundle paquete = getIntent().getExtras();
        if(paquete!=null){
            p = (Profesional) paquete.getSerializable("profesional");
            Glide.with(getApplicationContext())
                    .load(p.getRuta())
                    .centerCrop()
                    .fitCenter()
                    .into(url);
            nombre.setText(p.getNombre().toString()+" "+p.getApellido().toString());
            especialidad.setText(p.getEspecialidad().toString());
            areaMedica.setText(p.getAreaMedica().toString());
        }
    }
    //Metodo volver atras
    public void volverAtras(View view) {
        onBackPressed();
    }

}