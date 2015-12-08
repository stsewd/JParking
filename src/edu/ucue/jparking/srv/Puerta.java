/**
 * 
 */
package edu.ucue.jparking.srv;

/**
 *
 * @author Santos Gallegos
 */
public class Puerta {
    
    private String ubicacion;
    private boolean activa;

    public Puerta(String ubicacion) {
        this.ubicacion = ubicacion;
        this.activa = true;
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
    public boolean isActiva() {
        return activa;
    }
    
    
}
