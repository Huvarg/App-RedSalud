package com.example.redsalud.Modelo;

import java.io.Serializable;

public class Persona implements Serializable {

    private String ruta;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String areamedica;

    public Persona(String ruta, String nombre, String apellido, String especialidad, String areamedica) {
        this.ruta = ruta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.areamedica = areamedica;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getAreamedica() {
        return areamedica;
    }

    public void setAreamedica(String areamedica) {
        this.areamedica = areamedica;
    }

}