package es.taw.grupo1.controller;

import es.taw.grupo1.dao.*;
import es.taw.grupo1.entity.Cliente;
import es.taw.grupo1.entity.Ejercicio;
import es.taw.grupo1.entity.Entrenador;
import es.taw.grupo1.entity.Usuario;
import es.taw.grupo1.ui.FormLogin;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.Normalizer;

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


    @GetMapping("/login")
    public String login(Model m) {
        m.addAttribute("formLogin", new FormLogin());
        return "cliente/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("formLogin") FormLogin formLogin, HttpSession session) {
        // Tenemos que verificar primero si la contraseña es correcta
        //session.setAttribute("usuario", );
        return "redirect:/";
    }

    @GetMapping("/")
    public String index() {
        return "cliente/index";
    }



}
