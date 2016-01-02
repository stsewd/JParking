/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import edu.ucue.jparking.srv.excepciones.UsuarioNoRegistradoEnUnParqueaderoException;
import edu.ucue.jparking.srv.excepciones.FueraDelDiaDePagoException;
import edu.ucue.jparking.srv.excepciones.ContratoNoEstablecidoException;
import edu.ucue.jparking.srv.enums.TipoUsuario;

/**
 *
 * @author Santos Gallegos
 */
public class Estudiante extends Usuario{
    private static final double VALOR_PARQUEADERO = 75;
    private static final int diasContrato = 180;
    
    public Estudiante(String cedula, String nombres, String apellidos, String direccion, String telefono) {
        super(cedula, nombres, apellidos,direccion, telefono, TipoUsuario.ESTUDIANTE);
    }

    @Override
    public OrdenPago generarOrdenPago() throws ContratoNoEstablecidoException, FueraDelDiaDePagoException, UsuarioNoRegistradoEnUnParqueaderoException {
        super.generarOrdenPago();
        return new OrdenPago(getCedula(), getVALOR_PARQUEADERO());
    }
    
    /**
     * @return the VALOR_PARQUEADERO
     */
    public static double getVALOR_PARQUEADERO() {
        return VALOR_PARQUEADERO;
    }

    @Override
    public int getDiasContrato() {
        return this.diasContrato;
    }
    
}
