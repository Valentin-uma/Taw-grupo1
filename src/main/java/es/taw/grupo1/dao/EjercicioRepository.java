package es.taw.grupo1.dao;

import es.taw.grupo1.entity.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {
}
