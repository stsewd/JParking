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
public class PuertaNoExistenteException extends Exception {

    public PuertaNoExistenteException(String id) {
        super("La puerta de id " + id + " no existe.");
    }
    
}
