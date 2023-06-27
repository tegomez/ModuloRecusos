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
import java.util.UUID;

@Service
public class CargaHorasService {

    @Autowired
    private CargaHorasRepository cargaHorasRepository;

    public boolean cargarHoras(long legajo, long tarea, int cantidadHoras, String fecha) {
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

    private Long generarIdUnico() {
        //genera id random
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
