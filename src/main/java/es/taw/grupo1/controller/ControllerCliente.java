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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        if(session.getAttribute("usuario") == null){
            return "redirect:/login";
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Cliente cliente = clienteRepository.findCienteByUsuario(usuario);
        Rutina rutina = cliente.getRutinaIdrutina();

        if(rutina == null){
            return "cliente/noRutina";
        }

        m.addAttribute("rutina", rutina);
        List<Sesion> sesiones = sesionRepository.findByRutinaIdrutina(rutina);
        Map<String, List<SesionHasEjercicio>> sesionHasEjerciciosMap = new HashMap();

        for(Sesion sesion: sesiones){
            sesionHasEjerciciosMap.put(sesion.getDia(), sesionHasEjercicioRepository.findBySesionIdsesion(sesion));
        }

        m.addAttribute("usuario", usuario);
        m.addAttribute("sesionHasEjerciciosMap", sesionHasEjerciciosMap);

        return "cliente/miRutina";
    }

}
