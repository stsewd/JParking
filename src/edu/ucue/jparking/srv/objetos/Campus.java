/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Santos Gallegos
 */
public class Campus implements Comparable<Campus> {
    private final String nombre;
    private String direccion;
    private static final HoraDia horaApertura = new HoraDia(6, 30);
    private static final HoraDia horaCierre = new HoraDia(21, 30);
    private boolean activo;
    private Map<String, Puerta> puertas;
    private Map<String, Portero> porteros;
    private Map<String, Parqueadero> parqueaderos;
    
    public Campus(String nombre, String direccion) {
        this.nombre=nombre;
        this.direccion=direccion;
        this.activo=true;
        
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

    /**
     * @return the puertas
     */
    public Map<String, Puerta> getPuertas() {
        return puertas;
    }

    /**
     * @return the porteros
     */
    public Map<String, Portero> getPorteros() {
        return porteros;
    }

    /**
     * @return the parqueaderos
     */
    public Map<String, Parqueadero> getParqueaderos() {
        return parqueaderos;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public int compareTo(Campus o) {
        return getNombre().compareToIgnoreCase(o.getNombre());
    }
}
