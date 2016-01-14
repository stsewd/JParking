/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import edu.ucue.jparking.srv.Utilidades;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Santos Gallegos
 */
public class Campus implements Comparable<Campus>, Serializable {
    private static final int MAXLEN = 30;
    
    private final String nombre;
    private String direccion;
    private static final HoraDia horaApertura = new HoraDia(6, 30);
    private static final HoraDia horaCierre = new HoraDia(21, 30);
    private boolean activo;
    
    private Map<String, Puerta> puertas;
    private Map<String, Portero> porteros;
    private Map<String, Parqueadero> parqueaderos;
    
    public Campus(String nombre, String direccion) {
        this.nombre = nombre + Utilidades.fill(MAXLEN - nombre.length());
        this.direccion = direccion;
        this.activo = true;
        
        puertas = new HashMap<>();
        porteros = new HashMap<>();
        parqueaderos = new HashMap<>();
    }
    
    public void addPuerta(String idPuerta, Puerta puerta){
        puertas.put(idPuerta, puerta);
    }
    
    public void delPuerta(String idPuerta){
        puertas.remove(idPuerta);
    }
    
    public Puerta getPuerta(String idPuerta){
        return puertas.get(idPuerta);
    }
    
    public void addPortero(String cedula, Portero portero){
        porteros.put(cedula, portero);
    }
    
    public void delPortero(String cedula){
        porteros.remove(cedula);
    }
    
    public Portero getPortero(String cedula){
        return porteros.get(cedula);
    }
    
    public void addParqueadero(String idParqueadero, Parqueadero parqueadero){
        parqueaderos.put(idParqueadero, parqueadero);
    }
    
    public void delParqueadero(String idParqueadero){
        parqueaderos.remove(idParqueadero);
    }
    
    public Parqueadero getParqueadero(String idParqueadero){
        return parqueaderos.get(idParqueadero);
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
        return direccion.trim();
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion + Utilidades.fill(MAXLEN - direccion.length());
    }
    
    public Collection<Parqueadero> getParqueaderos(){
        return parqueaderos.values();
    }
    
    public Collection<Puerta> getPuertas() {
        return puertas.values();
    }

    public Collection<Portero> getPorteros() {
        return porteros.values();
    }

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
        return getNombre().compareTo(o.getNombre());
    }
}
