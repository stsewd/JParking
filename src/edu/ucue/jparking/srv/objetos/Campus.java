/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author Santos Gallegos
 */
public class Campus {
    private final String nombre;
    private String direccion;
    private static final HoraDia horaApertura = new HoraDia(6, 30);
    private static final HoraDia horaCierre = new HoraDia(21, 30);
    
    private Map<String, Puerta> puertas;
    
    private Map<String, Portero> porteros;
    private Map<String, Parqueadero> parqueaderos;
    
    public Campus(String nombre, String direccion) {
        this.nombre=nombre;
        this.direccion=direccion;
        
        puertas = new HashMap<>();
        porteros = new HashMap<>();
        parqueaderos = new HashMap<>();
    }


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

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
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
