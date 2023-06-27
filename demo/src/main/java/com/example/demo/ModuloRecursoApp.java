package com.example.demo;
import com.example.demo.model.Recurso;
import com.example.demo.model.CargaHoras;

import com.example.demo.service.CargaHorasService;
import com.example.demo.service.RecursoService;

import java.util.Collection;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@Configuration
@EnableSwagger2
public class ModuloRecursoApp {

    @Autowired
    private RecursoService recursoService;

    public static void main(String[] args) {
        SpringApplication.run(ModuloRecursoApp.class, args);
    }


    @GetMapping("/recursos/{legajo}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Recurso> getLegajo(@PathVariable Long legajo) {
        Optional<Recurso> recurso = recursoService.findByLegajo(legajo);
        if (!recurso.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.of(recurso);
    }

    @GetMapping("/recursos")
    public Collection<Recurso> getRecursos() {
        return recursoService.getRecursos();
    }

    @PostMapping("/cargaHoras")
    public ResponseEntity<String> cargarHoras(@RequestParam long legajo,@RequestParam long tarea,@RequestParam int cantidadHoras,@RequestParam String fecha) {
        boolean cargaExitosa = recursoService.cargarHoras(legajo,tarea,cantidadHoras,fecha);
        if (cargaExitosa) {
            return ResponseEntity.ok("Carga de horas exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo cargar las horas");
        }
    }

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

}
