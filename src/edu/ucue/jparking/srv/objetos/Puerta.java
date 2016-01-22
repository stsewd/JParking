/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import java.io.Serializable;

/**
 *
 * @author Santos Gallegos
 */
public class Puerta implements Comparable<Puerta>, Serializable {
    
    private final String id;
    private String ubicacion;
    private boolean activa;
    
    private String nombreCampus;
    

    public Puerta(String ubicacion, String id, Campus campus) {
        this.ubicacion = ubicacion;
        this.nombreCampus = campus.getNombre();
        this.activa = true;
        this.id = id;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @return the activa
     */
    public boolean estaActiva() {
        return activa;
    }    

    public String getId() {
        return id;
    }

    /**
     * @return the nombreCampus
     */
    public String getCampus() {
        return nombreCampus;
    }

    /**
     * @param campus the nombreCampus to set
     */
    public void setCampus(Campus campus) {
        this.nombreCampus = campus.getNombre();
    }

    @Override
    public int compareTo(Puerta o) {
        return getId().compareTo(o.getId());
    }
}
