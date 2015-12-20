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
    private int numeroLugaresOcupados;
    private String nombreCampus;

    public Parqueadero(String ubicacion, int numeroLugares, String id, String nombreCampus) {
        this.ubicacion=ubicacion;
        this.numeroLugares=numeroLugares;
        this.id = id;
        this.nombreCampus = nombreCampus;
        this.numeroLugaresOcupados = 0;
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
        if(getNumeroLugaresOcupados() > this.numeroLugares)
            this.numeroLugaresOcupados = this.numeroLugares;
    }

    /**
     * @return the numeroLugaresOcupados
     */
    public int getNumeroLugaresOcupados() {
        return numeroLugaresOcupados;
    }

    /**
     * @param numeroLugaresOcupados the numeroLugaresOcupados to set
     */
    public void setNumeroLugaresOcupados(int numeroLugaresOcupados) {
        this.numeroLugaresOcupados = numeroLugaresOcupados;
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

    /**
     * @return the nombreCampus
     */
    public String getNombreCampus() {
        return nombreCampus;
    }

    /**
     * @param nombreCampus the nombreCampus to set
     */
    public void setNombreCampus(String nombreCampus) {
        this.nombreCampus = nombreCampus;
    }
}
