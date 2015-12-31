/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv.excepciones;

/**
 *
 * @author ESTUDIANTE
 */
public class CedulaNoValidaException extends Exception {

    public CedulaNoValidaException(String cedula) {
        super("La cédula "+cedula+" No es valida");
    }
    
}
