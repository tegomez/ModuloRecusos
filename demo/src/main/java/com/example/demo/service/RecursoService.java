package com.example.demo.service;
import com.example.demo.model.ApiExterna;
import com.example.demo.model.CargaHoras;
import com.example.demo.model.Recurso;
import com.example.demo.repository.CargaHorasRepository;
import org.springframework.stereotype.Service;
import com.example.demo.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@Service
public class RecursoService {
    @Autowired
    private RecursoRepository recursoRepository;
    @Autowired
    private CargaHorasRepository cargaHorasRepository;
    @Autowired
    CloseableHttpClient httpClient;


    public Optional<Recurso> findByLegajo(Long legajo) {
        ApiExterna apiExterna = new ApiExterna();
        return apiExterna.findByLegajo(legajo);
    }
    public Collection<Recurso> getRecursos(){
        ApiExterna apiExterna = new ApiExterna();
        return apiExterna.getRecursos();
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
    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClients.createDefault();
    }
}
