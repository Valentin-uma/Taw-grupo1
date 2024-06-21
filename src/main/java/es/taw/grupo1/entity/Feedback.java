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

    @Column(name = "series_hechas", nullable = false)
    private Integer series_hechas;

    @Column(name = "repeticiones_hechas", nullable = false)
    private Integer repeticiones_hechas;

    @Column(name = "peso", nullable = false)
    private Integer peso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "sesion_idsesion", referencedColumnName = "sesion_idsesion", nullable = false),
            @JoinColumn(name = "ejercicio_idejercicio", referencedColumnName = "ejercicio_idejercicio", nullable = false)
    })
    private SesionHasEjercicio sesionHasEjercicio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_idcliente", nullable = false)
    private Cliente clienteIdcliente;

}
