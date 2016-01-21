/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaYaExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.srv.objetos.Puerta;
import java.io.FileNotFoundException;
import java.io.IOException;
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
     * @throws CampusNoExistenteException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     */
    public void addPuerta(String nombreCampus, Puerta puerta)
            throws PuertaYaExistenteException, CampusNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException;
    
    /**
     * Elimina una puerta dado su identificador
     * @param nombreCampus
     * @param idPuerta
     * @throws PuertaNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     */
    public void delPuerta(String nombreCampus, String idPuerta)
            throws PuertaNoExistenteException, CampusNoExistenteException,
            ParqueaderoNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException;
    
    
    /**
     * Retorna una puerta dado su identificador
     * Si la puerta no existe retorna null.
     * @param nombreCampus
     * @param id
     * @return 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     */
    public Puerta getPuerta(String nombreCampus, String id)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException;
    
    /**
     * Obtiene todas las puertas registradas
     * @return 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     */
    public Set<Puerta> getPuertas()
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException;
    
    /**
     * Obtiene todas las puertas de un campus
     * @param nombreCampus
     * @return 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     */
    public Set<Puerta> getPuertas(String nombreCampus)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException;
    
    /**
     * Modifica la ubicacion y estado de una puerta
     * dado su id.
     * @param nombreCampus
     * @param id
     * @param ubicacion
     * @param activa
     * @throws PuertaNoExistenteException
     * @throws CampusNoExistenteException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     */
    public void modPuerta(String nombreCampus, String id, String ubicacion, boolean activa)
            throws PuertaNoExistenteException, CampusNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException;
    
}
