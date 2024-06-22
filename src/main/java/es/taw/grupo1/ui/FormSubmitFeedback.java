package es.taw.grupo1.ui;

import es.taw.grupo1.entity.SesionHasEjercicioId;

public class FormSubmitFeedback {
    private Integer seriesRealizadas;
    private Integer repeticionesRealizadas;
    private Integer pesoUtilizado;
    private String fechaSubmit;
    private String comentarios;
    private Integer sesionId;
    private Integer ejercicioId;

    public Integer getSesionId() {
        return sesionId;
    }

    public void setSesionId(Integer sesionId) {
        this.sesionId = sesionId;
    }

    public Integer getEjercicioId() {
        return ejercicioId;
    }

    public void setEjercicioId(Integer ejercicioId) {
        this.ejercicioId = ejercicioId;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getFechaSubmit() {
        return fechaSubmit;
    }

    public void setFechaSubmit(String fechaSubmit) {
        this.fechaSubmit = fechaSubmit;
    }

    public Integer getSeriesRealizadas() {
        return seriesRealizadas;
    }

    public void setSeriesRealizadas(Integer seriesRealizadas) {
        this.seriesRealizadas = seriesRealizadas;
    }

    public Integer getRepeticionesRealizadas() {
        return repeticionesRealizadas;
    }

    public void setRepeticionesRealizadas(Integer repeticionesRealizadas) {
        this.repeticionesRealizadas = repeticionesRealizadas;
    }

    public Integer getPesoUtilizado() {
        return pesoUtilizado;
    }

    public void setPesoUtilizado(Integer pesoUtilizado) {
        this.pesoUtilizado = pesoUtilizado;
    }
}
