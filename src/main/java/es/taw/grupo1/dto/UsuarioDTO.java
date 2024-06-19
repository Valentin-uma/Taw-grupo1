package es.taw.grupo1.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Integer id;
    private String email;
    private String nombre;
    private String apellido;
    private String rangoEdad;
    private String sexo;
}