package es.taw.grupo1.service;

import es.taw.grupo1.dao.EntrenadorRepository;
import es.taw.grupo1.dao.RutinaRepository;
import es.taw.grupo1.dao.SesionHasEjercicioRepository;
import es.taw.grupo1.dao.SesionRepository;
import es.taw.grupo1.dto.SesionHasEjercicioDTO;
import es.taw.grupo1.entity.SesionHasEjercicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesionHasEjercicioService extends DTOService<SesionHasEjercicioDTO, SesionHasEjercicio> {

    @Autowired
    RutinaRepository rutinaRepository;

    @Autowired
    EntrenadorRepository entrenadorRepository;

    @Autowired
    SesionRepository sesionRepository;

    @Autowired
    SesionHasEjercicioRepository sesionHasEjercicioRepository;
}
