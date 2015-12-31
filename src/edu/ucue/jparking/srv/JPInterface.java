/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.excepciones.CampusExistenteExeption;

/**
 *
 * @author Santos Gallegos
 */
public interface JPInterface {
    
    /**
     * Agrega un nuevo campus al sistema.
     * @param nombre
     * @param direccion
     * @throws CampusExistenteExeption 
     */
    public void addCampus(String nombre, String direccion)
            throws CampusExistenteExeption;
}
