/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.excepciones;

/**
 *
 * @author stsewd
 */
public class RegistroNoExistenteException extends Exception {

    public RegistroNoExistenteException(Integer numero) {
        super("Regitro número " + numero + " no existe.");
    }
    
}
