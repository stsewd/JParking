/**
 * 
 */
package edu.ucue.jparking.srv;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Santos Gallegos
 */
public abstract class Usuario extends Persona{
    private List<Parqueadero> parqueaderos;
    private Calendar fechaContrato;
    private boolean debiendo;
    
    /**
     * 
     * @param cedula
     * @param nombres
     * @param apellidos 
     */
    public Usuario(String cedula, String nombres, String apellidos) {
        super(cedula, nombres, apellidos);
        this.fechaContrato = new GregorianCalendar();
        this.debiendo = false;
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
        return debiendo;
    }

    /**
     * @param debiendo the debiendo to set
     */
    public void setDebiendo(boolean debiendo) {
        this.debiendo = debiendo;
    }
}
