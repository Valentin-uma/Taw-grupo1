package es.taw.grupo1.service;

import es.taw.grupo1.dao.*;
import es.taw.grupo1.dto.RutinaDTO;
import es.taw.grupo1.entity.*;
import es.taw.grupo1.ui.EjercicioObject;
import es.taw.grupo1.ui.SessionObject;
import jakarta.transaction.Transactional;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*

AUTOR: Valentin Pecqueux    90%
AUTOR: Ruben Ipiña          10%

 */
@Service
public class RutinaService extends DTOService<RutinaDTO, Rutina>{

    @Autowired
    RutinaRepository rutinaRepository;

    @Autowired
    EntrenadorRepository entrenadorRepository;

    @Autowired
    SesionRepository sesionRepository;

    @Autowired
    SesionHasEjercicioRepository sesionHasEjercicioRepository;

    @Autowired
    EjercicioRepository ejercicioRepository;

    @Autowired
    ClienteRepository clienteRepository;

    public List<Rutina> findByEntrenadorIdentrenador(Entrenador userId){
        return rutinaRepository.findByEntrenadorIdentrenador(userId);
    }

    public List<Rutina> filtrarRutinaByType(String filtro){
        return rutinaRepository.filtrarRutinaByType(filtro);
    }

    public Rutina getReferenceById(int id){
        return rutinaRepository.getReferenceById(id);
    }

    public List<SessionObject> createNewSessionObject(){
        ArrayList<SessionObject> sessions;
        sessions = new ArrayList<>();
        List<EjercicioObject> ejerciciosLunes = new ArrayList<>();
        List<EjercicioObject> ejerciciosMartes = new ArrayList<>();
        List<EjercicioObject> ejerciciosMiercoles = new ArrayList<>();
        List<EjercicioObject> ejerciciosJueves = new ArrayList<>();
        List<EjercicioObject> ejerciciosViernes = new ArrayList<>();
        List<EjercicioObject> ejerciciosSabado = new ArrayList<>();
        List<EjercicioObject> ejerciciosDomingo = new ArrayList<>();

        sessions.add(new SessionObject("Lunes",ejerciciosLunes));
        sessions.add(new SessionObject("Martes",ejerciciosMartes));
        sessions.add(new SessionObject("Miercoles",ejerciciosMiercoles));
        sessions.add(new SessionObject("Jueves",ejerciciosJueves));
        sessions.add(new SessionObject("Viernes",ejerciciosViernes));
        sessions.add(new SessionObject("Sabado",ejerciciosSabado));
        sessions.add(new SessionObject("Domingo",ejerciciosDomingo));

        return sessions;
    }

    public List<String> findAllTypesOfRutinasOfTrainer(int userId){

        List<Rutina> rutinas = rutinaRepository.findByEntrenadorIdentrenador(entrenadorRepository.getEntrenadorByIdUser(userId));


        List<String> alltypesRutina = new ArrayList<>();
        for(Rutina rutina : rutinas) {
            List<String> typesEjercicios = new ArrayList<>();
            List<Sesion> sesions = sesionRepository.findByRutinaIdrutina(rutina);
            for (Sesion sesion : sesions) {
                List<SesionHasEjercicio> sesionHasEjercicios = sesionHasEjercicioRepository.findBySesionIdsesion(sesion);
                for (SesionHasEjercicio sesionHasEjercicio : sesionHasEjercicios) {
                    String ejType = sesionHasEjercicio.getEjercicioIdejercicio().getTipo();
                    if (!typesEjercicios.contains(ejType)) {
                        typesEjercicios.add(ejType);
                    }
                }
            }

            String typeFinal = typesEjercicios.get(0);
            for (String type : typesEjercicios) {
                if (!type.equals(typeFinal)) {
                    typeFinal = "CROSS";
                }
            }

            if(!alltypesRutina.contains(typeFinal)){
                alltypesRutina.add(typeFinal);
            }
        }

        return  alltypesRutina;
    }

