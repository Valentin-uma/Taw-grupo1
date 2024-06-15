package es.taw.grupo1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "feedback")
public class Feedback {
    @EmbeddedId
    private FeedbackId id;

    @MapsId("sesionIdsesion")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sesion_idsesion", nullable = false)
    private Sesion sesionIdsesion;

    @Column(name = "fecha", nullable = false)
    private Instant fecha;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

}