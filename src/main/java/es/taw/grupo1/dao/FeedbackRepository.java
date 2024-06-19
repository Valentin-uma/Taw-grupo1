package es.taw.grupo1.dao;

import es.taw.grupo1.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {
    List<Feedback> findByClienteIdclienteId(Integer clienteId);
}
