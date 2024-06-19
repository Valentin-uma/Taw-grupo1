package es.taw.grupo1.service;

import es.taw.grupo1.dao.*;
import es.taw.grupo1.dto.FeedbackDTO;
import es.taw.grupo1.entity.Feedback;
import es.taw.grupo1.entity.Sesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FeedbackService extends DTOService<FeedbackDTO, Feedback>{

    @Autowired
    RutinaRepository rutinaRepository;

    @Autowired
    EntrenadorRepository entrenadorRepository;

    @Autowired
    SesionRepository sesionRepository;

    @Autowired
    SesionHasEjercicioRepository sesionHasEjercicioRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    ClienteRepository clienteRepository;

    public List<Feedback> findByClienteIdclienteId(int idcliente) {
        return feedbackRepository.findByClienteIdclienteId(idcliente);
    }

    public void createTestFeedbackForRutina(String idRutina, String idCliente){
        Feedback feedback = new Feedback();

        List<Sesion> sesions = sesionRepository.findByRutinaIdrutina(rutinaRepository.getReferenceById(Integer.parseInt(idRutina)));

        feedback.setSesionIdsesion(sesions.get(0));
        feedback.setDescripcion("El ejercicio 1 estaba muy dificil !!!");
        feedback.setClienteIdcliente(clienteRepository.getReferenceById(Integer.parseInt(idCliente)));
        feedback.setFecha(new Date().toInstant());
        feedbackRepository.save(feedback);
    }
}
