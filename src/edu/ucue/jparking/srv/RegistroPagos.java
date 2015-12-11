/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

/**
 *
 * @author stsewd
 */
public class RegistroPagos extends Registro {
    private final TipoTramite tipoTramite;
    public RegistroPagos(Persona persona, TipoTramite tipoTramite) {
        super(persona);
        this.tipoTramite = tipoTramite;
    }

    public TipoTramite getTipoTramite() {
        return tipoTramite;
    }    
}
