package com.example.demo;

import com.example.demo.model.Recurso;
import com.example.demo.model.Tarea;
import com.example.demo.model.CargaHoras;
import com.example.demo.service.CargaHorasService;
import org.aspectj.lang.annotation.Before;
import cucumber.api.*;
import org.junit.Assert;

public class ModuloRecursosTest {

    private long legajo;
    private long tarea;
    private int cantidadHoras;
    private String fecha;
    private CargaHorasService cargaHorasService;
    private boolean cargaExitosa;

    @Given("existe un recurso con el número de legajo {long}")
    public void existeUnRecursoConElNumeroDeLegajo(long legajo) {
        this.legajo = legajo;
        // Aca tendriamos que cosnsultar la base de datos
    }

    @Given("existe una tarea con el id {long}")
    public void existeUnaTareaConElId(long tarea) {
        this.tarea = tarea;
        // entiendo que aca seria consultar las bbdd de tareas?
    }

    @When("se carga correctamente la cantidad de {int} horas para la tarea en la fecha {string}")
    public void seCargaCorrectamenteLaCantidadDeHorasParaLaTareaEnLaFecha(int cantidadHoras, String fecha) {
        this.cantidadHoras = cantidadHoras;
        this.fecha = fecha;

        cargaHorasService = new CargaHorasService();
        cargaExitosa = cargaHorasService.cargarHoras(legajo, tarea, cantidadHoras, fecha);
    }

    @Then("se registra la carga de horas exitosamente")
    public void seRegistraLaCargaDeHorasExitosamente() {
        Assert.assertTrue(cargaExitosa);
        //lo registra en la bbdd?
    }
}