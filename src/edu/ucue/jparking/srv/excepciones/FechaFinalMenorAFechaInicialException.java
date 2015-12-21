/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv.excepciones;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author stsewd
 */
public class FechaFinalMenorAFechaInicialException extends Exception {
    private final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private final Calendar fechaInicio;
    private final Calendar fechaFinal;
    public FechaFinalMenorAFechaInicialException(Calendar fechaInicio, Calendar fechaFinal) {
        this.fechaFinal = fechaFinal;
        this.fechaInicio = fechaInicio;
    }
    
    @Override
    public String getMessage() {
        return "La fecha final " + df.format(fechaFinal.getTime()) + " es menor a la inicial: " +df.format(fechaInicio.getTime());
    }
    
}
