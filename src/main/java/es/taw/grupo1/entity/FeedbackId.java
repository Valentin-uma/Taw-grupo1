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
public class FeedbackId implements java.io.Serializable {
    private static final long serialVersionUID = -9139898579589259343L;
    @Column(name = "idfeedback", nullable = false)
    private Integer idfeedback;

    @Column(name = "sesion_idsesion", nullable = false)
    private Integer sesionIdsesion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FeedbackId entity = (FeedbackId) o;
        return Objects.equals(this.idfeedback, entity.idfeedback) &&
                Objects.equals(this.sesionIdsesion, entity.sesionIdsesion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idfeedback, sesionIdsesion);
    }

}