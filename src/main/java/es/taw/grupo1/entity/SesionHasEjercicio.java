package es.taw.grupo1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sesion_has_ejercicio")
public class SesionHasEjercicio {
    @EmbeddedId
    private SesionHasEjercicioId id;

    @MapsId("sesionIdsesion")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sesion_idsesion", nullable = false)
    private Sesion sesionIdsesion;

    @MapsId("ejercicioIdejercicio")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ejercicio_idejercicio", nullable = false)
    private Ejercicio ejercicioIdejercicio;

    @Column(name = "series", nullable = false)
    private Integer series;

    @Column(name = "repeticiones", nullable = false)
    private Integer repeticiones;

    @Column(name = "peso", nullable = false)
    private Integer peso;

}