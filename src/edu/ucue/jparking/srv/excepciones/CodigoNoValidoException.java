/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv.excepciones;

/**
 *
 * @author Franklin
 */
public class CodigoNoValidoException extends Exception {

    public CodigoNoValidoException(String codigo) {
        super("Codigo no valido no cumple con las restricciones");
    }
    
}
