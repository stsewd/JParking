/**
 * 
 */
package edu.ucue.jparking.srv;

/**
 *
 * @author Santos Gallegos
 */
public class Parqueadero {
    
    /*
    private List<Usuario> usuarios;
    private List<Puerta> puertasEntrada;
    private List<Puerta> puertasSalida;
    */
   
    private final String id;
    private String ubicacion;
    private int numeroLugares;
    private int numeroLugaresDisponibles;

    public Parqueadero(String ubicacion, int numeroLugares, String id) {
        this.ubicacion=ubicacion;
        this.numeroLugares=numeroLugares;
        this.id = id;
        /*
        this.puertasEntrada = new ArrayList<>();
        this.puertasSalida = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        */
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
