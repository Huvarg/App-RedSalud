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

public class CentroSaludMap extends Fragment {

    public static CentroSaludMap newInstance() {
        CentroSaludMap fragment = new CentroSaludMap();
        return fragment;
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {

            Bundle datosRecuperados = getArguments();
            if(datosRecuperados!=null){
                String nombre = datosRecuperados.getString("nombre");
                Double lat = datosRecuperados.getDouble("lat");
                Double log = datosRecuperados.getDouble("log");
                LatLng centroMedico = new LatLng(lat, log);
                googleMap.addMarker(new MarkerOptions().position(centroMedico).title(nombre));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(centroMedico));

                float zoomLevel = 16;
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centroMedico, zoomLevel));
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_centro_salud_map, container, false);
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