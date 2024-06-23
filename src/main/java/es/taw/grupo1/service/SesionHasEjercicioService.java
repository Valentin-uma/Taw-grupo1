package es.taw.grupo1.service;

import es.taw.grupo1.dao.*;
import es.taw.grupo1.dto.SesionHasEjercicioDTO;
import es.taw.grupo1.entity.*;
import es.taw.grupo1.ui.FormFecha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;



@Service
public class SesionHasEjercicioService extends DTOService<SesionHasEjercicioDTO, SesionHasEjercicio> {

    @Autowired
    RutinaRepository rutinaRepository;

    @Autowired
    EntrenadorRepository entrenadorRepository;

    @Autowired
    SesionRepository sesionRepository;

    @Autowired
    SesionHasEjercicioRepository sesionHasEjercicioRepository;

    @Autowired
    ClienteRepository clienteRepository;

    public List<SesionHasEjercicio> listaSesionHasEjercicio(FormFecha formFecha, Usuario usuario){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = formato.parse(formFecha.getFecha());
        } catch (Exception e) {
            return null;
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

        Cliente cliente = clienteRepository.findCienteByUsuario(usuario);
        Rutina rutina = cliente.getRutinaIdrutina();
        Sesion sesion = sesionRepository.findByRutinaIdrutinaAndDia(rutina, dia);

        return sesionHasEjercicioRepository.findBySesionIdsesion(sesion);
    }

    public Map<String, List<SesionHasEjercicio>> getSesionHasEjerciciosMap(Rutina rutina){
        List<Sesion> sesiones = sesionRepository.findByRutinaIdrutina(rutina);
        Map<String, List<SesionHasEjercicio>> sesionHasEjerciciosMap = new HashMap();

        for(Sesion sesion: sesiones){
            sesionHasEjerciciosMap.put(sesion.getDia(), sesionHasEjercicioRepository.findBySesionIdsesion(sesion));
        }

        return sesionHasEjerciciosMap;
    }


}
