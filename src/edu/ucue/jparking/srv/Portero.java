/**
 * 
 */
package edu.ucue.jparking.srv;

/**
 *
 * @author Santos Gallegos
 */
public class Portero extends Persona{
    private Campus campus;

    public Portero(String cedula, String nombres, String apellidos, Campus campus) {
        super(cedula, nombres, apellidos);
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
