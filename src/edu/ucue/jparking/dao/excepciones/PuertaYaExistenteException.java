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
public class PuertaYaExistenteException extends Exception {

    public PuertaYaExistenteException(String id) {
        super("Puerta de id: " + id + " ya se encuentra registrada.");
    }
}
