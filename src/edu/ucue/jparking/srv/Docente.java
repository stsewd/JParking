/**
 * 
 */
package edu.ucue.jparking.srv;

/**
 *
 * @author Santos Gallegos
 */
public class Docente extends Usuario {
    private static final float VALOR_PARQUEADERO = 50f;

    /**
     * @return the VALOR_PARQUEADERO
     */
    public static float getVALOR_PARQUEADERO() {
        return VALOR_PARQUEADERO;
    }

    public Docente(String cedula, String nombres, String apellidos) {
        super(cedula, nombres, apellidos);
    }

    @Override
    public OrdenPago generarOrdenPago() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
