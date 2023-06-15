package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CargaHoras {
    @Id
    private Long id;

    private Long LEGAJO;

    private Long tarea;

    private Integer horas;

    private String fecha;
}
