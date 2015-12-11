/**
 * 
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.srv.enums.TipoUsuario;

/**
 *
 * @author Santos Gallegos
 */
public class Empleado extends Usuario {
    private static final float VALOR_PARQUEADERO = 30f;

    public Empleado(String cedula, String nombres, String apellidos) {
        super(cedula, nombres, apellidos, TipoUsuario.EMPLEADO);
    }

    @Override
    public OrdenPago generarOrdenPago() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    /**
     * @return the VALOR_PARQUEADERO
     */
    public static float getVALOR_PARQUEADERO() {
        return VALOR_PARQUEADERO;
    }
}
