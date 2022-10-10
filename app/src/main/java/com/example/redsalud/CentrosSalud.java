package com.example.redsalud;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CentrosSalud extends Fragment {

    public static CentrosSalud newInstance() {
        CentrosSalud fragment = new CentrosSalud();
        return fragment;
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng centroSalud1 = new LatLng(-27.37353333, -70.32269167);
            googleMap.addMarker(new MarkerOptions().position(centroSalud1).title("Hospital Regional Copiapó San José del Carmen"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(centroSalud1));

            LatLng centroSalud2 = new LatLng( -27.35991145, -70.32681842);
            googleMap.addMarker(new MarkerOptions().position(centroSalud2).title("Centro de Salud Familiar Pedro León Gallo"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(centroSalud2));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_centros_salud, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

}