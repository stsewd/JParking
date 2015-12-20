/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.dao.excepciones.CampusExistenteExeption;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.srv.objetos.Campus;
import java.util.Set;

/**
 *
 * @author Franklin Lara
 */
public interface CampusDAOInterface {
    
    /**
     * Agregar nuevo campus
     * Se lanza una excepcion en caso de que el
     * campus ya exista.
     * @param campus Campus a agregar
     * @throws CampusExistenteExeption 
     */
    public abstract void addCampus(Campus campus)
            throws CampusExistenteExeption;
    
    /**
     * Elimina un campus dado su nombre, si no existe
     * se lanza una excepcion.
     * @param nombre Nombre del campus a eliminar
     * @throws CampusNoExistenteException 
     */
    public abstract void delCampus(String nombre)
            throws CampusNoExistenteException;
    
    /**
     * Obtiene el campus buscado dado su nombre
     * se laza una excepcion en caso de no existir
     * @param nombre Nombre del campus a obtener
     * @return Campus buscado
     * @throws CampusNoExistenteException
     */
    public abstract Campus getCampus(String nombre)
            throws CampusNoExistenteException;
    
    /**
     * Modifica la ubicacion del campus
     * Se lanza una excepcion si el campus a modificar
     * no existe.
     * @param nombre Nombre del campus a modificar.
     * @param ubicacion Campo a modificar.
     * @throws CampusNoExistenteException 
     */
    public abstract void modCampus(String nombre, String ubicacion,boolean estado) 
            throws CampusNoExistenteException;
    
    /**
     * Retorna uns set con todos los campus registrados
     * @return
     */
    public abstract Set<Campus> getCampus();
}
