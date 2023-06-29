package com.example.demo.service;
import com.example.demo.exceptions.CantidadDeHorasNegativasException;
import com.example.demo.exceptions.RecursoNoEncontradoException;
import com.example.demo.exceptions.FormatoFechaIncorrectoException;
import com.example.demo.exceptions.CantidadDeHorasNoNumericoException;
import java.time.LocalDate;
import com.example.demo.model.Recurso;
import com.example.demo.model.Tarea;
import com.example.demo.model.CargaHoras;
import com.example.demo.repository.CargaHorasRepository;
import com.example.demo.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.Optional;

@Service
public class CargaHorasService {

    @Autowired
    private CargaHorasRepository cargaHorasRepository;
    @Autowired
    private RecursoService recursoService;

    public boolean cargarHoras(long legajo, long tarea, int cantidadHoras, String fecha) {
        Optional<Recurso> recursoOptional = recursoService.findByLegajo(legajo);
        if (recursoOptional.isEmpty()) {
            throw new RecursoNoEncontradoException("El recurso con legajo " + legajo + " no se encuentra.");
        }

        if (cantidadHoras < 0) {
            throw new CantidadDeHorasNegativasException("La cantidad de horas no puede ser negativa.");
        }

        if (!esNumerico(cantidadHoras)) {
            throw new CantidadDeHorasNoNumericoException("La cantidad de horas debe ser un valor numérico.");
        }

        try {
            // Verificar el formato de la fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(fecha, formatter);
        } catch (DateTimeParseException e) {
            throw new FormatoFechaIncorrectoException("El formato de la fecha es incorrecto. Debe ser yyyy-MM-dd");
        }

        try {
            CargaHoras cargaHoras = new CargaHoras();
            cargaHoras.setId(generarIdUnico()); // Asigna un valor único al id
            cargaHoras.setLegajo(legajo);
            cargaHoras.setTarea(tarea);
            cargaHoras.setCantidadHoras(cantidadHoras);
            cargaHoras.setFecha(fecha);

            cargaHorasRepository.save(cargaHoras);

            return true; // La carga de horas se realizó exitosamente
        } catch (Exception e) {
            e.printStackTrace();
            return false; // La carga de horas falló
        }
    }

    public List<CargaHoras> getCargaHorasByLegajo(Long legajo) {
        return cargaHorasRepository.findByLegajo(legajo);
    }

    private boolean esNumerico(int cantidadHoras) {
        try {
            Integer.parseInt(String.valueOf(cantidadHoras));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private Long generarIdUnico() {
        Random random = new Random();
        return random.nextInt(100) + 1L; // Genera un número aleatorio entre 1 y 100 (inclusive)
    }
}
