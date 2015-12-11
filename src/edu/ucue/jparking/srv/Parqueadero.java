/**
 * 
 */
package edu.ucue.jparking.srv;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santos Gallegos
 */
public class Parqueadero {
    
    private Campus campus;
    private List<Usuario> usuarios;
    private List<Puerta> puertasEntrada;
    private List<Puerta> puertasSalida;
    
    private String ubicacion;
    private int numeroLugares;
    private int numeroLugaresDisponibles;

    public Parqueadero(String ubicacion,int numeroLugares) {
        this.ubicacion=ubicacion;
        this.numeroLugares=numeroLugares;
        this.campus = campus;
        this.puertasEntrada = new ArrayList<>();
        this.puertasSalida = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    /**
     * @return the campus
     */
    public Campus getCampus() {
        return campus;
    }

    /**
     * @param campus the campus to set
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @return the puertasEntrada
     */
    public List<Puerta> getPuertasEntrada() {
        return puertasEntrada;
    }

    /**
     * @param puertasEntrada the puertasEntrada to set
     */
    public void setPuertasEntrada(List<Puerta> puertasEntrada) {
        this.puertasEntrada = puertasEntrada;
    }

    /**
     * @return the puertasSalida
     */
    public List<Puerta> getPuertasSalida() {
        return puertasSalida;
    }

    /**
     * @param puertasSalida the puertasSalida to set
     */
    public void setPuertasSalida(List<Puerta> puertasSalida) {
        this.puertasSalida = puertasSalida;
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
