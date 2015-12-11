/**
 * 
 */
package edu.ucue.jparking.srv;

/**
 *
 * @author Santos Gallegos
 */
public abstract class Persona {
    
    protected final TipoUsuario tipoUsuario;
    
    private final String cedula;
    private String nombres;
    private String apellidos;
    private boolean activo;
    
    /**
     * 
     * @param cedula Cédula de la persona
     * @param nombres Nombre(s) de la persona
     * @param apellidos Apellido(s) de la persona
     */
    public Persona(String cedula, String nombres, String apellidos, TipoUsuario tipoUsuario) {
        //Agregar validaciones de parametros no nulos
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.activo = true;
        this.tipoUsuario = tipoUsuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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
    
}
