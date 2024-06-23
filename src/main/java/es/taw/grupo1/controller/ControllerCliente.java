package es.taw.grupo1.controller;

import es.taw.grupo1.dao.*;
import es.taw.grupo1.entity.*;
import es.taw.grupo1.service.*;
import es.taw.grupo1.ui.FiltroHistorial;
import es.taw.grupo1.ui.FormFecha;
import es.taw.grupo1.ui.FormLogin;
import es.taw.grupo1.ui.FormSubmitFeedback;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*

AUTOR: Rubén Ipiña Rivas    100%

 */

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
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private SesionHasEjercicioService sesionHasEjercicioService;
    @Autowired
    private RutinaService rutinaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EntrenadorService entrenadorService;

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
            id = usuarioService.getIdFromEmailAndPassword(formLogin.getEmail(), formLogin.getContrasena());
        }catch(Exception e){
            return "redirect:/errorLogin";
        }

        // Una vez tenemos la id del usuario, buscamos la entidad y la ponemos en un atributo de la sesión
        Usuario usuario = usuarioService.findUsuarioById(id);
        session.setAttribute("usuario", usuario);


            if(clienteService.existsByUsuarioIdusuario_Id(usuario)){
                return "redirect:/miRutina";
            }
            else if(entrenadorService.existsByUsuarioIdusuario_Id(usuario)){
                return "redirect:/cross";
            }
            else{
                return "redirect:/errorLogin";
            }

    }

    @GetMapping("/miRutina")
    public String miRutina(HttpSession session, Model m) {

        if(session.getAttribute("usuario") == null){
            return "redirect:/login";
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Rutina rutina = rutinaService.getRutina(usuario);

        if(rutina == null){
            return "cliente/noRutina";
        }

        m.addAttribute("rutina", rutina);

        Map<String, List<SesionHasEjercicio>> sesionHasEjerciciosMap = sesionHasEjercicioService.getSesionHasEjerciciosMap(rutina);

        m.addAttribute("usuario", usuario);
        m.addAttribute("sesionHasEjerciciosMap", sesionHasEjerciciosMap);

        return "cliente/miRutina";
    }

    @GetMapping("/feedback")
    public String feedback(HttpSession session, Model m) {

        if(session.getAttribute("usuario") == null){
            return "redirect:/login";
        }

        m.addAttribute("formFecha", new FormFecha());
        m.addAttribute("listaEjercicios", null);
        m.addAttribute("formSubmitFeedback", new FormSubmitFeedback());

        return "cliente/feedback";
    }

    @PostMapping("/filtrarPorFecha")
    public String filtrarPorFecha(HttpSession session, Model m, @ModelAttribute("formFecha") FormFecha formFecha) {

        if(session.getAttribute("usuario") == null){
            return "redirect:/login";
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<SesionHasEjercicio> sesionHasEjercicioList = sesionHasEjercicioService.listaSesionHasEjercicio(formFecha, usuario);

        if(sesionHasEjercicioList == null){
            return "redirect:/feedback";
        }

        m.addAttribute("textoFecha", formFecha.getFecha());
        m.addAttribute("formFecha", new FormFecha());
        m.addAttribute("listaEjercicios", sesionHasEjercicioList);
        m.addAttribute("formSubmitFeedback", new FormSubmitFeedback());

        return "cliente/feedback";
    }

    @PostMapping("/submitFeedback")
    public String submitFeedback(@ModelAttribute("formSubmitFeedback") FormSubmitFeedback formSubmitFeedback, HttpSession session){

        if(session.getAttribute("usuario") == null){
            return "redirect:/login";
        }

        feedbackService.submitFeedback(formSubmitFeedback, (Usuario) session.getAttribute("usuario"));

        return "redirect:/feedbackAnadidoCorrectamente";
    }

    @GetMapping("/historial")
    public String historial(HttpSession session, Model m) {

        if(session.getAttribute("usuario") == null){
            return "redirect:/login";
        }

        Map<String, List<Feedback>> feedbackMap = feedbackService.getFeedbackMap();

        if(feedbackMap == null){
            return "redirect:/feedback";
        }

        m.addAttribute("feedbackMap", feedbackMap);
        m.addAttribute("filtroHistorial", new FiltroHistorial());

        return "cliente/historial";
    }

    @PostMapping("/filtrarHistorial")
    public String filtrarHistorial(HttpSession session, Model m, @ModelAttribute("filtroHistorial") FiltroHistorial filtroHistorial) {

        if(session.getAttribute("usuario") == null){
            return "redirect:/login";
        }

        Map<String, List<Feedback>> feedbackMap = feedbackService.getFeedbackMap(filtroHistorial);

        if(feedbackMap == null){
            return "redirect:/filtroErroneo";
        }

        m.addAttribute("feedbackMap", feedbackMap);
        m.addAttribute("filtroHistorial", new FiltroHistorial());
        m.addAttribute("fechaMin", filtroHistorial.getFechaMin());
        m.addAttribute("fechaMax", filtroHistorial.getFechaMax());

        return "cliente/historial";
    }

    @GetMapping("/eliminarFeedback")
    public String eliminarFeedback(HttpSession session, @RequestParam("feedbackId") Integer feedbackId) {

        if(session.getAttribute("usuario") == null){
            return "redirect:/login";
        }

        feedbackService.eliminarFeedbackById(feedbackId);

        return "redirect:/historial";
    }

    @GetMapping("/filtroErroneo")
    public String filtroErroneo(HttpSession session) {

        if(session.getAttribute("usuario") == null){
            return "redirect:/login";
        }

        return "cliente/filtroErroneo";

    }

    @GetMapping("/feedbackAnadidoCorrectamente")
    public String feedbackAnadidoCorrectamente(HttpSession session, Model m) {

        if(session.getAttribute("usuario") == null){
            return "redirect:/login";
        }

        return "cliente/feedbackAnadidoCorrectamente";

    }

}
