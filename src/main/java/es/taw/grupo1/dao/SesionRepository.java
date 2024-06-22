package es.taw.grupo1.dao;

import es.taw.grupo1.entity.Rutina;
import es.taw.grupo1.entity.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*

AUTOR: Valentin Pecqueux

 */

public interface SesionRepository extends JpaRepository<Sesion,Integer> {


    List<Sesion> findByRutinaIdrutina(Rutina rutina);

    Sesion findByRutinaIdrutinaAndDia(Rutina rutina, String dia);

}
