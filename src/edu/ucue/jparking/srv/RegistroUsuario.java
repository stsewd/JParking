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
public class RegistroUsuario extends Registro {
    private final TipoModificacion tipoModificacion;
    
    public RegistroUsuario(Persona persona, TipoModificacion tipoModificacion) {
        super(persona);
        this.tipoModificacion = tipoModificacion;
    }

    public TipoModificacion getTipoModificacion() {
        return tipoModificacion;
    }
}
