package es.taw.grupo1.service;

import es.taw.grupo1.dao.*;
import es.taw.grupo1.dto.EjercicioDTO;
import es.taw.grupo1.entity.Ejercicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*

AUTOR: Valentin Pecqueux

 */
@Service
public class EjercicioService extends DTOService<EjercicioDTO, Ejercicio>{

    @Autowired
    RutinaRepository rutinaRepository;

    @Autowired
    EntrenadorRepository entrenadorRepository;

    @Autowired
    SesionRepository sesionRepository;

    @Autowired
    SesionHasEjercicioRepository sesionHasEjercicioRepository;
    @Autowired
    private EjercicioRepository ejercicioRepository;

    public List<Ejercicio> findAll() {
        List<Ejercicio> ejercicios = ejercicioRepository.findAll();
        return ejercicios;
    }
}
