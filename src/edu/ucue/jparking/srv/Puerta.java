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
    private Campus campus;
    private boolean activa;

    public Puerta(String ubicacion, Campus campus) {
        this.ubicacion = ubicacion;
        this.campus = campus;
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
    public boolean estaActiva() {
        return activa;
    }    
    
}
