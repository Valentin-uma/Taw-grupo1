package es.taw.grupo1.service;

import es.taw.grupo1.dao.EntrenadorRepository;
import es.taw.grupo1.dao.RutinaRepository;
import es.taw.grupo1.dao.SesionHasEjercicioRepository;
import es.taw.grupo1.dao.SesionRepository;
import es.taw.grupo1.dto.AdministradorDTO;
import es.taw.grupo1.entity.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Autor: Valentin Pecqueux

@Service
public class AdministradorService extends DTOService<AdministradorDTO, Administrador>{

    @Autowired
    RutinaRepository rutinaRepository;

    @Autowired
    EntrenadorRepository entrenadorRepository;

    @Autowired
    SesionRepository sesionRepository;

    @Autowired
    SesionHasEjercicioRepository sesionHasEjercicioRepository;
}
