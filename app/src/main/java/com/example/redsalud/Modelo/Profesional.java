package com.example.redsalud.Modelo;

import java.io.Serializable;

public class Profesional implements Serializable {

    private String key;
    private String ruta;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String areaMedica;

    public Profesional() {
    }

    public Profesional(String key, String ruta, String nombre, String apellido, String especialidad, String areaMedica) {
        this.key = key;
        this.ruta = ruta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.areaMedica = areaMedica;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String getAreaMedica() {
        return areaMedica;
    }

    public void setAreaMedica(String areaMedica) {
        this.areaMedica = areaMedica;
    }

}