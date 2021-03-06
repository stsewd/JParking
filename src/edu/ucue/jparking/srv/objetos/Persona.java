/**
 * 
 */
package edu.ucue.jparking.srv.objetos;

import edu.ucue.jparking.srv.enums.TipoAcceso;
import edu.ucue.jparking.srv.enums.TipoModificacion;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.objetos.registros.Registro;
import edu.ucue.jparking.srv.objetos.registros.RegistroAccesoParqueadero;
import edu.ucue.jparking.srv.objetos.registros.RegistroUsuario;
import java.io.Serializable;

/**
 *
 * @author Santos Gallegos
 */
public abstract class Persona implements Comparable<Persona>, Serializable {
    
    protected final TipoUsuario tipoUsuario;
    
    // ABC -> segundo primero, medio medio, primero ultimo. Quién es?
    
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
    public Persona(String cedula, String nombres, String apellidos, String direccion, String telefono, TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.activo = true;
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
    
    public String getTipoUsuarioString(){
        return tipoUsuario.toString();
    }
    
    public Registro getRegistro(TipoAcceso tipoAcceso){
        Registro registro = null;
        registro = new RegistroAccesoParqueadero(this, tipoAcceso);
        return registro;
    }
    
    public Registro getRegistro(TipoModificacion tipoModificacion){
        Registro registro;
        registro = new RegistroUsuario(this, tipoModificacion);
        return registro;
    }
    
    @Override
    public int compareTo(Persona o) {
        return (getApellidos() + getNombres() + getCedula()).compareToIgnoreCase(o.getApellidos() + o.getNombres() + o.getCedula());
    }
}
