package com.example.demo;
import com.example.demo.model.Recurso;
import com.example.demo.model.CargaHoras;

import com.example.demo.service.CargaHorasService;
import com.example.demo.service.RecursoService;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@Configuration

public class ModuloRecursoApp {

    @Autowired
    private CargaHorasService cargaHorasService;
    @Autowired
    private RecursoService recursoService;

    @PostMapping("/cargaHoras")
    public ResponseEntity<String> cargarHoras(@RequestBody CargaHoras cargaHoras) {
        boolean cargaExitosa = cargaHorasService.cargarHoras(cargaHoras.getLegajo(), cargaHoras.getTarea(), cargaHoras.getHoras(), cargaHoras.getFecha());
        if (cargaExitosa) {
            return ResponseEntity.ok("Carga de horas exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo cargar las horas");
        }
    }

    @GetMapping("/{legajo}")
    public ResponseEntity<Recurso> getLegajo(@PathVariable Long LEGAJO) {
        Optional<Recurso> recurso = recursoService.findByLegajo(LEGAJO);
        if (!recurso.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.of(recurso);
    }
}
