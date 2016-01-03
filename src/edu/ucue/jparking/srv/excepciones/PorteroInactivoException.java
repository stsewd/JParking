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
public class PorteroInactivoException extends Exception {

    public PorteroInactivoException(String cedula) {
        super("Portero de ceudula: " + cedula + " inactivo, accion no permitida.");
    }
}
