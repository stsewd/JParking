/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv.excepciones;

import java.util.Calendar;

/**
 *
 * @author Santos Gallegos
 */
public class FueraDelDiaDePagoException extends Exception {

    public FueraDelDiaDePagoException(int diasContrato) {
        super("Aún no puede cancelar su pago, puede hacerlo luego de " + (diasContrato - 5) + " días de la fecha de contrato");
    }
    
}
