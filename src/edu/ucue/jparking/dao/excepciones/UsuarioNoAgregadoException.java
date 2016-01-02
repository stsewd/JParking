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
public class UsuarioNoAgregadoException extends Exception {

    public UsuarioNoAgregadoException(String cedula) {
        super("Usuario con cedula " + cedula + " no se encuentra registrado en el parqueadero.");
    }
    
}
