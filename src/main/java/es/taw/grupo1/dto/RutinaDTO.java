package es.taw.grupo1.dto;

import lombok.Data;

@Data
public class RutinaDTO {
    private Integer id;
    private Integer entrenadorId;
    private String nombre;
    private String tipo;
    private String descripcion;
}