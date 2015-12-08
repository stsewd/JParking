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
class PagoYaRealizadoException extends Exception {

    public PagoYaRealizadoException(String cedula) {
        super("El usuario con cédula: " + cedula + " ya ha realizado el pago.");
    }
    
}
