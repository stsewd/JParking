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
public class ContratoNoEstablecidoException extends Exception {

    public ContratoNoEstablecidoException(String cedula) {
        super("El usuario de cedula: " + cedula + " no tiene un contrato valido.");
    }
}
