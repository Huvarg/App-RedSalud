package com.example.redsalud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.redsalud.Modelo.Noticia;

public class DetalleNoticia extends AppCompatActivity {

    private ImageView urlFoto;
    private TextView titulo, contenico, tipo;
    private Noticia n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_noticia);


        urlFoto = (ImageView) findViewById(R.id.n_foto);
        titulo = (TextView) findViewById(R.id.n_titulo);
        contenico = (TextView) findViewById(R.id.n_contenido);
        tipo = (TextView) findViewById(R.id.n_tipo);

        Bundle paquete = getIntent().getExtras();
        if(paquete!=null){
            n = (Noticia) paquete.getSerializable("noticia");
            Glide.with(getApplicationContext())
                    .load(n.getRutaimg())
                    .centerCrop()
                    .fitCenter()
                    .into(urlFoto);
            titulo.setText(n.getTitulo().toString());
            contenico.setText(n.getContenido().toString());
            tipo.setText(n.getTipo().toString());
        }
    }
    //Metodo volver atras
    public void volverAtras(View view) {
        onBackPressed();
    }

}

