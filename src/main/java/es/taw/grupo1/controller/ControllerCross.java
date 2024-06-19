package es.taw.grupo1.controller;

import es.taw.grupo1.entity.*;
import es.taw.grupo1.service.*;
import es.taw.grupo1.ui.EjercicioObject;
import es.taw.grupo1.ui.SessionObject;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

/*

AUTOR: Valentin Pecqueux

 */

@org.springframework.stereotype.Controller
public class ControllerCross {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    RutinaService rutinaService;

    @Autowired
    EntrenadorService entrenadorService;

    @Autowired
    EjercicioService ejercicioService;

    @Autowired
    SesionService sesionService;

    @Autowired
    SesionHasEjercicioService sesionHasEjercicioService;

    @Autowired
    FeedbackService feedbackService;


    @GetMapping("/")
    public String index(HttpSession session) {


        session.setAttribute("userId",usuarioService.getIdFromEmailAndPassword("Tibo@email.com","mdp3"));




        return "prueba";
    }

    @GetMapping("/initBdd")
    public String initBdd(HttpSession session) {

        usuarioService.initBdd();

        return "prueba";
    }

    @GetMapping("/cross")
    public RedirectView crossRedirect(){

        return new RedirectView("/cross/null");
    }

    @GetMapping("/cross/{filtro}")
    public String cross(Model model,HttpSession session,@PathVariable("filtro")String filtro) {

        session.setAttribute("userId",usuarioService.getIdFromEmailAndPassword("Tibo@email.com","mdp3"));


        int userId = (int) session.getAttribute("userId");

        List<Rutina> rutinas = rutinaService.findByEntrenadorIdentrenador(entrenadorService.getEntrenadorByIdUser(userId));


        //Find types of rutina

        List<String> alltypesRutina = new ArrayList<>();
        alltypesRutina = rutinaService.findAllTypesOfRutinasOfTrainer(userId);

        if(!filtro.equals("null")) {
            rutinas = rutinaService.filtrarRutinaByType(filtro);
        }

        model.addAttribute("typesRutinas",alltypesRutina);
        model.addAttribute("rutinas", rutinas);

        model.addAttribute("clientes", clienteService.findByEntrenadorIdentrenador(entrenadorService.getEntrenadorByIdUser(userId)));

        return "crossTraining/CrossHome";

    }

    @GetMapping("/createRutina")
    public String createRutinaPage(HttpSession session, Model model) {

        List<SessionObject> sessions = (List<SessionObject>) session.getAttribute("sessions");
        if (sessions == null) {

            sessions = rutinaService.createNewSessionObject();

            session.setAttribute("sessions", sessions);
        }

        model.addAttribute("ejercicios", ejercicioService.findAll());
        model.addAttribute("ejercicioObject", new EjercicioObject());
        model.addAttribute("ejercicioObjectForm", new EjercicioObject());

        model.addAttribute("edit", "false");
        model.addAttribute("rutinaName", "null");
        model.addAttribute("rutinaId", "Id");



        return "crossTraining/RutinaPage";

    }

    @PostMapping("/saveEjercicioToSession/{edit}")
    public RedirectView saveEjercicioToSession(HttpSession session, @ModelAttribute("ejercicioObject") EjercicioObject ejercicioObject, Model model,@PathVariable(value="edit") String edit) {
        List<SessionObject> sessions = (List<SessionObject>) session.getAttribute("sessions");


        for (SessionObject sessionObject : sessions) {
            for(EjercicioObject ejercicio : sessionObject.getEjercicios()) {
            }
        }

        if (sessions == null) {
            sessions = new ArrayList<>();
        }
        for (SessionObject sessionObject : sessions) {
            if (sessionObject.getDia().equals(ejercicioObject.getDia())) {

                List<EjercicioObject> ejerciciosFiltres = new ArrayList<>(sessionObject.getEjercicios().stream() //Quitar el ejercicio sin data y poner el ejercicio llenado
                        .filter(ejercicio -> !Objects.equals(ejercicio.getIdSessionEx(), ejercicioObject.getIdSessionEx()))
                        .toList());

                ejerciciosFiltres.add(ejercicioObject);
                sessionObject.setEjercicios(ejerciciosFiltres);

            }
        }
        session.setAttribute("sessions", sessions);

        if(!edit.equals("null")){
            return new RedirectView("/editRutina/"+edit);
        }
        else{
            return new RedirectView("/createRutina");

        }
    }

