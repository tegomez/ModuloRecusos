package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Recurso {
    private String nombre;
    private String apellido;
    @Id
    private Long legajo;

    // Getters y setters

    Recurso(){}

    public void setNombre(String name){
        nombre = name;
    }
    public void setApellido(String name){
        apellido = name;
    }
    public void setLegajo(Long padron){
        legajo = padron;
    }
    public String getNombre() {
        return nombre;
    }

    public Long getLegajo() {
        return legajo;
    }
}
