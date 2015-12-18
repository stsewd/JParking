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

    public Portero(String cedula, String nombres, String apellidos, String direccion, String telefono, String nombreCampus) {
        super(cedula, nombres, apellidos, direccion, telefono, TipoUsuario.PORTERO);
        this.campus = campus;
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
