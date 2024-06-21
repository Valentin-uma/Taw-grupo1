package es.taw.grupo1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfeedback", nullable = false)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_idcliente", nullable = false)
    private Cliente clienteIdcliente;

    @Column(name = "series")
    private Integer series;

    @Column(name = "repeticiones")
    private Integer repeticiones;

    @Column(name = "peso")
    private Integer peso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "sesion_has_ejercicio_sesion_idsesion", referencedColumnName = "sesion_idsesion", nullable = false),
            @JoinColumn(name = "sesion_has_ejercicio_ejercicio_idejercicio", referencedColumnName = "ejercicio_idejercicio", nullable = false)
    })
    private SesionHasEjercicio sesionHasEjercicio;

}
