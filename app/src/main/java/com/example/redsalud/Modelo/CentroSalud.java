package com.example.redsalud.Modelo;

import java.io.Serializable;

public class CentroSalud implements Serializable {

    private String nombreCentro;
    private Double lat;
    private Double log;

    public CentroSalud() {
    }

    public CentroSalud(String nombreCentro, Double lat, Double log) {
        this.nombreCentro = nombreCentro;
        this.lat = lat;
        this.log = log;
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLog() {
        return log;
    }

    public void setLog(Double log) {
        this.log = log;
    }

}