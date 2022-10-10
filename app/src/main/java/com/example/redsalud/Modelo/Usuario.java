package com.example.redsalud.Modelo;

import java.io.Serializable;

public class Usuario implements Serializable {

    private Integer id;
    private String nombre;
    private String apellido;
    private String sexo;
    private String fNac;
    private String correo;
    private String pass1;
    private String pass2;

    public Usuario(Integer id, String nombre, String apellido, String sexo, String fNac, String correo, String pass1, String pass2) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.fNac = fNac;
        this.correo = correo;
        this.pass1 = pass1;
        this.pass2 = pass2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getfNac() {
        return fNac;
    }

    public void setfNac(String fNac) {
        this.fNac = fNac;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

}