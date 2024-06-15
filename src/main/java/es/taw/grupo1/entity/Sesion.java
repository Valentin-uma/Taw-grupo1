package es.taw.grupo1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sesion")
public class Sesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsesion", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "dia", nullable = false)
    private String dia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rutina_idrutina", nullable = false)
    private Rutina rutinaIdrutina;

}