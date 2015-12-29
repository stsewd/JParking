/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

/**
 *
 * @author Santos Gallegos
 */
public class Puerta implements Comparable<Puerta> {
    
    private final String id;
    private String ubicacion;
    private boolean activa;
    private String idCampus;
    

    public Puerta(String ubicacion, String id, String idCampus) {
        this.ubicacion = ubicacion;
        this.idCampus = idCampus;
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
     * @return the idCampus
     */
    public String getIdCampus() {
        return idCampus;
    }

    /**
     * @param idCampus the idCampus to set
     */
    public void setIdCampus(String idCampus) {
        this.idCampus = idCampus;
    }

    @Override
    public int compareTo(Puerta o) {
        return getId().compareToIgnoreCase(o.getId());
    }

    
    
}
