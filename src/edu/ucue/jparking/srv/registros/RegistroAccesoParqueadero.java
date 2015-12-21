/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv.registros;

import edu.ucue.jparking.srv.objetos.Persona;
import edu.ucue.jparking.srv.enums.TipoRegistro;
import edu.ucue.jparking.srv.enums.TipoAcceso;

/**
 *
 * @author Santos Gallegos
 */
public class RegistroAccesoParqueadero extends Registro {
    
    private final TipoAcceso tipoAcceso;
    
    public RegistroAccesoParqueadero(Persona persona, TipoAcceso tipoAcceso) {
        super(persona, TipoRegistro.ACCESO_PARQUEADERO);
        this.tipoAcceso = tipoAcceso;
    }

    /**
     * @return the tipoAcceso
     */
    public TipoAcceso getTipoAcceso() {
        return tipoAcceso;
    }

    @Override
    public String getTipoAccionString() {
        return tipoAcceso.toString();
    }
    
}
