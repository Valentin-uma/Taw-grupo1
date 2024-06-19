package es.taw.grupo1.entity;

import es.taw.grupo1.dto.DTO;
import es.taw.grupo1.dto.RutinaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rutina")
public class Rutina implements DTO<RutinaDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrutina", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entrenador_identrenador", nullable = false)
    private Entrenador entrenadorIdentrenador;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "tipo", length = 45)
    private String tipo;

    @Column(name = "descripcion", length = 400)
    private String descripcion;

    public RutinaDTO toDTO() {

        RutinaDTO rutina = new RutinaDTO();
        rutina.setId(this.id);
        rutina.setNombre(this.nombre);
        rutina.setTipo(this.tipo);
        rutina.setDescripcion(this.descripcion);
        rutina.setEntrenadorId(this.entrenadorIdentrenador.toDTO().getId());

        return rutina;
    }
}