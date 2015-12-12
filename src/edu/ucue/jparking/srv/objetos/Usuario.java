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
    /*
    private List<Parqueadero> parqueaderos;
    */
    private Calendar fechaContrato;
    private static final int diasContrato = 30;
    
    /**
     * 
     * @param cedula
     * @param nombres
     * @param apellidos 
     */
    public Usuario(String cedula, String nombres, String apellidos, TipoUsuario tipoUsuario) {
        super(cedula, nombres, apellidos, tipoUsuario);
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
     * @return the debiendo
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

}
