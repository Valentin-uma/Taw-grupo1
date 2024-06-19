package es.taw.grupo1.service;

import es.taw.grupo1.dao.EntrenadorRepository;
import es.taw.grupo1.dao.RutinaRepository;
import es.taw.grupo1.dao.SesionHasEjercicioRepository;
import es.taw.grupo1.dao.SesionRepository;
import es.taw.grupo1.dto.EntrenadorDTO;
import es.taw.grupo1.entity.Entrenador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*

AUTOR: Valentin Pecqueux

 */
@Service
public class EntrenadorService extends DTOService<EntrenadorDTO, Entrenador>{

    @Autowired
    RutinaRepository rutinaRepository;

    @Autowired
    EntrenadorRepository entrenadorRepository;

    @Autowired
    SesionRepository sesionRepository;

    @Autowired
    SesionHasEjercicioRepository sesionHasEjercicioRepository;

    public Entrenador getEntrenadorByIdUser(int userId){
        Entrenador entrenador = entrenadorRepository.getEntrenadorByIdUser(userId);
        return entrenador;
    }
}
