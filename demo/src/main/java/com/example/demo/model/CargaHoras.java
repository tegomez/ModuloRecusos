package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class CargaHoras {
    @Id
    private Long id;

    private Long legajo;

    private Long tarea;

    private Integer cantidadHoras;

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

    public Integer getCantidadHoras() {
        return cantidadHoras;
    }

    public void setCantidadHoras(Integer horas) {
        this.cantidadHoras = horas;
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
