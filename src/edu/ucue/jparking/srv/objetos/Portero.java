/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import edu.ucue.jparking.srv.enums.TipoUsuario;

/**
 *
 * @author Santos Gallegos
 */
public class Portero extends Persona {
    private String nombreCampus;

    public Portero(Campus campus, String cedula, String nombres, String apellidos, String direccion, String telefono) {
        super(cedula, nombres, apellidos, direccion, telefono, TipoUsuario.PORTERO);
        this.nombreCampus = campus.getNombre();
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
    
}
