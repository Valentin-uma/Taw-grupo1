package es.taw.grupo1.service;

import es.taw.grupo1.dao.*;
import es.taw.grupo1.dto.FeedbackDTO;
import es.taw.grupo1.entity.Feedback;
import es.taw.grupo1.entity.Sesion;
import es.taw.grupo1.entity.*;
import es.taw.grupo1.ui.FiltroHistorial;
import es.taw.grupo1.ui.FormFecha;
import es.taw.grupo1.ui.FormSubmitFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/*

AUTOR: Valentin Pecqueux    10%
AUTOR: Rubén Ipiña          90%

 */
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

    @Autowired
    EjercicioRepository ejercicioRepository;

    public List<Feedback> findByClienteIdclienteId(int idcliente) {
        return feedbackRepository.findByClienteIdclienteId(idcliente);
    }

    /*public void createTestFeedbackForRutina(String idRutina, String idCliente){
        Feedback feedback = new Feedback();

        List<Sesion> sesions = sesionRepository.findByRutinaIdrutina(rutinaRepository.getReferenceById(Integer.parseInt(idRutina)));

        feedback.setSesionIdsesion(sesions.get(0));
        feedback.setDescripcion("El ejercicio 1 estaba muy dificil !!!");
        feedback.setClienteIdcliente(clienteRepository.getReferenceById(Integer.parseInt(idCliente)));
        feedback.setFecha(new Date().toInstant());
        feedbackRepository.save(feedback);
    }*/

    public Map<String, List<Feedback>> getFeedbackMap(FiltroHistorial filtroHistorial){

        if(filtroHistorial.getFechaMax().equals("") || filtroHistorial.getFechaMin().equals("")){
            return null;
        }

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaMax = null;
        Date fechaMin = null;
        try {
            fechaMax = formato.parse(filtroHistorial.getFechaMax());
            fechaMin = formato.parse(filtroHistorial.getFechaMin());
        } catch (ParseException e) {
            return null;
        }

        List<Feedback> feedbackList;

        if(filtroHistorial.getCompleto() == null){
            return null;
        }

        if(filtroHistorial.getCompleto().equals("si")){
            feedbackList = feedbackRepository.findFilterCompleto(fechaMin, fechaMax);
        }else{
            feedbackList = feedbackRepository.findFilterNoCompleto(fechaMin, fechaMax);
        }

        SimpleDateFormat formatoddmmyyyy = new SimpleDateFormat("dd-MM-yyyy");

        Map<String, List<Feedback>> feedbackMap = new HashMap<>();
        for(Feedback feedback : feedbackList){
            if(!feedbackMap.containsKey(feedback.getFecha().toString())){
                ArrayList<Feedback> feedbacks = new ArrayList<>();
                feedbackMap.put(formatoddmmyyyy.format(feedback.getFecha()), feedbacks);
                feedbacks.add(feedback);
            }else{
                feedbackMap.get(feedback.getFecha().toString()).add(feedback);
            }
        }

        return feedbackMap;

    }

    public Map<String, List<Feedback>> getFeedbackMap(){

        SimpleDateFormat formatoddmmyyyy = new SimpleDateFormat("dd-MM-yyyy");

        List<Feedback> feedbackList = feedbackRepository.findAll();
        Map<String, List<Feedback>> feedbackMap = new HashMap<>();
        for(Feedback feedback : feedbackList){
            if(!feedbackMap.containsKey(feedback.getFecha().toString())){
                ArrayList<Feedback> feedbacks = new ArrayList<>();
                feedbackMap.put(formatoddmmyyyy.format(feedback.getFecha()), feedbacks);
                feedbacks.add(feedback);
            }else{
                feedbackMap.get(feedback.getFecha().toString()).add(feedback);
            }
        }

        return feedbackMap;

    }

    public void submitFeedback(FormSubmitFeedback formSubmitFeedback, Usuario usuario){

        if(formSubmitFeedback.getSeriesRealizadas() == null || formSubmitFeedback.getRepeticionesRealizadas() == null || formSubmitFeedback.getPesoUtilizado() == null || formSubmitFeedback.getFechaSubmit().equals("") ){
            return;
        }

        Feedback feedback = new Feedback();
        feedback.setDescripcion(formSubmitFeedback.getComentarios());
        feedback.setRepeticiones(formSubmitFeedback.getRepeticionesRealizadas());
        feedback.setSeries(formSubmitFeedback.getSeriesRealizadas());
        feedback.setPeso(formSubmitFeedback.getPesoUtilizado());
        Cliente cliente = clienteRepository.findCienteByUsuario(usuario);
        Ejercicio ejercicio = ejercicioRepository.findById(formSubmitFeedback.getEjercicioId()).get();
        Sesion sesion = sesionRepository.findById(formSubmitFeedback.getSesionId()).get();
        feedback.setSesionHasEjercicio(sesionHasEjercicioRepository.findSesionHasEjercicioByEjercicioIdejercicioAndSesionIdsesion(ejercicio, sesion));

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = formato.parse(formSubmitFeedback.getFechaSubmit());
        } catch (ParseException e) {
            return;
        }

        feedback.setFecha(fecha);
        feedback.setClienteIdcliente(cliente);
        feedbackRepository.save(feedback);
    }

    public void eliminarFeedbackById(Integer id){
        Feedback feedback = feedbackRepository.findById(id).get();
        feedbackRepository.delete(feedback);
    }




}
