package com.example.demo.repository;
import com.example.demo.model.Recurso;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursoRepository extends CrudRepository<Recurso, Long> {
        Optional<Recurso> findByLegajo(Long legajo);

        @Override
        List<Recurso> findAll();

}
