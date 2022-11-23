package com.example.redsalud.Modelo;

import java.io.Serializable;

public class Profesional implements Serializable {

    private String idProfesional;
    private String ruta;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String areaMedica;
    private String idCentroMedico;

    public Profesional() {
    }

    public Profesional(String idProfesional, String ruta, String nombre, String apellido, String especialidad, String areaMedica, String idCentroMedico) {
        this.idProfesional = idProfesional;
        this.ruta = ruta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.areaMedica = areaMedica;
        this.idCentroMedico = idCentroMedico;
    }

    public String getIdProfesional() {
        return idProfesional;
    }

    public void setIdProfesional(String idProfesional) {
        this.idProfesional = idProfesional;
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

    public String getIdCentroMedico() {
        return idCentroMedico;
    }

    public void setIdCentroMedico(String idCentroMedico) {
        this.idCentroMedico = idCentroMedico;
    }

}