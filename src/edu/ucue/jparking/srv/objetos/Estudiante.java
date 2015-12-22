/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import edu.ucue.jparking.srv.excepciones.ContratoNoEstablecidoException;
import edu.ucue.jparking.srv.enums.TipoUsuario;

/**
 *
 * @author Santos Gallegos
 */
public class Estudiante extends Usuario{
    private static final float VALOR_PARQUEADERO = 30f;
    
    public Estudiante(String cedula, String nombres, String apellidos, String direccion, String telefono) {
        super(cedula, nombres, apellidos,direccion, telefono, TipoUsuario.ESTUDIANTE);
    }

    @Override
    public OrdenPago generarOrdenPago() throws ContratoNoEstablecidoException {
        super.generarOrdenPago();
        return new OrdenPago(getCedula(), getVALOR_PARQUEADERO());
    }
    
    /**
     * @return the VALOR_PARQUEADERO
     */
    public static float getVALOR_PARQUEADERO() {
        return VALOR_PARQUEADERO;
    }
    
}
