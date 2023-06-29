package com.example.demo;
import com.example.demo.model.Recurso;
import com.example.demo.model.CargaHoras;
import com.example.demo.exceptions.*;
import com.example.demo.service.CargaHorasService;
import com.example.demo.service.RecursoService;
import java.util.Optional;
import java.util.Collection;

import io.swagger.annotations.*;
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
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@Configuration
@EnableSwagger2
public class ModuloRecursoApp {

    @Autowired
    private RecursoService recursoService;
    @Autowired
    private CargaHorasService cargaHorasService;

    public static void main(String[] args) {

        SpringApplication.run(ModuloRecursoApp.class, args);
    }
/*
    @PostMapping("/cargaHoras")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cargar horas", notes = "Cargar las horas trabajadas por un recurso")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Carga de horas exitosa"),
        @ApiResponse(code = 500, message = "No se pudo cargar las horas")
})
public ResponseEntity<String> cargarHoras(
        @ApiParam(value = "Legajo del recurso", example = "123") @RequestParam Long legajo,
        @ApiParam(value = "Tarea realizada", example = "456") @RequestParam Long tarea,
        @ApiParam(value = "Cantidad de horas trabajadas", example = "8") @RequestParam Integer cantidadHoras,
        @ApiParam(value = "Fecha de la carga de horas", example = "2023-06-26") @RequestParam String fecha) {
    boolean cargaExitosa = cargaHorasService.cargarHoras(legajo, tarea, cantidadHoras, fecha);
    if (cargaExitosa) {
        return ResponseEntity.ok("Carga de horas exitosa");
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo cargar las horas");
    }
}
*/

    @PostMapping("/cargaHoras")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cargar horas", notes = "Cargar las horas trabajadas por un recurso")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Carga de horas exitosa"),
            @ApiResponse(code = 400, message = "Datos de carga de horas inválidos"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "No se pudo cargar las horas")
    })
    public ResponseEntity<String> cargarHoras(
            @ApiParam(value = "Legajo del recurso", example = "123") @RequestParam Long legajo,
            @ApiParam(value = "Tarea realizada", example = "456") @RequestParam Long tarea,
            @ApiParam(value = "Cantidad de horas trabajadas", example = "8") @RequestParam Integer cantidadHoras,
            @ApiParam(value = "Fecha de la carga de horas", example = "2023-06-26") @RequestParam String fecha) {
        try {
            cargaHorasService.cargarHoras(legajo, tarea, cantidadHoras, fecha);
            return ResponseEntity.status(HttpStatus.CREATED).body("Carga de horas exitosa");
        } catch (CantidadDeHorasNegativasException e) {
            return ResponseEntity.badRequest().body("No se permiten horas negativas");
        } catch (FormatoFechaIncorrectoException e) {
            return ResponseEntity.badRequest().body("El formato de la fecha es incorrecto. Debe ser AAAA-MM-DD");
        } catch (CantidadDeHorasNoNumericoException e) {
            return ResponseEntity.badRequest().body("La cantidad de horas debe ser numérica");
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El recurso con el legajo especificado no fue encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo realizar la carga horas");
        }
    }

    @GetMapping("/recursos/{legajo}")
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

    @GetMapping("/cargaHoras/{legajo}")
    public ResponseEntity<List<CargaHoras>> getCargaHorasByLegajo(@PathVariable Long legajo) {
        List<CargaHoras> cargaHoras = cargaHorasService.getCargaHorasByLegajo(legajo);
        if (cargaHoras.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cargaHoras);
    }
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                .build();
    }
}
