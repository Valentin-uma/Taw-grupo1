package es.taw.grupo1.entity;

import es.taw.grupo1.dto.DTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario implements DTO<Usuario> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario", nullable = false)
    private Integer id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "apellido", length = 45)
    private String apellido;

    @Lob
    @Column(name = "rango_edad", nullable = false)
    private String rangoEdad;

    @Lob
    @Column(name = "sexo")
    private String sexo;


    public Usuario toDTO() {
        Usuario usuario = new Usuario();
        usuario.setId(this.id);
        usuario.setEmail(this.email);
        usuario.setContrasena(this.contrasena);
        usuario.setNombre(this.nombre);
        usuario.setApellido(this.apellido);
        usuario.setRangoEdad(this.rangoEdad);
        usuario.setSexo(this.sexo);
        return usuario;

    }
}