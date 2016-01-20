/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv.objetos.registros;

import edu.ucue.jparking.srv.objetos.Persona;
import edu.ucue.jparking.srv.enums.TipoRegistro;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Santos Gallegos
 */
public abstract class Registro implements Serializable {
    
    private final TipoRegistro tipoRegistro;
    private final Calendar fecha;
    private int numeroRegistro;
    
    private final TipoUsuario tipoPersona;
    private final String cedulaPersona;
    private final String nombresPersona;
    private final String apellidosPersona;
    private final String direccionPersona;
    private final String telefonoPersona;
    private final boolean activoPersona;

    public Registro(Persona persona, TipoRegistro tipoRegistro) {
        this.fecha = Calendar.getInstance();
        this.cedulaPersona = persona.getCedula();
        this.nombresPersona = persona.getNombres();
        this.apellidosPersona = persona.getApellidos();
        this.activoPersona = persona.isActivo();
        this.tipoPersona = persona.getTipoUsuario();
        this.telefonoPersona = persona.getTelefono();
        this.direccionPersona = persona.getDireccion();
        this.tipoRegistro = tipoRegistro;
    }

    public TipoRegistro getTipoRegistro() {
        return tipoRegistro;
    }

    /**
     * @return the fecha
     */
    public Calendar getFecha() {
        return fecha;
    }

    /**
     * @return the cedulaPersona
     */
    public String getCedulaPersona() {
        return cedulaPersona;
    }

    /**
     * @return the nombresPersona
     */
    public String getNombresPersona() {
        return nombresPersona;
    }

    /**
     * @return the apellidosPersona
     */
    public String getApellidosPersona() {
        return apellidosPersona;
    }


    /**
     * @return the activoPersona
     */
    public boolean isActivoPersona() {
        return activoPersona;
    }   
    
    
    /**
     * 
     * @return 
     */
    public int getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(int numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getTipoRegistroString() {
        return tipoRegistro.toString();
    }

    public abstract String getTipoAccionString();

    /**
     * @return the tipoPersona
     */
    public TipoUsuario getTipoPersona() {
        return tipoPersona;
    }

    /**
     * @return the direccionPersona
     */
    public String getDireccionPersona() {
        return direccionPersona;
    }

    /**
     * @return the telefonoPersona
     */
    public String getTelefonoPersona() {
        return telefonoPersona;
    }
}
