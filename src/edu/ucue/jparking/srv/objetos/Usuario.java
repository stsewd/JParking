/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import edu.ucue.jparking.srv.enums.TipoTramite;
import edu.ucue.jparking.srv.excepciones.FueraDelDiaDePagoException;
import edu.ucue.jparking.srv.excepciones.ContratoNoEstablecidoException;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.excepciones.PagoYaRealizadoException;
import edu.ucue.jparking.srv.objetos.registros.Registro;
import edu.ucue.jparking.srv.objetos.registros.RegistroPagos;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public abstract class Usuario extends Persona{

    private Calendar fechaContrato;
    //private static final int diasContrato = 30;
    private Set<Parqueadero> parqueaderos;
    
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
        fechaActual.roll(Calendar.DAY_OF_WEEK, -getDiasContrato());
        return fechaActual.after(this.getFechaContrato());
    }
    
    public void cancelarPago() throws PagoYaRealizadoException {
        if (!estaDebiendo())
            throw new PagoYaRealizadoException(this.getCedula());
        this.setFechaContrato(Calendar.getInstance());
    }
    
    /**
     * @return the diasContrato
     */
    public abstract int getDiasContrato();
    
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
        fechaActual.roll(Calendar.DAY_OF_MONTH, -(getDiasContrato() - 5));
        if(fechaActual.before(this.getFechaContrato()))
            throw new FueraDelDiaDePagoException(getDiasContrato());
        return null;
    }

    /**
     * @return the parqueaderos
     */
    public Set<Parqueadero> getParqueaderos() {
        return parqueaderos;
    }

    /**
     * @param parqueaderos the parqueaderos to set
     */
    public void setParqueaderos(Set<Parqueadero> parqueaderos) {
        this.parqueaderos = parqueaderos;
    }
    
    public Registro getRegistro(TipoTramite tipoTramite){
        Registro registro = null;
        registro = new RegistroPagos(this, tipoTramite);
        return registro;
    }
}
