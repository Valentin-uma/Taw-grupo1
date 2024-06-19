package es.taw.grupo1.dto;

import lombok.Data;

@Data
public class EjercicioDTO {
    private Integer id;
    private String nombre;
    private String tipo;
    private String descripcion;
    private String urlDemo;
    private String dificultad;
    private Integer calorias;
    private String grupoMuscular;

}
