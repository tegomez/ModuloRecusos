package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Recurso {
    @Id
    private long legajo;
    private String Nombre;
    private String Apellido;

    // Constructor, getters y setters

    public Recurso() {
    }

    public long getLegajo() {
        return legajo;
    }

    public void setLegajo(long legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }
}