/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv.registros;

import edu.ucue.jparking.srv.objetos.Persona;
import edu.ucue.jparking.srv.enums.TipoRegistro;
import edu.ucue.jparking.srv.enums.TipoTramite;

/**
 *
 * @author Santos Gallegos
 */
public class RegistroPagos extends Registro {
    private final TipoTramite tipoTramite;
    public RegistroPagos(Persona persona, TipoTramite tipoTramite) {
        super(persona, TipoRegistro.PAGOS);
        this.tipoTramite = tipoTramite;
    }

    public TipoTramite getTipoTramite() {
        return tipoTramite;
    }    

    @Override
    public String getTipoAccionString() {
        return tipoTramite.toString();
    }
}
