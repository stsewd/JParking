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
public class PuertaNoAgregadaException extends Exception {

    public PuertaNoAgregadaException(String idPuerta) {
        super("Puerta de id " + idPuerta + " no se encuentra registrada en el parqueadero.");
    }
    
}
