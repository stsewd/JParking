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
public class Parqueadero implements Comparable<Parqueadero> {
    
    private Map<String, Usuario> usuarios; //Cedula de usuario
    private Map<String, Puerta> puertasEntrada; //Id de puerta
    private Map<String, Puerta> puertasSalida; //Id de puerta

    private boolean activo;
    private final String id;
    private String ubicacion;
    private int numeroLugares;
    
    private Campus campus;

    public Parqueadero(String ubicacion, int numeroLugares, String id, Campus nombreCampus) {
        this.ubicacion=ubicacion;
        this.numeroLugares=numeroLugares;
        this.id = id;
        this.campus = nombreCampus;
        this.puertasEntrada = new HashMap<>();
        this.puertasSalida = new HashMap<>();
        this.usuarios = new HashMap<>();
        this.activo = true;
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
     * @return the numeroLugaresOcupados
     */
    public int getNumeroLugaresOcupados() {
        return usuarios.size();
    }

    /**
     * @return the usuarios
     */
    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(Map<String, Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @param puertasEntrada the puertasEntrada to set
     */
    public void setPuertasEntrada(Map<String, Puerta> puertasEntrada) {
        this.puertasEntrada = puertasEntrada;
    }

    /**
     * @param puertasSalida the puertasSalida to set
     */
    public void setPuertasSalida(Map<String, Puerta> puertasSalida) {
        this.puertasSalida = puertasSalida;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the campus
     */
    public Campus getCampus() {
        return campus;
    }

    /**
     * @param nombreCampus the campus to set
     */
    public void setCampus(Campus nombreCampus) {
        this.campus = nombreCampus;
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
    public int compareTo(Parqueadero o) {
        return getId().compareToIgnoreCase(o.getId());
    }

    /**
     * @return the puertasEntrada
     */
    public Map<String, Puerta> getPuertasEntrada() {
        return puertasEntrada;
    }

    /**
     * @return the puertasSalida
     */
    public Map<String, Puerta> getPuertasSalida() {
        return puertasSalida;
    }
    
}
