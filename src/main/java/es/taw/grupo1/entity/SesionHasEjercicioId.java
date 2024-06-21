package es.taw.grupo1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class SesionHasEjercicioId implements java.io.Serializable {
    private static final long serialVersionUID = 8691311730922720511L;
    @Column(name = "sesion_idsesion", nullable = false)
    private Integer sesionIdsesion;

    @Column(name = "ejercicio_idejercicio", nullable = false)
    private Integer ejercicioIdejercicio;

    public SesionHasEjercicioId() {}

    public SesionHasEjercicioId(Integer sesionIdsesion, Integer ejercicioIdejercicio) {
        this.sesionIdsesion = sesionIdsesion;
        this.ejercicioIdejercicio = ejercicioIdejercicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SesionHasEjercicioId entity = (SesionHasEjercicioId) o;
        return Objects.equals(this.ejercicioIdejercicio, entity.ejercicioIdejercicio) &&
                Objects.equals(this.sesionIdsesion, entity.sesionIdsesion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ejercicioIdejercicio, sesionIdsesion);
    }

}