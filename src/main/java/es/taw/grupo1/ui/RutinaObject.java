package es.taw.grupo1.ui;

import java.util.List;

public class RutinaObject {

    private String nombre;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;

    private List<SessionObject> sessions;

    public List<SessionObject> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionObject> sessions) {
        this.sessions = sessions;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public RutinaObject(String nombre, List<SessionObject> sessions,String desc) {
        this.nombre = nombre;
        this.sessions = sessions;
        this.desc = desc;

    }

    public RutinaObject(){

    }

}
