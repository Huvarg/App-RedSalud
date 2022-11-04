package com.example.redsalud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Soporte extends Fragment {

    public Soporte() {
    }

    public static Soporte newInstance() {
        Soporte fragment = new Soporte();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView titleToolbar = getActivity().findViewById(R.id.toolbarName);
        titleToolbar.setText("Soporte");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_soporte, container, false);
        return view;
    }

}