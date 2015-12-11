/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

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
    
}