    public Rutina GuardarRutina(int userId, String routineName, String routineDesc, List<SessionObject> sessions){

        String dia = "L";
        Rutina rutina = new Rutina();
        rutina.setEntrenadorIdentrenador(entrenadorRepository.getEntrenadorByIdUser(userId));
        rutina.setNombre(routineName);
        rutina.setDescripcion(routineDesc);

        rutinaRepository.save(rutina);


        for (SessionObject sessionObject : sessions){
            dia = transformDia(sessionObject.getDia());
            System.out.println(sessionObject.getDia());
            Sesion sesion = new Sesion();
            sesion.setDia(dia);
            sesion.setRutinaIdrutina(rutina);


            sesionRepository.save(sesion);


            List<EjercicioObject> ejercicioObjects = sessionObject.getEjercicios();
            for (EjercicioObject ejercicioObject : ejercicioObjects){
                SesionHasEjercicio sesionHasEjercicio = new SesionHasEjercicio();
                Ejercicio ejercicioSelect = ejercicioRepository.getReferenceById(ejercicioObject.getIdejercicio());
                System.out.println(ejercicioSelect.getDescripcion());


                sesionHasEjercicio.setEjercicioIdejercicio(ejercicioSelect);
                sesionHasEjercicio.setSesionIdsesion(sesion);
                sesionHasEjercicio.setSeries(ejercicioObject.getNumeroSeries());
                sesionHasEjercicio.setPeso(ejercicioObject.getPeso());
                sesionHasEjercicio.setRepeticiones(ejercicioObject.getNumeroRepeticion());

                SesionHasEjercicioId sesionHasEjercicioId = new SesionHasEjercicioId();
                sesionHasEjercicioId.setEjercicioIdejercicio(ejercicioSelect.getId());
                sesionHasEjercicioId.setSesionIdsesion(sesion.getId());

                sesionHasEjercicio.setId(sesionHasEjercicioId);



                sesionHasEjercicioRepository.save(sesionHasEjercicio);


                System.out.println(ejercicioObject.getPeso());

            }
        }

        return rutina;

    }

    public String findRutinaType(Rutina rutina){
        List<String> typesEjercicios = new ArrayList<>();
        List<Sesion> sesions = sesionRepository.findByRutinaIdrutina(rutina);
        for (Sesion sesion : sesions){
            List<SesionHasEjercicio> sesionHasEjercicios = sesionHasEjercicioRepository.findBySesionIdsesion(sesion);
            for (SesionHasEjercicio sesionHasEjercicio : sesionHasEjercicios){
                String ejType = sesionHasEjercicio.getEjercicioIdejercicio().getTipo();
                if(!typesEjercicios.contains(ejType)){
                    typesEjercicios.add(ejType);
                }
            }
        }

        String typeFinal = typesEjercicios.get(0);
        for (String type : typesEjercicios){
            if(!type.equals(typeFinal)){
                typeFinal = "CROSS";
            }
        }

        return  typeFinal;
    }

    public void save(Rutina rutina){
        rutinaRepository.save(rutina);
    }

    public String transformDia(String dia) {
        return switch (dia) {
            case "Lunes" -> "L";
            case "Martes" -> "M";
            case "Miercoles" -> "X";
            case "Jueves" -> "J";
            case "Viernes" -> "V";
            case "Sabado" -> "S";
            case "Domingo" -> "D";
            default -> "L";
        };
    }


