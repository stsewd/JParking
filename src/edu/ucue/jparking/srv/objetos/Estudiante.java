/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import edu.ucue.jparking.srv.enums.TipoUsuario;

/**
 *
 * @author Santos Gallegos
 */
public class Estudiante extends Usuario{
    private static final float VALOR_PARQUEADERO = 30f;
    
    public Estudiante(String cedula, String nombres, String apellidos) {
        super(cedula, nombres, apellidos, TipoUsuario.ESTUDIANTE);
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
