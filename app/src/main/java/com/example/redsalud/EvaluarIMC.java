package com.example.redsalud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EvaluarIMC extends Fragment {

    private EditText etPeso;
    private EditText etAltura;
    private TextView tvResultado;
    private Button bntCalcular;

    public EvaluarIMC() {
    }

    public static EvaluarIMC newInstance() {
        EvaluarIMC fragment = new EvaluarIMC();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluar_i_m_c, container, false);

        TextView titleToolbar = getActivity().findViewById(R.id.toolbarName);
        titleToolbar.setText("Evalua tu Salud");

        etPeso = view.findViewById(R.id.etPeso2);
        etAltura = view.findViewById(R.id.etAltura2);
        tvResultado = view.findViewById(R.id.tvResultado2);
        bntCalcular = view.findViewById(R.id.btnCalcularIMC2);

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