package es.taw.grupo1.controller;

import es.taw.grupo1.dao.*;
import es.taw.grupo1.entity.*;
import es.taw.grupo1.ui.FormLogin;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/*
* Autor: Rubén Ipiña Rivas
* */

@Controller
public class ControllerCliente {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EntrenadorRepository entrenadorRepository;
    @Autowired
    private RutinaRepository rutinaRepository;
    @Autowired
    private SesionRepository sesionRepository;
    @Autowired
    private EjercicioRepository ejercicioRepository;
    @Autowired
    private SesionHasEjercicioRepository sesionHasEjercicioRepository;

    @GetMapping("/")
    public String index(HttpSession session) {
        // Si ya estamos logeados, entramos directamente a /miRutina
        if(session.getAttribute("usuario") != null){
            return "redirect:/miRutina";
        }
        return "cliente/index";
    }

    @GetMapping("/login")
    public String loginForm(Model m) {
        m.addAttribute("formLogin", new FormLogin());
        return "cliente/login";
    }

    @GetMapping("/salir")
    public String salir(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/errorLogin")
    public String errorLogin() {
        return "cliente/errorLogin";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("formLogin") FormLogin formLogin, HttpSession session){

        // Tenemos que asegurarnos de que existe un usuario con esa contraseña
        Integer id;
        try{
            id = usuarioRepository.getIdFromEmailAndPassword(formLogin.getEmail(), formLogin.getContrasena());
        }catch(Exception e){
            return "redirect:/errorLogin";
        }

        // Una vez tenemos la id del usuario, buscamos la entidad y la ponemos en un atributo de la sesión
        Usuario usuario = usuarioRepository.findById(id).get();
        session.setAttribute("usuario", usuario);

        return "redirect:/miRutina";
    }



    @GetMapping("/miRutina")
    public String miRutina(HttpSession session, Model m) {

        // Primero, garantizamos que el usuario se encuentra logeado
        if(session.getAttribute("usuario") == null){
            return "redirect:/login";
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Vamos a buscar la entidad cliente a la que está asociada el usuario

        Cliente c = clienteRepository.findCienteByUsuario(usuario);

        // Con la entidad cliente, vamos a recuperar la rutina
        Rutina r = c.getRutinaIdrutina();

        // Si aún no hay una rutina asociada, vamos a la página noRutina
        if(r == null){
            return "cliente/noRutina";
        }

        // En cambio, si hay una rutina, se la pasaremos al model.      Deberíamos añadirle un campo nombre a la rutina

        m.addAttribute("rutina", r);

        // Buscamos las sesiones de la rutina y las añadimos también al modelo

        List<Sesion> sesiones = sesionRepository.findByRutinaIdrutina(r);

        // Recopilamos los ejercicioHasSesion de cada sesión y se los pasamos al modelo

        List<SesionHasEjercicio> sesionHasEjerciciosList = new ArrayList<SesionHasEjercicio>();

        for(Sesion sesion: sesiones){
            sesionHasEjerciciosList.addAll(sesionHasEjercicioRepository.findBySesionIdsesion(sesion));
        }

        // Pasamos sesionHasEjerciciosList al modelo
        m.addAttribute("sesionHasEjerciciosList", sesionHasEjerciciosList);

        return "cliente/miRutina";
    }

}
