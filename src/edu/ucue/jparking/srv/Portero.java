/**
 * 
 */
package edu.ucue.jparking.srv;

/**
 *
 * @author Santos Gallegos
 */
public class Portero extends Persona{
    private String nombreCampus;

    public Portero(String cedula, String nombres, String apellidos, String nombreCampus) {
        super(cedula, nombres, apellidos);
        this.nombreCampus = nombreCampus;
    }

    /**
     * @return the nombreCampus
     */
    public String getNombreCampus() {
        return nombreCampus;
    }

    /**
     * @param nombreCampus the nombreCampus to set
     */
    public void setNombreCampus(String nombreCampus) {
        this.nombreCampus = nombreCampus;
    }
    
    
}
