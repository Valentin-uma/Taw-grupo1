package es.taw.grupo1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "entrenador")
public class Entrenador {
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

}