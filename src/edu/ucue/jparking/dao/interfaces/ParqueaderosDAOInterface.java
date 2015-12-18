/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.srv.objetos.Parqueadero;
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
     */
    public void addParqueadero(String nombreCampus, Parqueadero parqueadero);
    
    /**
     * Elimina un parqueadero dado su id
     * @param idParqueadero 
     */
    public void delParqueadero(String idParqueadero);
    
    /**
     * Obtiene un parqueadero dado su id
     * @param idParqueadero
     * @return Parqueadero
     */
    public Parqueadero getParqueadero(String idParqueadero);
    
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
     */
    public void modParqueadero(String idParqueadero, String ubicacion);
    
    /**
     * Agrega una puerta de entrada a un parqueadero dado su id.
     * @param idParqueadero
     * @param idPuerta 
     */
    public void addPuertaEntrada(String idParqueadero, String idPuerta);
    
    /**
     * Agrega una puerta de salida a un parqueadero dado su id.
     * @param idParqueadero
     * @param idPuerta 
     */
    public void addPuertaSalida(String idParqueadero, String idPuerta);
    
    /**
     * Elimina una puerta de entrada de un parqueadero dada su id
     * @param idParqueadero
     * @param idPuerta 
     */
    public void delPuertaEntrada(String idParqueadero, String idPuerta);
    
    /**
     * Elimina una puera de salida de un parqueadero dada su id
     * @param idParqueadero
     * @param idPuerta 
     */
    public void delPuertaSalida(String idParqueadero, String idPuerta);
    
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
