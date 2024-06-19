package es.taw.grupo1.dto;

import lombok.Data;

@Data
public class EntrenadorDTO {
    private Integer id;
    private String tipo;
    private Integer usuarioIdusuario;  // Aquí asumo que el ID del usuario es de tipo Integer

}