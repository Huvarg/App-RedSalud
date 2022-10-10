package com.example.redsalud;


import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class EvaluarSalud extends Fragment {

    EditText etPeso;
    EditText etAltura;
    TextView tvResultado;
    Button bntCalcular;

    public EvaluarSalud() {
    }

    public static EvaluarSalud newInstance() {
        EvaluarSalud fragment = new EvaluarSalud();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluar_salud, container, false);

        etPeso = view.findViewById(R.id.etPeso);
        etAltura = view.findViewById(R.id.etAltura);
        tvResultado = view.findViewById(R.id.tvResultado);
        bntCalcular = view.findViewById(R.id.btnCalcularIMC);
        bntCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interpretaIMC(imc(etPeso.getText().toString(),etAltura.getText().toString()));
            }
        });
        return view;
    }

    private float imc(String strPeso, String strAltura){
        float peso = Float.parseFloat(strPeso);
        float altura = Float.parseFloat(strAltura);
        return peso/(altura*altura);
    }

    private void interpretaIMC(float imc){
        String resultado="";
        if (imc<18.5){
            resultado="Su IMC es: "+imc+" (Bajo peso)";
        }else if (imc>18.5 && imc<25){
            resultado="Su IMC es: "+imc+" (Normal)";
        }else{
            resultado="Su IMC es: "+imc+" (Sobrepeso)";
        }
        tvResultado.setText(resultado);
    }

}