/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.CampusExistenteExeption;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoAgregadoException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.srv.objetos.Campus;
import java.io.FileNotFoundException;
import java.io.IOException;
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
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     */
    public void addCampus(Campus campus)
            throws CampusExistenteExeption, IOException, FileNotFoundException, ClassNotFoundException,
            ObjectSizeException;
    
    /**
     * Elimina un campus dado su nombre, si no existe
     * se lanza una excepcion.
     * @param nombre Nombre del campus a eliminar
     * @throws CampusNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.UsuarioNoAgregadoException 
     * @throws java.io.IOException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     */
    public void delCampus(String nombre)
            throws CampusNoExistenteException, ParqueaderoNoExistenteException,
            UsuarioNoExistenteException, UsuarioNoAgregadoException, IOException,
            ClassNotFoundException, ObjectSizeException;
    
    /**
     * Obtiene el campus buscado dado su nombre
     * se laza una excepcion en caso de no existir
     * @param nombre Nombre del campus a obtener
     * @return Campus buscado
     * @throws CampusNoExistenteException
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public Campus getCampus(String nombre)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException;
    
    /**
     * Modifica la ubicacion del campus
     * Se lanza una excepcion si el campus a modificar
     * no existe.
     * @param nombre Nombre del campus a modificar.
     * @param ubicacion Campo a modificar.
     * @throws CampusNoExistenteException 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     */
    public void modCampus(String nombre, String ubicacion, boolean estado)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException,ObjectSizeException;
    
    /**
     * Retorna uns set con todos los campus registrados
     * @return
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public Set<Campus> getCampus()
            throws IOException, FileNotFoundException, ClassNotFoundException;
    
    /**
     * actualiza el mao de campus
     * @param nomrbreCampus
     * @param campus
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws ObjectSizeException 
     */
    public void update(String nomrbreCampus, Campus campus)
            throws IOException, FileNotFoundException, ClassNotFoundException,
            ObjectSizeException;
}
