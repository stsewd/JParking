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
public class ParqueaderoNoExistenteException extends Exception {

    public ParqueaderoNoExistenteException(String idParqueadero) {
        super("Parqueadero de id " + idParqueadero + " no existe.");
    }
    
}
