package com.example.demo.service;
import com.example.demo.model.Recurso;
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
}
