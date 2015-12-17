/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaYaExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.srv.objetos.Puerta;
import java.util.Set;

/**
 *
 * @author Franklin Lara
 */
public interface PuertasDAOInterface {
    
    /**
     * Agregar puerta a un campus
     * @param nombreCampus
     * @param puerta
     * @throws PuertaYaExistenteException 
     */
    public abstract void addPuerta(String nombreCampus, Puerta puerta)
            throws PuertaYaExistenteException;
    
    /**
     * Elimina una puerta dado su identificador
     * @param id
     * @throws PuertaNoExistenteException 
     */
    public abstract void delPuerta(String id)
            throws PuertaNoExistenteException;
    
    /**
     * Obtiene todas las puertas registradas
     * @return 
     */
    public abstract Set<Puerta> getPuertas();
    
    /**
     * Obtiene todas las puertas de un campus
     * @param nombreCampus
     * @return 
     */
    public abstract Set<Puerta> getPuertas(String nombreCampus);
    
    /**
     * Modifica la ubicacion y estado de una puerta
     * dado su id.
     * @param id
     * @param ubicacion
     * @param activa
     * @throws PuertaNoExistenteException
     * @throws CampusNoExistenteException 
     */
    public abstract void modPuerta(String id, String ubicacion, boolean activa)
            throws PuertaNoExistenteException, CampusNoExistenteException;
}
