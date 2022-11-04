package com.example.redsalud.Modelo;

import java.io.Serializable;

public class Noticia implements Serializable {

    private String tipo;
    private String rutaimg;
    private String titulo;
    private String contenido;

    public Noticia() {
    }

    public Noticia(String tipo, String rutaimg, String titulo, String contenido) {
        this.tipo = tipo;
        this.rutaimg = rutaimg;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRutaimg() {
        return rutaimg;
    }

    public void setRutaimg(String rutaimg) {
        this.rutaimg = rutaimg;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

}