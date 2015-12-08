/**
 * 
 */
package edu.ucue.jparking.srv;

import java.util.Calendar;

/**
 *
 * @author Santos Gallegos
 */
public class Campus {

    /**
     * @return the horaApertura
     */
    public static HoraDia getHoraApertura() {
        return horaApertura;
    }

    /**
     * @return the horaCierre
     */
    public static HoraDia getHoraCierre() {
        return horaCierre;
    }
    
    private String nombre;
    private String direccion;
    private static final HoraDia horaApertura = new HoraDia(6,30);
    private static final HoraDia horaCierre = new HoraDia(21,30);
    
    public Campus(String nombre, String direccion) {
        this.nombre=nombre;
        this.direccion=direccion;
        
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
