package es.taw.grupo1.dto;

import lombok.Data;

@Data
public class RutinaDTO {
    private Integer id;
    private Integer entrenadorId; // Vous pouvez utiliser l'ID de l'entité associée plutôt que l'objet complet
    private String nombre;
    private String tipo;
    private String descripcion;
}