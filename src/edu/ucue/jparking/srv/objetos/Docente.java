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
public class Docente extends Usuario {
    private static final float VALOR_PARQUEADERO = 35;
    private static final int diasContrato = 30;
    /**
     * @return the VALOR_PARQUEADERO
     */
    public static float getVALOR_PARQUEADERO() {
        return VALOR_PARQUEADERO;
    }

    public Docente(String cedula, String nombres, String apellidos,String direccion,String telefono) {
        super(cedula, nombres, apellidos, direccion, telefono, TipoUsuario.DOCENTE);
    }

    @Override
    public OrdenPago generarOrdenPago() throws ContratoNoEstablecidoException, FueraDelDiaDePagoException, UsuarioNoRegistradoEnUnParqueaderoException {
        super.generarOrdenPago();
        return new OrdenPago(getCedula(), getVALOR_PARQUEADERO());
    }

    @Override
    public int getDIAS_CONTRATO() {
        return this.diasContrato;
    }
    
}
