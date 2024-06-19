package es.taw.grupo1.dao;

import es.taw.grupo1.entity.Entrenador;
import es.taw.grupo1.entity.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RutinaRepository extends JpaRepository<Rutina,Integer> {
    List<Rutina> findByEntrenadorIdentrenador(Entrenador entrenador);

    @Query("select r from Rutina r where r.tipo = :filtro")
    public List<Rutina> filtrarRutinaByType(@Param("filtro")String filtro);
}
