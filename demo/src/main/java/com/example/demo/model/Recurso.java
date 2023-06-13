package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Recurso {
    private String nombre;
    private String apellido;
    @Id
    private Long LEGAJO;
}
