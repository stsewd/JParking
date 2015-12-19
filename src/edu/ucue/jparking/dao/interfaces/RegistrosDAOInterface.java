/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.dao.excepciones.RegistroNoExistenteException;
import edu.ucue.jparking.srv.enums.TipoRegistro;
import edu.ucue.jparking.srv.registros.Registro;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public interface RegistrosDAOInterface {
    
    /**
     * Agregar nuevo registro
     * @param registro
     */
    public void addRegistro(Registro registro);
    
    /**
     * Retorna un registro dado su id
     * @param numeroRegistro
     * @return 
     * @throws edu.ucue.jparking.dao.excepciones.RegistroNoExistenteException
     */
    public Registro getRegistro(Integer numeroRegistro)
            throws RegistroNoExistenteException;

    /**
     * Obtener registros entre un rango de fechas
     * @param fechaInicial
     * @param fechaFinal
     * @return 
     */
    public Set<Registro> getRegistros(Calendar fechaInicial, Calendar fechaFinal);
    
    /**
     * Obtener todos los registros
     * @return
     */
    public Set<Registro> getRegistros();
    
    /**
     * 
     * @param tipoRegistro
     * @return
     */
    public Set<Registro> getRegistros(TipoRegistro tipoRegistro);
    
    /**
     * 
     * @param tipoRegistro
     * @param fechaInicial
     * @param fechaFinal
     * @return 
     */
    public Set<Registro> getRegistros(TipoRegistro tipoRegistro,Calendar fechaInicial, Calendar fechaFinal);
}
