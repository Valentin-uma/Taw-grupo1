package es.taw.grupo1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcliente", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_idusuario", nullable = false)
    private Usuario usuarioIdusuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrenador_identrenador")
    private Entrenador entrenadorIdentrenador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rutina_idrutina")
    private Rutina rutinaIdrutina;

}