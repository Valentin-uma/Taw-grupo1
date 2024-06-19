package es.taw.grupo1.dao;

import es.taw.grupo1.entity.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer> {

    @Query("select e from Entrenador e where e.usuarioIdusuario.id = :id")
    public Entrenador getEntrenadorByIdUser(@Param("id")int id);

}
