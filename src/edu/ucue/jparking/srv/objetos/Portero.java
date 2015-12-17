/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import edu.ucue.jparking.srv.enums.TipoUsuario;

/**
 *
 * @author Santos Gallegos
 */
public class Portero extends Persona{
    private String campus;

    public Portero(String cedula, String nombres, String apellidos, String nombreCampus) {
        super(cedula, nombres, apellidos, TipoUsuario.PORTERO);
        this.campus = nombreCampus;
    }

    /**
     * @return the campus
     */
    public String getCampus() {
        return campus;
    }

    /**
     * @param campus the campus to set
     */
    public void setCampus(String campus) {
        this.campus = campus;
    }

    
    
}
