/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.srv.objetos.OrdenPago;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public interface OrdenesPagoDAOInterface {
    
    /**
     * Agrega una orden de pago al sistema
     * @param ordenPago 
     */
    public void addOrdenPago(OrdenPago ordenPago);
    
    /**
     * Retorna una orden de pago específica, dado
     * su número.
     * @param numeroOrdenPago
     * @return 
     * @throws edu.ucue.jparking.dao.interfaces.OrdenPagoNoExistenteException 
     */
    public OrdenPago getOrdenPago(int numeroOrdenPago)
            throws OrdenPagoNoExistenteException;
    
    /**
     * Retorna todas las órdenes de pago registradas.
     * @return 
     */
    public Set<OrdenPago> getOrdenesPago();
    
    /**
     * Retorna todas las ordenes de pago generadas entre
     * fechaInicial y fechaFinal.
     * @param fechaInicial
     * @param fechaFinal
     * @return 
     */
    public Set<OrdenPago> getOrdenesPago(Calendar fechaInicial, Calendar fechaFinal);
    
    /**
     * Obtiene el valor del dinero recaudado entre todos los fondos.
     * @return 
     */
    public double getFondos();
    
    /**
     * Obtiene el valor del dinero recaudado entre fechaInicial
     * y fechaFinal.
     * @param fechaInicial
     * @param fechaFinal
     * @return 
     */
    public double getFondos(Calendar fechaInicial, Calendar fechaFinal);
}
