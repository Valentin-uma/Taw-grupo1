package es.taw.grupo1.controller;

import es.taw.grupo1.dao.*;
import es.taw.grupo1.entity.*;
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

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private FeedbackRepository feedbackRepository;

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


            if(clienteRepository.existsByUsuarioIdusuario_Id(usuario.getId())){
                return "redirect:/miRutina";
            }
            else if(entrenadorRepository.existsByUsuarioIdusuario_Id(usuario.getId())){
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

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = null;
        try {
            fecha = formato.parse(formFecha.getFecha());
        } catch (Exception e) {
            return "redirect:/feedback";
        }

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        int dayOfWeek = calendario.get(Calendar.DAY_OF_WEEK);

        String dia;
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                dia = "L";
                break;
            case Calendar.TUESDAY:
                dia = "M";
                break;
            case Calendar.WEDNESDAY:
                dia = "X";
                break;
            case Calendar.THURSDAY:
                dia = "J";
                break;
            case Calendar.FRIDAY:
                dia = "V";
                break;
            case Calendar.SATURDAY:
                dia = "S";
                break;
            case Calendar.SUNDAY:
                dia = "D";
                break;
            default:
                dia = "";
        }


        // Vamos a buscar los ejercicios correspondientes para la fecha

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Cliente cliente = clienteRepository.findCienteByUsuario(usuario);
        Rutina rutina = cliente.getRutinaIdrutina();

        // Buscamos una sesion de la rutina y dia correspondientes

        Sesion sesion = sesionRepository.findByRutinaIdrutinaAndDia(rutina, dia);

        // Encontramos los ejercicios correspondientes a la sesion

        List<SesionHasEjercicio> sesionHasEjercicioList = sesionHasEjercicioRepository.findBySesionIdsesion(sesion);

        m.addAttribute("textoFecha", formFecha.getFecha());
        m.addAttribute("formFecha", new FormFecha());
        m.addAttribute("listaEjercicios", sesionHasEjercicioList);
        m.addAttribute("formSubmitFeedback", new FormSubmitFeedback());

        return "cliente/feedback";
    }

    @PostMapping("/submitFeedback")
    public String submitFeedback(@ModelAttribute("formSubmitFeedback") FormSubmitFeedback formSubmitFeedback, HttpSession session){

        Feedback feedback = new Feedback();
        feedback.setDescripcion(formSubmitFeedback.getComentarios());
        feedback.setRepeticiones(formSubmitFeedback.getRepeticionesRealizadas());
        feedback.setSeries(formSubmitFeedback.getSeriesRealizadas());
        feedback.setPeso(formSubmitFeedback.getPesoUtilizado());
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Cliente cliente = clienteRepository.findCienteByUsuario(usuario);
        Ejercicio ejercicio = ejercicioRepository.findById(formSubmitFeedback.getEjercicioId()).get();
        Sesion sesion = sesionRepository.findById(formSubmitFeedback.getSesionId()).get();
        feedback.setSesionHasEjercicio(sesionHasEjercicioRepository.findSesionHasEjercicioByEjercicioIdejercicioAndSesionIdsesion(ejercicio, sesion));

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = null;
        try {
            fecha = formato.parse(formSubmitFeedback.getFechaSubmit());
        } catch (ParseException e) {
            return "redirect:/feedback";
        }

        feedback.setFecha(fecha);
        feedback.setClienteIdcliente(cliente);
        feedbackRepository.save(feedback);


        return "redirect:/feedback";
    }

    @GetMapping("/historial")
    public String historial(HttpSession session, Model m) {

        if(session.getAttribute("usuario") == null){
            return "redirect:/login";
        }

        // Vamos a agrupar todos los feedbacks por dia en un HashMap y llevarlo al model
        List<Feedback> feedbackList = feedbackRepository.findAll();
        Map<String, List<Feedback>> feedbackMap = new HashMap<>();
        for(Feedback feedback : feedbackList){
            if(!feedbackMap.containsKey(feedback.getFecha().toString())){
                ArrayList<Feedback> feedbacks = new ArrayList<>();
                feedbackMap.put(feedback.getFecha().toString(), feedbacks);
                feedbacks.add(feedback);
            }else{
                feedbackMap.get(feedback.getFecha().toString()).add(feedback);
            }
        }

        m.addAttribute("feedbackMap", feedbackMap);



        return "cliente/historial";
    }

}
