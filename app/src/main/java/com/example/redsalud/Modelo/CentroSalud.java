package com.example.redsalud.Modelo;

import java.io.Serializable;

public class CentroSalud implements Serializable {

    private String key;
    private String nombre;
    private Double lat;
    private Double log;

    public CentroSalud() {
    }

    public CentroSalud(String key, String nombre, Double lat, Double log) {
        this.key = key;
        this.nombre = nombre;
        this.lat = lat;
        this.log = log;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

}