    @GetMapping("/createEjercicioToSession/{edit}")
    public String createEjercicioToSession(Model model,HttpSession session,@RequestParam("dia") String dia,@PathVariable(value="edit") String edit) {

        System.out.println("INSIDE");
        List<SessionObject> sessions = (List<SessionObject>) session.getAttribute("sessions");
        if (sessions == null) {
            sessions = new ArrayList<>();
        }
        String randomId = "";
        for (SessionObject sessionObject : sessions) {
            if (sessionObject.getDia().equals(dia)) {
                List<EjercicioObject> ejercicioObjects2 = sessionObject.getEjercicios();
                randomId = UUID.randomUUID().toString();
                ejercicioObjects2.add(new EjercicioObject(randomId));
                sessionObject.setEjercicios(ejercicioObjects2);
            }
        }
        session.setAttribute("sessions", sessions);



        model.addAttribute("ejercicios", ejercicioService.findAll()); //ex statics
        model.addAttribute("ejercicioObject", new EjercicioObject());


        if(!edit.equals("null")){
            Rutina rutina = rutinaService.getReferenceById(Integer.parseInt(edit));

            model.addAttribute("edit", "true");
            model.addAttribute("rutinaName",rutina.getNombre());
            model.addAttribute("rutinaDesc",rutina.getDescripcion());
            model.addAttribute("rutinaId", edit);
        }
        else {
            model.addAttribute("edit", "false");
            model.addAttribute("rutinaName", "null");
            model.addAttribute("rutinaId", "Id");
        }
        return "crossTraining/RutinaPage";



    }

    @PostMapping("/submitRutina")
    public RedirectView submitRutina(Model model, HttpSession session, @RequestParam("routineName") String routineName,@RequestParam("routineDesc") String routineDesc) {

        List<SessionObject> sessions = (List<SessionObject>) session.getAttribute("sessions");


        int userId = (int) session.getAttribute("userId");

        Rutina rutina = rutinaService.GuardarRutina(userId,routineName,routineDesc,sessions);

        //Find types of rutina
        String typeFinal = rutinaService.findRutinaType(rutina);

            rutina.setTipo(typeFinal);
            rutinaService.save(rutina);


        session.setAttribute("sessions", null);

        return new RedirectView("/cross");
    }

    @GetMapping("/editRutina/{id}")
    public String editRutina(Model model,@PathVariable(value="id") String id,HttpSession session){

        List<SessionObject> sessions = (List<SessionObject>) session.getAttribute("sessions");
        Rutina rutina = rutinaService.getReferenceById(Integer.parseInt(id));
        if (sessions == null) {

            sessions = rutinaService.llenarSessionObjectsForEdit(id);

            session.setAttribute("sessions", sessions);
        }


        model.addAttribute("ejercicios", ejercicioService.findAll());
        model.addAttribute("ejercicioObject", new EjercicioObject());
        model.addAttribute("ejercicioObjectForm", new EjercicioObject());

        model.addAttribute("edit", "true");
        model.addAttribute("rutinaName", rutina.getNombre());
        model.addAttribute("rutinaDesc",rutina.getDescripcion());
        model.addAttribute("rutinaId", id);



        return "crossTraining/RutinaPage";

    }

    @PostMapping("/modifyRutina")
    public RedirectView modifyRutina(Model model, HttpSession session, @RequestParam("routineName") String routineName,@RequestParam("routineDesc") String routineDesc,@RequestParam("rutinaId") String rutinaId) {


        int rutinaIdInt = Integer.parseInt(rutinaId);
        //ADD RUTINA
        List<SessionObject> sessions = (List<SessionObject>) session.getAttribute("sessions");
        String dia = "L";


        int userId = (int) session.getAttribute("userId");
        Rutina rutina = rutinaService.GuardarRutina(userId,routineName,routineDesc,sessions);

        String typeFinal = rutinaService.findRutinaType(rutina);

        rutina.setTipo(typeFinal);
        rutinaService.save(rutina);

        session.setAttribute("sessions", null);



        //DELETE OLD RUTINA
        final String uri = "http://localhost:8080/rutina/" + rutinaIdInt;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri);


        return new RedirectView("/cross");
    }


    @DeleteMapping("/rutina/{id}")
    public RedirectView deleteRutina(Model model,@PathVariable(value="id") String id){

        rutinaService.deleteRutinaAndAssociations(id);

        return new RedirectView("/cross");
    }



    @GetMapping("/manageClient/{id}")
    public String manageClientes(Model model,HttpSession session,@PathVariable(value = "id")String id){

        model.addAttribute("rutinas", rutinaService.findAll());
        model.addAttribute("cliente", clienteService.getReferenceById(Integer.parseInt(id)));
        model.addAttribute("feedbacks",feedbackService.findByClienteIdclienteId(Integer.parseInt(id)));

        return "crossTraining/ManageClientHome";
    }

    @PostMapping("/changeRutinaAsinada")
    public RedirectView changeRutinaAsinada(Model model, HttpSession session, @RequestParam("clienteId") String clienteId,@RequestParam("rutinaSelect") String rutinaSelect ){

        clienteService.changeClientRutina(clienteId,rutinaSelect);
        return new RedirectView("/manageClient/"+clienteId);
    }

    @GetMapping("/testcreateFeedback/{idRutina}/{idCliente}")
    public RedirectView testcreateFeedback(Model model, HttpSession session, @PathVariable("idCliente") String idCliente,@PathVariable("idRutina") String idRutina ){

        feedbackService.createTestFeedbackForRutina(idRutina,idCliente);
        return new RedirectView("/manageClient/"+idCliente);
    }

    @PostMapping("/crossFiltrar")
    public RedirectView crossFiltrar(Model model,HttpSession session,@RequestParam("filtro") String filtro){

        return new RedirectView("/cross/"+filtro);
    }

}

