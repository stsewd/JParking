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
        super("Código no valido. El código debe tener una letra mayúscula seguida de 2 números.");
    }
    
}