    public List<SessionObject> llenarSessionObjectsForEdit(String id){

        Rutina rutina = rutinaRepository.getReferenceById(Integer.parseInt(id));
        List<SessionObject> sessions;
        sessions = new ArrayList<>();


        List<EjercicioObject> ejerciciosLunes = new ArrayList<>();
        List<EjercicioObject> ejerciciosMartes = new ArrayList<>();
        List<EjercicioObject> ejerciciosMiercoles = new ArrayList<>();
        List<EjercicioObject> ejerciciosJueves = new ArrayList<>();
        List<EjercicioObject> ejerciciosViernes = new ArrayList<>();
        List<EjercicioObject> ejerciciosSabado = new ArrayList<>();
        List<EjercicioObject> ejerciciosDomingo = new ArrayList<>();




        // Get all Sesion entities associated with the Rutina
        List<Sesion> sesiones = sesionRepository.findByRutinaIdrutina(rutina);


        for (Sesion sesion : sesiones) {
            List<SesionHasEjercicio> ejercicios = sesionHasEjercicioRepository.findBySesionIdsesion(sesion);
            for (SesionHasEjercicio sesionHasEjercicio : ejercicios) {
                switch (sesion.getDia()) {
                    case "L":
                        ejerciciosLunes.add(new EjercicioObject(sesionHasEjercicio.getEjercicioIdejercicio().getId(), sesionHasEjercicio.getRepeticiones(), sesionHasEjercicio.getPeso(),sesionHasEjercicio.getSeries(), sesion.getDia(), UUID.randomUUID().toString()));
                        break;

                    case "M":
                        ejerciciosMartes.add(new EjercicioObject(sesionHasEjercicio.getEjercicioIdejercicio().getId(), sesionHasEjercicio.getRepeticiones(), sesionHasEjercicio.getPeso(),sesionHasEjercicio.getSeries(), sesion.getDia(), UUID.randomUUID().toString()));

                        break;

                    case "X":
                        ejerciciosMiercoles.add(new EjercicioObject(sesionHasEjercicio.getEjercicioIdejercicio().getId(), sesionHasEjercicio.getRepeticiones(), sesionHasEjercicio.getPeso(),sesionHasEjercicio.getSeries(), sesion.getDia(), UUID.randomUUID().toString()));

                        break;
                    case "J":
                        ejerciciosJueves.add(new EjercicioObject(sesionHasEjercicio.getEjercicioIdejercicio().getId(), sesionHasEjercicio.getRepeticiones(), sesionHasEjercicio.getPeso(),sesionHasEjercicio.getSeries(), sesion.getDia(), UUID.randomUUID().toString()));

                        break;

                    case "V":
                        ejerciciosViernes.add(new EjercicioObject(sesionHasEjercicio.getEjercicioIdejercicio().getId(), sesionHasEjercicio.getRepeticiones(), sesionHasEjercicio.getPeso(),sesionHasEjercicio.getSeries(), sesion.getDia(), UUID.randomUUID().toString()));

                        break;
                    case "S":
                        ejerciciosSabado.add(new EjercicioObject(sesionHasEjercicio.getEjercicioIdejercicio().getId(), sesionHasEjercicio.getRepeticiones(), sesionHasEjercicio.getPeso(),sesionHasEjercicio.getSeries(), sesion.getDia(), UUID.randomUUID().toString()));

                        break;
                    case "D":
                        ejerciciosDomingo.add(new EjercicioObject(sesionHasEjercicio.getEjercicioIdejercicio().getId(), sesionHasEjercicio.getRepeticiones(), sesionHasEjercicio.getPeso(),sesionHasEjercicio.getSeries(), sesion.getDia(), UUID.randomUUID().toString()));

                        break;
                }
            }


        }
        sessions.add(new SessionObject("Lunes", ejerciciosLunes));
        sessions.add(new SessionObject("Martes", ejerciciosMartes));
        sessions.add(new SessionObject("Miercoles", ejerciciosMiercoles));
        sessions.add(new SessionObject("Jueves", ejerciciosJueves));
        sessions.add(new SessionObject("Viernes", ejerciciosViernes));
        sessions.add(new SessionObject("Sabado", ejerciciosSabado));
        sessions.add(new SessionObject("Domingo", ejerciciosDomingo));

        return sessions;
    }

    @Transactional
    public void deleteRutinaAndAssociations(String id){
        System.out.println(1);

        Rutina rutina = rutinaRepository.getReferenceById(Integer.parseInt(id));
        System.out.println(1);
        // Get all Sesion entities associated with the Rutina
        List<Sesion> sesiones = sesionRepository.findByRutinaIdrutina(rutina);
        System.out.println(2);

        // Loop through each Sesion and delete all SesionHasEjercicio associated with it
        for (Sesion sesion : sesiones) {
            sesionHasEjercicioRepository.deleteBySesionIdsesion(sesion);
        }
        System.out.println(3);

        // Now delete all Sesion entities associated with the Rutina
        sesionRepository.deleteAll(sesiones);
        System.out.println(4);

        // Finally, delete the Rutina entity itself
        rutinaRepository.delete(rutina);
        System.out.println(5);
    }

    public List<Rutina> findAll(){
        return rutinaRepository.findAll();
    }


    public Rutina getRutina(Usuario usuario){
        Cliente cliente = clienteRepository.findCienteByUsuario(usuario);
        if(cliente == null){
            return null;
        }
        return cliente.getRutinaIdrutina();
    }

}
