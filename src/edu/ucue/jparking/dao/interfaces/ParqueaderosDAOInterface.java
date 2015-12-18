/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoYaExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaYaAgregadaException;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Puerta;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public interface ParqueaderosDAOInterface {
    /**
     * Agrega un nuevo parqueadero al campus dado.
     * @param nombreCampus
     * @param parqueadero 
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoYaExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public void addParqueadero(String nombreCampus, Parqueadero parqueadero)
            throws ParqueaderoYaExistenteException, CampusNoExistenteException;
    
    /**
     * Elimina un parqueadero dado su id
     * @param idParqueadero 
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException 
     */
    public void delParqueadero(String idParqueadero)
            throws ParqueaderoNoExistenteException;
    
    /**
     * Obtiene un parqueadero dado su id
     * @param idParqueadero
     * @return Parqueadero
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException
     */
    public Parqueadero getParqueadero(String idParqueadero)
            throws ParqueaderoNoExistenteException;
    
    /**
     * Retorna un set con todos los parqueaderos registrados
     * @return Set de todos los parqueaderos
     */
    public Set<Parqueadero> getParqueaderos();
    
    /**
     * Retorna todos los parqueadero de un campus dado.
     * @param nombreCampus
     * @return Set de todos los parqueaderos de un campus
     */
    public Set<Parqueadero> getParqueaderos(String nombreCampus);
    
    /**
     * Modifica los campos: ubicacion de un parqueadero dado su id.
     * @param idParqueadero
     * @param ubicacion 
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException 
     */
    public void modParqueadero(String idParqueadero, String ubicacion)
            throws ParqueaderoNoExistenteException;
    
    /**
     * Agrega una puerta de entrada a un parqueadero dado su id.
     * @param idParqueadero
     * @param idPuerta 
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.PuertaYaAgregadaException 
     */
    public void addPuertaEntrada(String idParqueadero, String idPuerta)
            throws ParqueaderoNoExistenteException, PuertaNoExistenteException,
            PuertaYaAgregadaException;
    
    
    /**
     * Agrega una puerta de salida a un parqueadero dado su id.
     * @param idParqueadero
     * @param idPuerta 
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.PuertaYaAgregadaException 
     */
    public void addPuertaSalida(String idParqueadero, String idPuerta)
            throws ParqueaderoNoExistenteException, PuertaNoExistenteException,
            PuertaYaAgregadaException;
    
    /**
     * Elimina una puerta de entrada al parqueadero dada su id
     * @param idParqueadero
     * @param idPuerta 
     */
    public void delPuertaEntrada(String idParqueadero, String idPuerta);
    
    /**
     * Elimina una puerta de salida al parqueadero dada su id
     * @param idParqueadero
     * @param idPuerta 
     */
    public void delPuertaSalida(String idParqueadero, String idPuerta);
    
    public Set<Puerta> getPuertasEntrada(String idParqueadero);
    public Set<Puerta> getPuertasSalida(String idParqueadero);
    public Set<Puerta> getPuertas(String idParqueadero);
    
    /**
     * Agrega un usuario dado su cedula a un parqueadero dado su id.
     * @param idParqueadero
     * @param cedula
     */
    public void addUsuario(String idParqueadero, String cedula);
    
    /**
     * Eliminar un usuario de un parqueadero dado su id.
     * @param idParqueadero
     * @param cedula 
     */
    public void delUsuario(String idParqueadero, String cedula);
}
