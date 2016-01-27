/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public class Parqueadero implements Comparable<Parqueadero>, Serializable {
    
    // private Map<String, Usuario> usuarios; //Cedula de usuario
    private Set<String> usuarios; //Cedula de usuario
    private Map<String, Puerta> puertasEntrada; //Id de puerta
    private Map<String, Puerta> puertasSalida; //Id de puerta

    private boolean activo;
    private final String id;
    private String ubicacion;
    private int numeroLugares;
    
    private final String nombreCampus;

    public Parqueadero(String ubicacion, int numeroLugares, String id, Campus nombreCampus) {
        this.ubicacion = ubicacion;
        this.numeroLugares = numeroLugares;
        this.id = id;
        this.nombreCampus = nombreCampus.getNombre();
        this.puertasEntrada = new HashMap<>();
        this.puertasSalida = new HashMap<>();
        // this.usuarios = new HashMap<>();
        this.usuarios = new HashSet<>();
        this.activo = true;
    }
    
    public void addUsuario(String cedula, Usuario usuario){
        // usuarios.put(cedula, usuario);
        usuarios.add(cedula);
    }
    
    public void delUsuario(String cedula){
        usuarios.remove(cedula);
    }
    
//    public Usuario getUsuario(String cedula){
//        return usuarios.get(cedula);
//    }
    
    public void addPuertaEntrada(String idPuerta, Puerta puerta){
        puertasEntrada.put(idPuerta, puerta);
    }
    
    public void delPuertaEntrada(String idPuerta){
        puertasEntrada.remove(idPuerta);
    }
    
    public Puerta getPuertaEntrada(String idPuerta){
        return puertasEntrada.get(idPuerta);
    }
    
    public void addPuertaSalida(String idPuerta, Puerta puerta){
        puertasSalida.put(idPuerta, puerta);
    }
    
    public void delPuertaSalida(String idPuerta){
        puertasSalida.remove(idPuerta);
    }
    
    public Puerta getPuertaSalida(String idPuerta){
        return puertasSalida.get(idPuerta);
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
//    public Collection<Usuario> getUsuarios() {
//        return usuarios.values();
//    }
    
    public Collection<String> getUsuariosCedula(){
        return new HashSet(usuarios);
    }
    
    /**
     * @return the puertasEntrada
     */
    public Collection<Puerta> getPuertasEntrada() {
        return puertasEntrada.values();
    }

    /**
     * @return the puertasSalida
     */
    public Collection<Puerta> getPuertasSalida() {
        return puertasSalida.values();
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
    public String getCampus() {
        return nombreCampus;
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
        return getId().compareTo(o.getId());
    }
}
