package es.taw.grupo1.ui;

import java.util.List;

public class SessionObject {

    private String dia;
    private List<EjercicioObject> ejercicios;

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public List<EjercicioObject> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<EjercicioObject> ejercicios) {
        this.ejercicios = ejercicios;
    }


    public SessionObject(String dia, List<EjercicioObject> ejercicios) {
        this.dia = dia;
        this.ejercicios = ejercicios;
    }
    public SessionObject() {

    }

}
