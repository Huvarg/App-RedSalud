package com.example.redsalud.Modelo;

public class Usuario {

    private String urlImg;
    private String nombre;
    private String apellido;
    private String correo;
    private String pass;

    public Usuario(String urlImg, String nombre, String apellido, String correo, String pass) {
        this.urlImg = urlImg;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.pass = pass;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}