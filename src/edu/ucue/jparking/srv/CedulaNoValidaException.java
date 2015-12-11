/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

/**
 *
 * @author ESTUDIANTE
 */
class CedulaNoValidaException extends Exception {

    public CedulaNoValidaException(String cedula) {
        super("La cedula "+cedula+" No es valida");
    }
    
}
