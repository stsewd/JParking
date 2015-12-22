/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import edu.ucue.jparking.srv.excepciones.FueraDelDiaDePagoException;
import edu.ucue.jparking.srv.excepciones.ContratoNoEstablecidoException;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.excepciones.PagoYaRealizadoException;
import java.util.Calendar;

/**
 *
 * @author Santos Gallegos
 */
public abstract class Usuario extends Persona{

    private Calendar fechaContrato;
    private static final int diasContrato = 30;
    private String parqueaderoActual;
    
    //private TipoUsuario tipoUsuario;
    /**
     * 
     * @param cedula
     * @param nombres
     * @param apellidos 
     * @param direccion 
     * @param telefono 
     * @param tipoUsuario 
     */
    public Usuario(String cedula, String nombres, String apellidos, String direccion, String telefono, TipoUsuario tipoUsuario) {
        super(cedula, nombres, apellidos, direccion, telefono, tipoUsuario);
        this.fechaContrato = null;
    }

    /**
     * @return the fechaContrato
     */
    public Calendar getFechaContrato() {
        return fechaContrato;
    }

    /**
     * @param fechaContrato the fechaContrato to set
     */
    public void setFechaContrato(Calendar fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    /**
     * 
     * @return 
     */
    public boolean estaDebiendo() {
        Calendar fechaActual = Calendar.getInstance();
        //fechaActual.add(Calendar.DAY_OF_WEEK, -getDiasContrato());
        fechaActual.roll(Calendar.DAY_OF_WEEK, -getDiasContrato());
        return this.getFechaContrato().before(fechaActual);
    }
    
    public void cancelarPago() throws PagoYaRealizadoException {
        if (!estaDebiendo())
            throw new PagoYaRealizadoException(this.getCedula());
        this.setFechaContrato(Calendar.getInstance());
    }
    
    /**
     * @return the diasContrato
     */
    public static int getDiasContrato() {
        return diasContrato;
    }
    
    /**
     *
     * @return La orden de pago del usuario que llama el método
     */
    public OrdenPago generarOrdenPago() throws ContratoNoEstablecidoException, FueraDelDiaDePagoException{
        if(getFechaContrato() == null)
            throw new ContratoNoEstablecidoException(getCedula());
        
        Calendar fechaActual = Calendar.getInstance();
        fechaActual.roll(Calendar.DAY_OF_WEEK, -(getDiasContrato() - 5));
        if(this.getFechaContrato().before(fechaActual))
            throw new FueraDelDiaDePagoException(getDiasContrato());
        return null;
    }

    /**
     * @return the tipoUsuario
     */
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @return the parqueaderoActual
     */
    public String getParqueaderoActual() {
        return parqueaderoActual;
    }

    /**
     * @param parqueaderoActual the parqueaderoActual to set
     */
    public void setParqueaderoActual(String parqueaderoActual) {
        this.parqueaderoActual = parqueaderoActual;
    }
    
}
