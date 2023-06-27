package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long legajo;
    private String Nombre;
    private String Apellido;



    // Getters y setters

    Recurso(){}

    public void setNombre(String name){
        Nombre = name;
    }
    public void setApellido(String name){
        Apellido = name;
    }
    public void setLegajo(Long padron){
        legajo = padron;
    }
    public String getNombre() {
        return Nombre;
    }

    public Long getLegajo() {
        return legajo;
    }
}
