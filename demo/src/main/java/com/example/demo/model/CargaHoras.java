package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CargaHoras {
    @Id
    private Long id;

    private Long legajo;

    private Long tarea;

    private Integer horas;

    private String fecha;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLegajo() {
        return legajo;
    }

    public void setLegajo(Long legajo) {
        this.legajo = legajo;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setTarea(Long tarea) {
        this.tarea = tarea;
    }

    public Long getTarea() {
        return tarea;
    }
}
