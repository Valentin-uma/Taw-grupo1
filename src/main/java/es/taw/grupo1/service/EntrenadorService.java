package es.taw.grupo1.service;

import es.taw.grupo1.dao.EntrenadorRepository;
import es.taw.grupo1.dao.RutinaRepository;
import es.taw.grupo1.dao.SesionHasEjercicioRepository;
import es.taw.grupo1.dao.SesionRepository;
import es.taw.grupo1.dto.EntrenadorDTO;
import es.taw.grupo1.entity.Entrenador;
import es.taw.grupo1.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*

AUTOR: Valentin Pecqueux 20%
AUTOR: Rubén Ipiña 80%

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
        return entrenadorRepository.getEntrenadorByIdUser(userId);
    }

    public Boolean existsByUsuarioIdusuario_Id(Usuario usuario){
        return entrenadorRepository.existsByUsuarioIdusuario_Id(usuario.getId());
    }
}
