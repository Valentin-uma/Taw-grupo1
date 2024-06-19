package es.taw.grupo1.dao;

import es.taw.grupo1.entity.Rutina;
import es.taw.grupo1.entity.Sesion;
import es.taw.grupo1.entity.SesionHasEjercicio;
import es.taw.grupo1.entity.SesionHasEjercicioId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*

AUTOR: Valentin Pecqueux

 */

public interface SesionHasEjercicioRepository extends JpaRepository<SesionHasEjercicio, SesionHasEjercicioId> {
    void deleteBySesionIdsesion(Sesion sesion);
    List<SesionHasEjercicio> findBySesionIdsesion(Sesion sesion);


}
