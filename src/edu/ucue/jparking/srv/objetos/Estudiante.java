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
    private static final int DIAS_CONTRATO = 180;
    
    public Estudiante(String cedula, String nombres, String apellidos, String direccion, String telefono) {
        super(cedula, nombres, apellidos,direccion, telefono, TipoUsuario.ESTUDIANTE);
    }

    @Override
    public OrdenPago generarOrdenPago() throws ContratoNoEstablecidoException, FueraDelDiaDePagoException, UsuarioNoRegistradoEnUnParqueaderoException {
        super.generarOrdenPago();
        return new OrdenPago(getCedula(), getValorParqueadero());
    }
    
    @Override
    public int getDiasContrato() {
        return DIAS_CONTRATO;
    }

    @Override
    public double getValorParqueadero() {
        return VALOR_PARQUEADERO;
    }
    
}
