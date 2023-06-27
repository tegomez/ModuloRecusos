package com.example.demo.service;
import com.example.demo.model.CargaHoras;
import com.example.demo.model.Recurso;
import com.example.demo.repository.CargaHorasRepository;
import org.springframework.stereotype.Service;
import com.example.demo.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class RecursoService {
    @Autowired
    private RecursoRepository recursoRepository;

    public Optional<Recurso> findByLegajo(Long legajo) {
        return recursoRepository.findByLegajo(legajo);
    }

    public boolean cargarHoras(long legajo, long tarea, int cantidadHoras, String fecha) {
        try {
            CargaHorasService cargaHorasService = new CargaHorasService();
            return cargaHorasService.cargarHoras(legajo, tarea, cantidadHoras, fecha);
        } catch (Exception e) {
            return false; // La carga de horas falló
        }
    }
}