package es.taw.grupo1.dto;

import lombok.Data;

@Data
public class SesionHasEjercicioDTO {
    private SesionHasEjercicioIdDTO id;
    private Integer sesionIdsesion;
    private Integer ejercicioIdejercicio;
    private Integer series;
    private Integer repeticiones;
    private Integer peso;
}