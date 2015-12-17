/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.dao.excepciones.PorteroNoExistenteException;
import edu.ucue.jparking.dao.excepciones.*;
import edu.ucue.jparking.srv.objetos.Portero;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public interface PorterosDAOInterface {
    
    /**
     * Agrega un portero a un campus
     * @param nombreCampus
     * @param portero
     * @throws CampusNoExistenteException
     * @throws PorteroYaExistenteException 
     */
    public void addPortero(String nombreCampus, Portero portero)
            throws CampusNoExistenteException, PorteroYaExistenteException;
    
    /**
     * Elimina un portero dado su cedula
     * @param cedula
     * @throws PorteroNoExistenteException
     * @throws CampusNoExistenteException 
     */
    public void delPortero(String cedula)
            throws PorteroNoExistenteException, CampusNoExistenteException;
    
    /**
     * Modifica el nombre, apellido y estado del portero
     * dado su cedula.
     * @param cedula
     * @param nombres
     * @param apellidos
     * @param activo
     * @throws PorteroNoExistenteException 
     */
    public void modPortero(String cedula, String nombres, String apellidos, boolean activo)
            throws PorteroNoExistenteException;
    
    /**
     * Obtiene un portero dado su cedula.
     * @param cedula
     * @return
     */
    public Portero getPortero(String cedula);
    
    /**
     * Obtiene todos los porteros de todos los campus
     * @return 
     */
    public Set<Portero> getPorteros();
    
    /**
     * Obtiene todos los porteros de un campus
     * dado.
     * @param nombreCampus
     * @return 
     */
    public Set<Portero> getPorteros(String nombreCampus) throws CampusNoExistenteException;
}
