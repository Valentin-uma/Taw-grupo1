package es.taw.grupo1.dao;

import es.taw.grupo1.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*

AUTOR: Valentin Pecqueux

 */

public interface SesionHasEjercicioRepository extends JpaRepository<SesionHasEjercicio, SesionHasEjercicioId> {
    void deleteBySesionIdsesion(Sesion sesion);
    List<SesionHasEjercicio> findBySesionIdsesion(Sesion sesion);

    // Rubén Ipiña Rivas
    SesionHasEjercicio findSesionHasEjercicioByEjercicioIdejercicioAndSesionIdsesion(Ejercicio ejercicio, Sesion sesion);


}
