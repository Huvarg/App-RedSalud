package com.example.redsalud.Modelo;

import java.io.Serializable;

public class CentroSalud implements Serializable {

    private String idCentroSalud;
    private String nombre;
    private Double lat;
    private Double log;

    public CentroSalud() {
    }

    public CentroSalud(String idCentroSalud, String nombre, Double lat, Double log) {
        this.idCentroSalud = idCentroSalud;
        this.nombre = nombre;
        this.lat = lat;
        this.log = log;
    }

    public String getIdCentroSalud() {
        return idCentroSalud;
    }

    public void setIdCentroSalud(String idCentroSalud) {
        this.idCentroSalud = idCentroSalud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return nombre;
    }

}