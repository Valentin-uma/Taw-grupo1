package es.taw.grupo1.service;

import es.taw.grupo1.dao.*;
import es.taw.grupo1.dto.ClienteDTO;
import es.taw.grupo1.entity.Cliente;
import es.taw.grupo1.entity.Entrenador;
import es.taw.grupo1.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*

AUTOR: Valentin Pecqueux 50%
AUTOR: Rubén Ipiña 50%

 */

@Service
public class ClienteService extends DTOService<ClienteDTO, Cliente>{

    @Autowired
    RutinaRepository rutinaRepository;

    @Autowired
    EntrenadorRepository entrenadorRepository;

    @Autowired
    SesionRepository sesionRepository;

    @Autowired
    SesionHasEjercicioRepository sesionHasEjercicioRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findByEntrenadorIdentrenador(Entrenador entrenador){
        List<Cliente> clientes = clienteRepository.findByEntrenadorIdentrenador(entrenador);
        return clientes;
    }

    public Cliente getReferenceById(int id) {
        return clienteRepository.getReferenceById(id);
    }

    public void changeClientRutina(String clienteId, String rutinaSelect){

        Cliente cliente = clienteRepository.getReferenceById(Integer.parseInt(clienteId));
        cliente.setRutinaIdrutina(rutinaRepository.getReferenceById(Integer.parseInt(rutinaSelect)));
        clienteRepository.save(cliente);


    }

    public Boolean existsByUsuarioIdusuario_Id(Usuario usuario){
        return clienteRepository.existsByUsuarioIdusuario_Id(usuario.getId());
    }

}
