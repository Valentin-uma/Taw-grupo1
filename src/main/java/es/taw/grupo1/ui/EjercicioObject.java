package es.taw.grupo1.ui;


/*

AUTOR: Valentin Pecqueux

 */
public class EjercicioObject {

    private int idejercicio;
    private int numeroRepeticion;


    private int numeroSeries;
    private int peso;
    private String dia;
    private String idSessionEx;

    public String getIdSessionEx() {
        return idSessionEx;
    }

    public void setIdSessionEx(String idSessionEx) {
        this.idSessionEx = idSessionEx;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }



    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getNumeroRepeticion() {
        return numeroRepeticion;
    }

    public void setNumeroRepeticion(int numeroRepeticion) {
        this.numeroRepeticion = numeroRepeticion;
    }

    public int getIdejercicio() {
        return idejercicio;
    }

    public void setIdejercicio(int idejercicio) {
        this.idejercicio = idejercicio;
    }



    public int getNumeroSeries() {
        return numeroSeries;
    }

    public void setNumeroSeries(int numeroSeries) {
        this.numeroSeries = numeroSeries;
    }


    public EjercicioObject(int idejercicio, int numeroRepeticion, int peso,int numeroSeries,String dia,String idSessionEx) {
        this.idejercicio = idejercicio;
        this.numeroRepeticion = numeroRepeticion;
        this.peso = peso;
        this.dia = dia;
        this.idSessionEx = idSessionEx;
        this.numeroSeries = numeroSeries;

    }
    public EjercicioObject(String idSessionEx) {

        this.idSessionEx = idSessionEx;

    }
    public EjercicioObject() {

    }



}
