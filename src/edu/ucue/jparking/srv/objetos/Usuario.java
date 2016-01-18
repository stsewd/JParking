/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import edu.ucue.jparking.srv.excepciones.UsuarioNoRegistradoEnUnParqueaderoException;
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
public abstract class Usuario extends Persona {

    private Calendar fechaContrato;
    private Set<Parqueadero> parqueaderos;
    private boolean in;
    
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
        fechaActual.add(Calendar.DAY_OF_MONTH, -getDiasContrato());
        return fechaActual.after(this.getFechaContrato());
    }
    
    public void cancelarPago() throws PagoYaRealizadoException {
        if (fechaContrato != null && !estaDebiendo())
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
    public OrdenPago generarOrdenPago() throws ContratoNoEstablecidoException, FueraDelDiaDePagoException, UsuarioNoRegistradoEnUnParqueaderoException{
        /*
        if(getFechaContrato() == null)
            throw new ContratoNoEstablecidoException(getCedula());
        */
        if(parqueaderos.isEmpty())
            throw new UsuarioNoRegistradoEnUnParqueaderoException(getCedula());
        if(fechaContrato == null)
            return null;
        
        if(!estaDebiendo()){
            Calendar fechaActual = Calendar.getInstance();
            fechaActual.add(Calendar.DAY_OF_MONTH, -(getDiasContrato() - 5));
            if(fechaActual.before(this.getFechaContrato()))
                throw new FueraDelDiaDePagoException(getDiasContrato());
        }
        return null;
    }
    
    public void addParqueadero(Parqueadero parqueadero){
        parqueaderos.add(parqueadero);
    }
    
    public void delParqueadero(Parqueadero parqueadero){
        parqueaderos.remove(parqueadero);
    }
    
    /**
     * @return the parqueaderos
     */
    public Set<Parqueadero> getParqueaderos() {
        return parqueaderos;
    }
   
    public Registro getRegistro(TipoTramite tipoTramite){
        Registro registro;
        registro = new RegistroPagos(this, tipoTramite);
        return registro;
    }
    
    public abstract double getValorParqueadero();

    /**
     * @return the in
     */
    public boolean isIn() {
        return in;
    }

    /**
     * @param in the in to set
     */
    public void setIn(boolean in) {
        this.in = in;
    }
}
