package es.taw.grupo1.dao;

import es.taw.grupo1.entity.Cliente;
import es.taw.grupo1.entity.Entrenador;
import es.taw.grupo1.entity.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/*

AUTOR: Valentin Pecqueux    40%
AUTOR: Rubén Ipiña Rivas    60%

 */

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    List<Cliente> findByEntrenadorIdentrenador(Entrenador entrenador);

    // Autor: Rubén Ipiña Rivas

    @Query("select c from Cliente c where c.usuarioIdusuario = :idusuario")
    Cliente findCienteByUsuario(@Param("idusuario") Usuario idusuario);

    boolean existsByUsuarioIdusuario_Id(Integer usuarioId);

}
