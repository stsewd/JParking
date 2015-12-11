/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

/**
 *
 * @author Santos Gallegos
 */
public class UsuarioNoExistenteException extends Exception {

    public UsuarioNoExistenteException(String cedula) {
        super("El usuario de cedula: " + cedula + " no existe.");
    }
    
}
