package es.taw.grupo1.dao;

import es.taw.grupo1.entity.Cliente;
import es.taw.grupo1.entity.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    List<Cliente> findByEntrenadorIdentrenador(Entrenador entrenador);

}
