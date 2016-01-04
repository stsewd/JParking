/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

/**
 *
 * @author Santos Gallegos
 */
public class OrdenPagoNoExistenteException extends Exception {

    public OrdenPagoNoExistenteException(int numeroOrdenPago) {
        super("La orden de pago con número: " + numeroOrdenPago + " no existe.");
    }    
}
