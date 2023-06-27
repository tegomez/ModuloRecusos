package com.example.demo.service;
import com.example.demo.model.CargaHoras;
import com.example.demo.model.Recurso;
import com.example.demo.repository.CargaHorasRepository;
import org.springframework.stereotype.Service;
import com.example.demo.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;

@Service
public class RecursoService {
    @Autowired
    private RecursoRepository recursoRepository;
    @Autowired
    private CargaHorasRepository cargaHorasRepository;

    public Optional<Recurso> findByLegajo(Long legajo) {
        try {
            URL url = new URL("https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.1/m/api/recursos");
            ObjectMapper mapper = new ObjectMapper();

            Recurso[] recursos = mapper.readValue(url, Recurso[].class);
            for (Recurso recurso : recursos) {
                if (recurso.getLegajo().equals(legajo)) {
                    return Optional.ofNullable(recurso);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejar el error de lectura del JSON
        }
        return Optional.empty();
    }
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
