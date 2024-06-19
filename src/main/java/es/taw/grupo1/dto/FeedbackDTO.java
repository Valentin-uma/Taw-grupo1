package es.taw.grupo1.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class FeedbackDTO {
    private Integer id;
    private Instant fecha;
    private String descripcion;
    private Integer sesionIdsesion;
    private Integer clienteIdcliente;
}