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
public class AccesoNoAutorizadoException extends Exception {

    public AccesoNoAutorizadoException(String cedula, String tipoUsuarioString, String campus, String idPuerta) {
        super("El " + tipoUsuarioString + " de cedula " + cedula + " no tiene permitido el acceso a la puerta " + idPuerta + " del campus " + campus);
    }
    
}
