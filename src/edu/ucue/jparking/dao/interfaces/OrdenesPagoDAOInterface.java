/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.dao.excepciones.OrdenPagoNoExistenteException;
import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.srv.objetos.OrdenPago;
import java.io.FileNotFoundException;
import java.io.IOException;
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
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     */
    public void addOrdenPago(OrdenPago ordenPago)
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException;
    
    /**
     * Retorna una orden de pago específica, dado
     * su número.
     * @param numeroOrdenPago
     * @return 
     * @throws edu.ucue.jparking.dao.interfaces.OrdenPagoNoExistenteException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     */
    public OrdenPago getOrdenPago(int numeroOrdenPago)
            throws OrdenPagoNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException;
    
    /**
     * Retorna todas las órdenes de pago registradas.
     * @return 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     */
    public Set<OrdenPago> getOrdenesPago()
            throws IOException, FileNotFoundException, ClassNotFoundException;
    
    /**
     * Retorna todas las ordenes de pago generadas entre
     * fechaInicial y fechaFinal.
     * @param fechaInicial
     * @param fechaFinal
     * @return 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     */
    public Set<OrdenPago> getOrdenesPago(Calendar fechaInicial, Calendar fechaFinal)
            throws IOException, FileNotFoundException, ClassNotFoundException;
    
    /**
     * Obtiene el valor del dinero recaudado entre todos los fondos.
     * @return 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     */
    public double getFondos() throws IOException, FileNotFoundException, ClassNotFoundException;
    
    /**
     * Obtiene el valor del dinero recaudado entre fechaInicial
     * y fechaFinal.
     * @param fechaInicial
     * @param fechaFinal
     * @return 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     */
    public double getFondos(Calendar fechaInicial, Calendar fechaFinal)
            throws IOException, FileNotFoundException, ClassNotFoundException;
}
