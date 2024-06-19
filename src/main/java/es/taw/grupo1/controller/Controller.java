package es.taw.grupo1.controller;

import es.taw.grupo1.dao.*;
import es.taw.grupo1.entity.Cliente;
import es.taw.grupo1.entity.Ejercicio;
import es.taw.grupo1.entity.Entrenador;
import es.taw.grupo1.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    RutinaRepository rutinaRepository;

    @Autowired
    EntrenadorRepository entrenadorRepository;

    @Autowired
    EjercicioRepository ejercicioRepository;



    @GetMapping("/")
    public String index() {


        //INIT BDD

        /*

        Usuario usuario = new Usuario();

        usuario.setNombre("nombre");
        usuario.setContrasena("mdp");
        usuario.setSexo("M");
        usuario.setRangoEdad("1");
        usuario.setEmail("val@email.com");
        usuarioRepository.save(usuario);

        Usuario usuario2 = new Usuario();

        usuario2.setNombre("nombre2");
        usuario2.setContrasena("mdp");
        usuario2.setSexo("M");
        usuario2.setRangoEdad("1");
        usuario2.setEmail("val2@email.com");
        usuarioRepository.save(usuario2);


        Entrenador entrenador = new Entrenador();
        entrenador.setUsuarioIdusuario(usuario2);
        entrenador.setTipo("CROSS");
        entrenadorRepository.save(entrenador);

        Cliente cliente = new Cliente();
        cliente.setUsuarioIdusuario(usuario);
        cliente.setEntrenadorIdentrenador(entrenador);
        clienteRepository.save(cliente);

        System.out.println(usuario.getNombre());


        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setNombre("Ex1");
        ejercicio.setTipo("FUERZA");
        ejercicio.setCalorias(10);
        ejercicio.setDificultad("facil");
        ejercicio.setDescripcion("Description");
        ejercicio.setGrupoMuscular("grupo muscular");
        ejercicio.setUrlDemo("");
        ejercicioRepository.save(ejercicio);


         */


        return "prueba";
    }


    @GetMapping("/cross")
    public String cross(Model model) {


        model.addAttribute("rutinas", rutinaRepository.findAll());

        model.addAttribute("clientes", clienteRepository.findAll());



        return "crossTraining/CrossHome";

    }

    @GetMapping("/createRutina")
    public String createRutinaPage(Model model) {


        model.addAttribute("ejercicios", ejercicioRepository.findAll());



        return "crossTraining/RutinaPage";

    }

}
