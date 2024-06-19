package es.taw.grupo1.entity;

import es.taw.grupo1.dto.DTO;
import es.taw.grupo1.dto.EntrenadorDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "entrenador")
public class Entrenador implements DTO<EntrenadorDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identrenador", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "tipo", nullable = false)
    private String tipo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_idusuario", nullable = false)
    private Usuario usuarioIdusuario;


    public EntrenadorDTO toDTO() {
        EntrenadorDTO entrenador = new EntrenadorDTO();
        entrenador.setId(this.id);
        entrenador.setTipo(this.tipo);
        entrenador.setUsuarioIdusuario(this.usuarioIdusuario.getId());
        return null;
    }
}