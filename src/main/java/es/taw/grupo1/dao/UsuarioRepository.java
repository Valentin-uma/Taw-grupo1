package es.taw.grupo1.dao;

import es.taw.grupo1.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*

AUTOR: Valentin Pecqueux

 */

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


    @Query("select u.id from Usuario u where u.nombre = :nombre and u.contrasena = :password")
    public int getIdFromNameAndPassword(@Param("nombre")String nombre, @Param("password")String password);

    @Query("select u.id from Usuario u where u.email = :email and u.contrasena = :password")
    public int getIdFromEmailAndPassword(@Param("email")String email, @Param("password")String password);

}
