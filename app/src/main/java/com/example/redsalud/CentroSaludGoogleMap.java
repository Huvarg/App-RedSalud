package com.example.redsalud;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.redsalud.Modelo.CentroSalud;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.redsalud.databinding.ActivityCentroSaludGoogleMapBinding;

public class CentroSaludGoogleMap extends FragmentActivity implements OnMapReadyCallback {

    private CentroSalud cs;
    private GoogleMap mMap;
    private ActivityCentroSaludGoogleMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCentroSaludGoogleMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        String nombre = getIntent().getExtras().getString("centro-salud-nombre");
        Double lat = getIntent().getExtras().getDouble("centro-salud-lat");
        Double log = getIntent().getExtras().getDouble("centro-salud-log");

        LatLng centroMedico = new LatLng(lat, log);
        googleMap.addMarker(new MarkerOptions().position(centroMedico).title(nombre));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(centroMedico));

        float zoomLevel = 16;
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centroMedico, zoomLevel));
    }

}