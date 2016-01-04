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
public class Empleado extends Usuario {
    private static final double VALOR_PARQUEADERO = 30;
    private static final int DIAS_CONTRATO = 30;

    public Empleado(String cedula, String nombres, String apellidos, String direccion, String telefono) {
        super(cedula, nombres, apellidos, direccion, telefono, TipoUsuario.EMPLEADO);
    }

    @Override
    public OrdenPago generarOrdenPago() throws ContratoNoEstablecidoException, FueraDelDiaDePagoException, UsuarioNoRegistradoEnUnParqueaderoException {
        super.generarOrdenPago();
        return new OrdenPago(getCedula(), getValorParqueadero());
    }
        
    @Override
    public int getDiasContrato() {
        return Empleado.DIAS_CONTRATO;
    }

    @Override
    public double getValorParqueadero() {
        return Empleado.VALOR_PARQUEADERO;
    }
}
