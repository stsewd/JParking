/**
 * 
 */
package edu.ucue.jparking.srv;

/**
 *
 * @author Santos Gallegos
 */
public class Estudiante extends Usuario{
    private static final float VALOR_PARQUEADERO = 30f;
    
    public Estudiante(String cedula, String nombres, String apellidos) {
        super(cedula, nombres, apellidos);
    }

    @Override
    public OrdenPago generarOrdenPago() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
