package es.taw.grupo1.service;

import es.taw.grupo1.dao.EntrenadorRepository;
import es.taw.grupo1.dao.RutinaRepository;
import es.taw.grupo1.dao.SesionHasEjercicioRepository;
import es.taw.grupo1.dao.SesionRepository;
import es.taw.grupo1.dto.SesionDTO;
import es.taw.grupo1.entity.Sesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesionService extends DTOService<SesionDTO, Sesion> {

    @Autowired
    RutinaRepository rutinaRepository;

    @Autowired
    EntrenadorRepository entrenadorRepository;

    @Autowired
    SesionRepository sesionRepository;

    @Autowired
    SesionHasEjercicioRepository sesionHasEjercicioRepository;
}
