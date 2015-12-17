/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public class Parqueadero {
    
    private Set<String> usuarios; //Cedula
    private Set<String> puertasEntrada;
    private Set<String> puertasSalida;
   
    private final String id;
    private String ubicacion;
    private int numeroLugares;
    private int numeroLugaresDisponibles;

    public Parqueadero(String ubicacion, int numeroLugares, String id) {
        this.ubicacion=ubicacion;
        this.numeroLugares=numeroLugares;
        this.id = id;
        
        this.puertasEntrada = new HashSet<>();
        this.puertasSalida = new HashSet<>();
        this.usuarios = new HashSet<>();
    }


    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the numeroLugares
     */
    public int getNumeroLugares() {
        return numeroLugares;
    }

    /**
     * @param numeroLugares the numeroLugares to set
     */
    public void setNumeroLugares(int numeroLugares) {
        this.numeroLugares = numeroLugares;
    }

    /**
     * @return the numeroLugaresDisponibles
     */
    public int getNumeroLugaresDisponibles() {
        return numeroLugaresDisponibles;
    }

    /**
     * @param numeroLugaresDisponibles the numeroLugaresDisponibles to set
     */
    public void setNumeroLugaresDisponibles(int numeroLugaresDisponibles) {
        this.numeroLugaresDisponibles = numeroLugaresDisponibles;
    }
}
