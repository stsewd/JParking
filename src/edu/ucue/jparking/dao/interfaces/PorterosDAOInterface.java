/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.PersonaYaRegistradaComoUsuarioException;
import edu.ucue.jparking.dao.excepciones.PorteroNoExistenteException;
import edu.ucue.jparking.dao.excepciones.*;
import edu.ucue.jparking.srv.objetos.Portero;
import java.io.FileNotFoundException;
import java.io.IOException;
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
     * @throws edu.ucue.jparking.dao.excepciones.PersonaYaRegistradaComoUsuarioException 
     * @throws java.io.IOException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     */
    public void addPortero(String nombreCampus, Portero portero)
            throws CampusNoExistenteException, PorteroYaExistenteException,
            PersonaYaRegistradaComoUsuarioException, IOException, ClassNotFoundException,
            ObjectSizeException;
    
    /**
     * Elimina un portero dado su cedula
     * @param cedula
     * @throws PorteroNoExistenteException
     * @throws CampusNoExistenteException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     */
    public void delPortero(String cedula)
            throws PorteroNoExistenteException, CampusNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException;
    
    /**
     * Modifica el nombre, apellido y estado del portero
     * dado su cedula.
     * @param cedula
     * @param nombres
     * @param apellidos
     * @param direccion
     * @param telefono
     * @param activo
     * @throws PorteroNoExistenteException 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public void modPortero(String cedula, String nombres, String apellidos, String direccion, String telefono, boolean activo)
            throws PorteroNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException,
            CampusNoExistenteException;
    
    /**
     * Obtiene un portero dado su cedula
     * Si el portero no existe retorna null.
     * @param cedula
     * @return
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException
     */
    public Portero getPortero(String cedula)
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException;
    
    /**
     * Obtiene todos los porteros de todos los campus
     * @return 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     */
    public Set<Portero> getPorteros()
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException;
    
    /**
     * Obtiene todos los porteros de un campus
     * dado.
     * @param nombreCampus
     * @return 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     */
    public Set<Portero> getPorteros(String nombreCampus)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException;
    
}
