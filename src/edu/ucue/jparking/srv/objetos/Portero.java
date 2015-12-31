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
    private Campus campus;

    public Portero(Campus campus, String cedula, String nombres, String apellidos, String direccion, String telefono) {
        super(cedula, nombres, apellidos, direccion, telefono, TipoUsuario.PORTERO);
        this.campus = campus;
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
    
}
