/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import edu.ucue.jparking.srv.excepciones.FueraDelDiaDePagoException;
import edu.ucue.jparking.srv.excepciones.ContratoNoEstablecidoException;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.excepciones.PagoYaRealizadoException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public abstract class Usuario extends Persona{

    private Calendar fechaContrato;
    private static final int diasContrato = 30;
    private Set<String> parqueaderos;
    private boolean dentro;
    
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
        this.parqueaderos = new HashSet<>();
        this.dentro = false;
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
     * @throws edu.ucue.jparking.srv.excepciones.ContratoNoEstablecidoException
     * @throws edu.ucue.jparking.srv.excepciones.FueraDelDiaDePagoException
     */
    public OrdenPago generarOrdenPago() throws ContratoNoEstablecidoException, FueraDelDiaDePagoException{
        if(getFechaContrato() == null)
            throw new ContratoNoEstablecidoException(getCedula());
        
        Calendar fechaActual = Calendar.getInstance();
        fechaActual.roll(Calendar.DAY_OF_WEEK, -(getDiasContrato() - 5));
        if(fechaActual.before(this.getFechaContrato()))
            throw new FueraDelDiaDePagoException(getDiasContrato());
        return null;
    }

    /**
     * @return the parqueaderos
     */
    public Set<String> getParqueaderos() {
        return parqueaderos;
    }

    /**
     * @param parqueaderos the parqueaderos to set
     */
    public void setParqueaderos(Set<String> parqueaderos) {
        this.parqueaderos = parqueaderos;
    }

    /**
     * @return the dentro
     */
    public boolean isDentro() {
        return dentro;
    }

    /**
     * @param dentro the dentro to set
     */
    public void setDentro(boolean dentro) {
        this.dentro = dentro;
    }
    
}
