/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv.excepciones;

/**
 *
 * @author lara
 */
public class PuertaInactivaException extends Exception {

    public PuertaInactivaException(String idpuerta) {
        super("Puerta inactiva, acción no realizada.");
    }
    
}
