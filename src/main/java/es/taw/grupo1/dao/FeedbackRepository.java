package es.taw.grupo1.dao;

import es.taw.grupo1.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/*

AUTOR: Valentin Pecqueux    20%
AUTOR: Rubén Ipiña    80%

 */

public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {
    List<Feedback> findByClienteIdclienteId(Integer clienteId);

    // Rubén Ipiña Rivas
    @Query("select f from Feedback f where f.fecha >= :fechaMin and f.fecha <= :fechaMax and (f.series < f.sesionHasEjercicio.series or f.repeticiones < f.sesionHasEjercicio.repeticiones or f.peso < f.sesionHasEjercicio.peso )")
    List<Feedback> findFilterNoCompleto(@Param("fechaMin") Date fechaMin, @Param("fechaMax") Date fechaMax);

    @Query("select f from Feedback f where f.fecha >= :fechaMin and f.fecha <= :fechaMax and (f.series >= f.sesionHasEjercicio.series and f.repeticiones >= f.sesionHasEjercicio.repeticiones and f.peso >= f.sesionHasEjercicio.peso )")
    List<Feedback> findFilterCompleto(@Param("fechaMin") Date fechaMin, @Param("fechaMax") Date fechaMax);
}
