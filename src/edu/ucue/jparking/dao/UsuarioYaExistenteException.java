/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

/**
 *
 * @author stsewd
 */
public class UsuarioYaExistenteException extends Exception {

    public UsuarioYaExistenteException(String cedula) {
        super("El usuario con cédula: " +  cedula + " ya existe.");
    }
    
}
