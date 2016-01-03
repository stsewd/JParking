/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv.excepciones;

/**
 *
 * @author Santos Gallegos
 */
public class PagoNoCanceladoException extends Exception {

    public PagoNoCanceladoException(String cedula) {
        super("Acceso denegado. Usuario de cedula " + cedula + " no ha cancelado su pago.");
    }
    
}
