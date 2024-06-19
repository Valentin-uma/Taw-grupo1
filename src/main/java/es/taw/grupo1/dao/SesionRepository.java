package es.taw.grupo1.dao;

import es.taw.grupo1.entity.Rutina;
import es.taw.grupo1.entity.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SesionRepository extends JpaRepository<Sesion,Integer> {
    List<Sesion> findByRutinaIdrutina(Rutina rutina);
}
