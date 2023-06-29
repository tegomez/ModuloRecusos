package com.example.demo.repository;
import com.example.demo.model.CargaHoras;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CargaHorasRepository extends CrudRepository<CargaHoras, Long> {
    List<CargaHoras> findByLegajo(Long legajo);
}