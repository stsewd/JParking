/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

/**
 *
 * @author Santos Gallegos
 */
public class Puerta {
    
    private final String id;
    private String ubicacion;
    private boolean activa;
    
    

    public Puerta(String ubicacion, String id) {
        this.ubicacion = ubicacion;
        
        this.activa = true;
        this.id = id;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @return the activa
     */
    public boolean estaActiva() {
        return activa;
    }    

    public String getId() {
        return id;
    }

    
    
}
