/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import edu.ucue.jparking.srv.enums.TipoUsuario;

/**
 *
 * @author Santos Gallegos
 */
public abstract class Persona {
    
    protected final TipoUsuario tipoUsuario;
    
    private final String cedula;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private boolean activo;

    
    /**
     * 
     * @param cedula Cédula de la persona
     * @param nombres Nombre(s) de la persona
     * @param apellidos Apellido(s) de la persona
     * @param direccion direccion de la persona
     * @param telefono telefono de la persona
     * @param tipoUsuario
     */
    public Persona(String cedula, String nombres, String apellidos, String direccion, String telefono,TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.activo = activo;
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

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
