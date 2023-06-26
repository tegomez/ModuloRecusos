package com.example.demo.service;
import java.time.LocalDate;
import com.example.demo.model.Recurso;
import com.example.demo.model.Tarea;
import com.example.demo.model.CargaHoras;
import com.example.demo.repository.CargaHorasRepository;
import com.example.demo.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargaHorasService {

    @Autowired
    private CargaHorasRepository cargaHorasRepository;

    public boolean cargarHoras(long legajo, long tarea, int cantidadHoras, String fecha) {
        try {
            CargaHoras cargaHoras = new CargaHoras();
            cargaHoras.setLegajo(legajo);
            cargaHoras.setTarea(tarea);
            cargaHoras.setHoras(cantidadHoras);
            cargaHoras.setFecha(fecha);

            cargaHorasRepository.save(cargaHoras);

            return true; // La carga de horas se realizó exitosamente
        } catch (Exception e) {
            return false; // La carga de horas falló
        }
    }
}
