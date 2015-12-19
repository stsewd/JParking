/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

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
    
    //private TipoUsuario tipoUsuario;
    /**
     * 
     * @param cedula
     * @param nombres
     * @param apellidos 
     */
    public Usuario(String cedula, String nombres, String apellidos,String direccion, String telefono, TipoUsuario tipoUsuario) {
        super(cedula, nombres, apellidos, direccion,telefono, tipoUsuario);
        this.fechaContrato = Calendar.getInstance();
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
        fechaActual.add(Calendar.DAY_OF_WEEK, -diasContrato);
        return this.fechaContrato.compareTo(fechaActual) < 0;
    }
    
    public void cancelarPago() throws PagoYaRealizadoException {
        if (!estaDebiendo())
            throw new PagoYaRealizadoException(this.getCedula());
        this.fechaContrato = Calendar.getInstance();
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
    public abstract OrdenPago generarOrdenPago();

    /**
     * @return the tipoUsuario
     */
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

}
