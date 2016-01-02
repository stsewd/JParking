/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.excepciones;

/**
 *
 * @author Santos Gallegos
 */
public class PorteroNoExistenteException extends Exception {

    public PorteroNoExistenteException(String cedula) {
        super("Portero de cedula " + cedula + " no se encuentra registrado.");
    }
    
}
