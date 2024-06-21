package es.taw.grupo1.service;

import es.taw.grupo1.dao.*;
import es.taw.grupo1.dto.UsuarioDTO;
import es.taw.grupo1.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*

AUTOR: Valentin Pecqueux

 */
@Service
public class UsuarioService extends DTOService<UsuarioDTO, Usuario> {

    @Autowired
    RutinaRepository rutinaRepository;

    @Autowired
    EntrenadorRepository entrenadorRepository;

    @Autowired
    SesionRepository sesionRepository;

    @Autowired
    SesionHasEjercicioRepository sesionHasEjercicioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    EjercicioRepository ejercicioRepository;

    @Autowired
    ClienteRepository clienteRepository;

    public int getIdFromEmailAndPassword(String email,String password){
        return usuarioRepository.getIdFromEmailAndPassword(email,password);
    }

    public void initBdd(){

        Usuario usuario1 = new Usuario();
        usuario1.setNombre("Valentin");
        usuario1.setContrasena("mdp");
        usuario1.setSexo("M");
        usuario1.setRangoEdad("2");
        usuario1.setEmail("val@email.com");
        usuarioRepository.save(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Ruben");
        usuario2.setContrasena("mdp2");
        usuario2.setSexo("M");
        usuario2.setRangoEdad("2");
        usuario2.setEmail("Ruben@email.com");
        usuarioRepository.save(usuario2);

        Usuario usuario3 = new Usuario();
        usuario3.setNombre("Tibo");
        usuario3.setContrasena("mdp3");
        usuario3.setSexo("M");
        usuario3.setRangoEdad("3");
        usuario3.setEmail("Tibo@email.com");
        usuarioRepository.save(usuario3);

        Usuario usuario4 = new Usuario();
        usuario4.setNombre("Laura");
        usuario4.setContrasena("mdp4");
        usuario4.setSexo("F");
        usuario4.setRangoEdad("3");
        usuario4.setEmail("laura@email.com");
        usuarioRepository.save(usuario4);

        Usuario usuario5 = new Usuario();
        usuario5.setNombre("Ana");
        usuario5.setContrasena("mdp5");
        usuario5.setSexo("F");
        usuario5.setRangoEdad("1");
        usuario5.setEmail("ana@email.com");
        usuarioRepository.save(usuario5);

        Usuario usuario6 = new Usuario();
        usuario6.setNombre("Carlos");
        usuario6.setContrasena("mdp6");
        usuario6.setSexo("M");
        usuario6.setRangoEdad("4");
        usuario6.setEmail("carlos@email.com");
        usuarioRepository.save(usuario6);

        Usuario usuario7 = new Usuario();
        usuario7.setNombre("Maria");
        usuario7.setContrasena("mdp7");
        usuario7.setSexo("F");
        usuario7.setRangoEdad("2");
        usuario7.setEmail("maria@email.com");
        usuarioRepository.save(usuario7);

        Usuario usuario8 = new Usuario();
        usuario8.setNombre("Jorge");
        usuario8.setContrasena("mdp8");
        usuario8.setSexo("M");
        usuario8.setRangoEdad("1");
        usuario8.setEmail("jorge@email.com");
        usuarioRepository.save(usuario8);

        Usuario usuario9 = new Usuario();
        usuario9.setNombre("Elena");
        usuario9.setContrasena("mdp9");
        usuario9.setSexo("F");
        usuario9.setRangoEdad("4");
        usuario9.setEmail("elena@email.com");
        usuarioRepository.save(usuario9);

        Usuario usuario10 = new Usuario();
        usuario10.setNombre("Sofia");
        usuario10.setContrasena("mdp10");
        usuario10.setSexo("F");
        usuario10.setRangoEdad("3");
        usuario10.setEmail("sofia@email.com");
        usuarioRepository.save(usuario10);



        Entrenador entrenador = new Entrenador();
        entrenador.setUsuarioIdusuario(usuario3);
        entrenador.setTipo("CROSS");
        entrenadorRepository.save(entrenador);

        Entrenador entrenador2 = new Entrenador();
        entrenador2.setUsuarioIdusuario(usuario4);
        entrenador2.setTipo("FUERZA");
        entrenadorRepository.save(entrenador2);

        Cliente cliente = new Cliente();
        cliente.setUsuarioIdusuario(usuario1);
        cliente.setEntrenadorIdentrenador(entrenador);
        clienteRepository.save(cliente);

        Cliente cliente2 = new Cliente();
        cliente2.setUsuarioIdusuario(usuario2);
        cliente2.setEntrenadorIdentrenador(entrenador);
        clienteRepository.save(cliente2);

        Cliente cliente3 = new Cliente();
        cliente3.setUsuarioIdusuario(usuario5);
        cliente3.setEntrenadorIdentrenador(entrenador);
        clienteRepository.save(cliente3);

        Cliente cliente4 = new Cliente();
        cliente4.setUsuarioIdusuario(usuario6);
        cliente4.setEntrenadorIdentrenador(entrenador);
        clienteRepository.save(cliente4);

        Cliente cliente5 = new Cliente();
        cliente5.setUsuarioIdusuario(usuario7);
        cliente5.setEntrenadorIdentrenador(entrenador2);
        clienteRepository.save(cliente5);

        Cliente cliente6 = new Cliente();
        cliente6.setUsuarioIdusuario(usuario8);
        cliente6.setEntrenadorIdentrenador(entrenador2);
        clienteRepository.save(cliente6);

        Ejercicio ejercicio1 = new Ejercicio();
        ejercicio1.setNombre("Levantamiento de potencia");
        ejercicio1.setTipo("FUERZA");
        ejercicio1.setCalorias(180);
        ejercicio1.setDificultad("facil");
        ejercicio1.setDescripcion("Ejercicio de fuerza para desarrollar la potencia muscular.");
        ejercicio1.setGrupoMuscular("Piernas, espalda");
        ejercicio1.setUrlDemo("");
        ejercicioRepository.save(ejercicio1);

        Ejercicio ejercicio2 = new Ejercicio();
        ejercicio2.setNombre("Sprints");
        ejercicio2.setTipo("VELOCIDAD");
        ejercicio2.setCalorias(100);
        ejercicio2.setDificultad("facil");
        ejercicio2.setDescripcion("Ejercicio de velocidad que consiste en carreras cortas y rápidas.");
        ejercicio2.setGrupoMuscular("Piernas, corazón");
        ejercicio2.setUrlDemo("");
        ejercicioRepository.save(ejercicio2);

        Ejercicio ejercicio3 = new Ejercicio();
        ejercicio3.setNombre("Bicicleta");
        ejercicio3.setTipo("AEROBICA");
        ejercicio3.setCalorias(110);
        ejercicio3.setDificultad("facil");
        ejercicio3.setDescripcion("Ejercicio aeróbico que mejora la resistencia cardiovascular.");
        ejercicio3.setGrupoMuscular("Piernas, corazón");
        ejercicio3.setUrlDemo("");
        ejercicioRepository.save(ejercicio3);

        Ejercicio ejercicio4 = new Ejercicio();
        ejercicio4.setNombre("Yoga");
        ejercicio4.setTipo("FLEXIBILIDAD");
        ejercicio4.setCalorias(70);
        ejercicio4.setDificultad("facil");
        ejercicio4.setDescripcion("Ejercicio de flexibilidad que combina posturas físicas y respiración.");
        ejercicio4.setGrupoMuscular("Cuerpo entero");
        ejercicio4.setUrlDemo("");
        ejercicioRepository.save(ejercicio4);

        Ejercicio ejercicio5 = new Ejercicio();
        ejercicio5.setNombre("Natación");
        ejercicio5.setTipo("AEROBICA");
        ejercicio5.setCalorias(200);
        ejercicio5.setDificultad("medio");
        ejercicio5.setDescripcion("Ejercicio aeróbico que trabaja todo el cuerpo y mejora la capacidad pulmonar.");
        ejercicio5.setGrupoMuscular("Cuerpo entero");
        ejercicio5.setUrlDemo("");
        ejercicioRepository.save(ejercicio5);

        Ejercicio ejercicio6 = new Ejercicio();
        ejercicio6.setNombre("Sentadillas");
        ejercicio6.setTipo("FUERZA");
        ejercicio6.setCalorias(80);
        ejercicio6.setDificultad("facil");
        ejercicio6.setDescripcion("Ejercicio de fuerza que fortalece las piernas y glúteos.");
        ejercicio6.setGrupoMuscular("Piernas, glúteos");
        ejercicio6.setUrlDemo("");
        ejercicioRepository.save(ejercicio6);

        Ejercicio ejercicio7 = new Ejercicio();
        ejercicio7.setNombre("Flexiones");
        ejercicio7.setTipo("FUERZA");
        ejercicio7.setCalorias(90);
        ejercicio7.setDificultad("facil");
        ejercicio7.setDescripcion("Ejercicio de fuerza que trabaja el pecho, hombros y tríceps.");
        ejercicio7.setGrupoMuscular("Pecho, hombros, tríceps");
        ejercicio7.setUrlDemo("");
        ejercicioRepository.save(ejercicio7);

        Ejercicio ejercicio8 = new Ejercicio();
        ejercicio8.setNombre("Estiramientos");
        ejercicio8.setTipo("FLEXIBILIDAD");
        ejercicio8.setCalorias(30);
        ejercicio8.setDificultad("facil");
        ejercicio8.setDescripcion("Ejercicio de flexibilidad para mejorar la movilidad y prevenir lesiones.");
        ejercicio8.setGrupoMuscular("Cuerpo entero");
        ejercicio8.setUrlDemo("");
        ejercicioRepository.save(ejercicio8);

        // Rubén Ipiña Rivas

        Rutina rutina1 = new Rutina();
        rutina1.setNombre("Rutina para principiantes");
        rutina1.setDescripcion("La rutina perfecta para quien esta empezando");
        rutina1.setEntrenadorIdentrenador(entrenador);
        rutina1.setTipo("Cross");
        rutinaRepository.save(rutina1);

        cliente.setRutinaIdrutina(rutina1);
        clienteRepository.save(cliente);

        Sesion sesion1 = new Sesion();
        sesion1.setDia("L");
        sesion1.setRutinaIdrutina(rutina1);
        sesionRepository.save(sesion1);

        Sesion sesion2 = new Sesion();
        sesion2.setDia("V");
        sesion2.setRutinaIdrutina(rutina1);
        sesionRepository.save(sesion2);

        SesionHasEjercicio sesionhasejercicio1 = new SesionHasEjercicio();
        sesionhasejercicio1.setEjercicioIdejercicio(ejercicio1);
        sesionhasejercicio1.setPeso(0);
        sesionhasejercicio1.setSeries(10);
        sesionhasejercicio1.setRepeticiones(5);
        sesionhasejercicio1.setSesionIdsesion(sesion1);
        sesionhasejercicio1.setId(new SesionHasEjercicioId());
        sesionHasEjercicioRepository.save(sesionhasejercicio1);

        SesionHasEjercicio sesionhasejercicio2 = new SesionHasEjercicio();
        sesionhasejercicio2.setEjercicioIdejercicio(ejercicio2);
        sesionhasejercicio2.setPeso(0);
        sesionhasejercicio2.setSeries(20);
        sesionhasejercicio2.setRepeticiones(50);
        sesionhasejercicio2.setSesionIdsesion(sesion2);
        sesionhasejercicio2.setId(new SesionHasEjercicioId());
        sesionHasEjercicioRepository.save(sesionhasejercicio2);

    }



}



