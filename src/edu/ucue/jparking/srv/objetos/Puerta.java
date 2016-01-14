/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import edu.ucue.jparking.srv.Utilidades;
import java.io.Serializable;

/**
 *
 * @author Santos Gallegos
 */
public class Puerta implements Comparable<Puerta>, Serializable {
    private static final int MAXLEN = 30;
    
    private final String id;
    private String ubicacion;
    private boolean activa;
    
    private Campus campus;
    

    public Puerta(String ubicacion, String id, Campus campus) {
        this.ubicacion = ubicacion + Utilidades.fill(MAXLEN - ubicacion.length());
        this.campus = campus;
        this.activa = true;
        this.id = id;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion + Utilidades.fill(MAXLEN - ubicacion.length());
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getUbicacion() {
        return ubicacion.trim();
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
     * @return the campus
     */
    public Campus getCampus() {
        return campus;
    }

    /**
     * @param campus the campus to set
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    @Override
    public int compareTo(Puerta o) {
        return getId().compareTo(o.getId());
    }
}
