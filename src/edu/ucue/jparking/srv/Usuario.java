/**
 * 
 */
package edu.ucue.jparking.srv;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Santos Gallegos
 */
public abstract class Usuario extends Persona{
    private List<Parqueadero> parqueaderos;
    private Calendar fechaContrato;
    private static final int diasContrato = 30;
    
    /**
     * 
     * @param cedula
     * @param nombres
     * @param apellidos 
     */
    public Usuario(String cedula, String nombres, String apellidos) {
        super(cedula, nombres, apellidos);
        this.fechaContrato = Calendar.getInstance();
        this.parqueaderos = new ArrayList<>();
    }

    /**
     * @return the parqueaderos
     */
    public List<Parqueadero> getParqueaderos() {
        return parqueaderos;
    }

    /**
     * @param parqueaderos the parqueaderos to set
     */
    public void setParqueaderos(List<Parqueadero> parqueaderos) {
        this.parqueaderos = parqueaderos;
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
            throw new PagoYaRealizadoException(this.cedula);
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
