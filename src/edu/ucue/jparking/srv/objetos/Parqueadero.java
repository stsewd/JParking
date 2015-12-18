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
    
    private Set<String> usuarios; //Cedula de usuarios
    private Set<String> puertasEntrada; //Id de puertas
    private Set<String> puertasSalida; //Id de puertas
   
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

    /**
     * @return the usuarios
     */
    public Set<String> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(Set<String> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @return the puertasEntrada
     */
    public Set<String> getPuertasEntrada() {
        return puertasEntrada;
    }

    /**
     * @param puertasEntrada the puertasEntrada to set
     */
    public void setPuertasEntrada(Set<String> puertasEntrada) {
        this.puertasEntrada = puertasEntrada;
    }

    /**
     * @return the puertasSalida
     */
    public Set<String> getPuertasSalida() {
        return puertasSalida;
    }

    /**
     * @param puertasSalida the puertasSalida to set
     */
    public void setPuertasSalida(Set<String> puertasSalida) {
        this.puertasSalida = puertasSalida;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
}
