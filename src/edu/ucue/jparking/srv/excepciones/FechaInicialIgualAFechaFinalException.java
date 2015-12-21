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
 * @author Santos Gallegos
 */
public class FechaInicialIgualAFechaFinalException extends Exception {
    private final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private final Calendar fecha;
    public FechaInicialIgualAFechaFinalException(Calendar fecha) {
        this.fecha = fecha;
    }

    @Override
    public String getMessage() {
        return "La fecha final y la inicial no pueden ser iguales: " + df.format(fecha.getTime());
    }
}
