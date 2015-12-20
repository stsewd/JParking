/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv.excepciones;

/**
 *
 * @author stsewd
 */
public class NumeroLugaresDeParqueoInsuficientesException extends Exception {

    public NumeroLugaresDeParqueoInsuficientesException(int numUsuarios) {
        super("Numero de lugares de parqueo insufiente para " + numUsuarios + "usuarios registrados.");
    }
    
}
