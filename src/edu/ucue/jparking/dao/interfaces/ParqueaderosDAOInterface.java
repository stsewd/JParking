/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.PuertaNoAgregadaException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoAgregadoException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaAgregadoException;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoYaExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaYaAgregadaException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Puerta;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.io.IOException;
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
     * @param nombreCampus
     * @param idParqueadero 
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.UsuarioNoAgregadoException 
     * @throws java.io.IOException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     */
    public void delParqueadero(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, CampusNoExistenteException,
            UsuarioNoExistenteException, UsuarioNoAgregadoException, IOException,
            ClassNotFoundException, ObjectSizeException;
    
    /**
     * Obtiene un parqueadero dado su id
     * @param nombreCampus
     * @param idParqueadero
     * @return Parqueadero
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException
     */
    public Parqueadero getParqueadero(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, CampusNoExistenteException;
    
    /**
     * Retorna un set con todos los parqueaderos registrados
     * @return Set de todos los parqueaderos
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException
     */
    public Set<Parqueadero> getParqueaderos()
            throws CampusNoExistenteException;
    
    /**
     * Retorna todos los parqueadero de un campus dado.
     * @param nombreCampus
     * @return Set de todos los parqueaderos de un campus
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException
     */
    public Set<Parqueadero> getParqueaderos(String nombreCampus)
            throws CampusNoExistenteException;
    
    /**
     * Modifica los campos: ubicacion de un parqueadero dado su id.
     * @param nombreCampus
     * @param idParqueadero
     * @param ubicacion 
     * @param numLugares 
     * @param estado 
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public void modParqueadero(String nombreCampus, String idParqueadero, String ubicacion, int numLugares, boolean estado)
            throws ParqueaderoNoExistenteException, CampusNoExistenteException;
    
    /**
     * Agrega una puerta de entrada a un parqueadero dado su id.
     * @param nombreCampus
     * @param idParqueadero
     * @param idPuerta 
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.PuertaYaAgregadaException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public void addPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta)
            throws ParqueaderoNoExistenteException, PuertaNoExistenteException,
            PuertaYaAgregadaException, CampusNoExistenteException;
    
    
    /**
     * Agrega una puerta de salida a un parqueadero dado su id.
     * @param nombreCampus
     * @param idParqueadero
     * @param idPuerta 
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.PuertaYaAgregadaException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public void addPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta)
            throws ParqueaderoNoExistenteException, PuertaNoExistenteException,
            PuertaYaAgregadaException, CampusNoExistenteException;
    
    /**
     * Elimina una puerta de entrada al parqueadero dada su id
     * @param nombreCampus
     * @param idParqueadero
     * @param idPuerta 
     * @throws edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.PuertaNoAgregadaException 
     */
    public void delPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta)
            throws PuertaNoExistenteException, ParqueaderoNoExistenteException,
            CampusNoExistenteException, PuertaNoAgregadaException;
    
    /**
     * Elimina una puerta de salida al parqueadero dada su id
     * @param nombreCampus
     * @param idParqueadero
     * @param idPuerta 
     * @throws edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.PuertaNoAgregadaException 
     */
    public void delPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta)
            throws PuertaNoExistenteException, ParqueaderoNoExistenteException,
            CampusNoExistenteException, PuertaNoAgregadaException;
    
    /**
     * Retorna un set con todas las puertas de entrada de un
     * parqueadero dado su id.
     * @param nombreCampus
     * @param idParqueadero
     * @return
     * @throws ParqueaderoNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public Set<Puerta> getPuertasEntrada(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, CampusNoExistenteException;
    
    /**
     * Retorna un set con todas las puertas de salida de un
     * parqueadero dado su id.
     * @param nombreCampus
     * @param idParqueadero
     * @return
     * @throws ParqueaderoNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public Set<Puerta> getPuertasSalida(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, CampusNoExistenteException;
    
    /**
     * Agrega un usuario dado su cedula a un parqueadero dado su id.
     * @param nombreCampus
     * @param idParqueadero
     * @param cedula
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException
     * @throws edu.ucue.jparking.dao.excepciones.UsuarioYaAgregadoException
     * @throws edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException
     */
    public void addUsuario(String nombreCampus, String idParqueadero, String cedula)
            throws ParqueaderoNoExistenteException, UsuarioYaAgregadoException,
            UsuarioNoExistenteException, CampusNoExistenteException, IOException,
            ClassNotFoundException, ObjectSizeException;
    
    /**
     * Eliminar un usuario de un parqueadero dado su id.
     * @param nombreCampus
     * @param idParqueadero
     * @param cedula 
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.UsuarioNoAgregadoException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     * @throws java.io.IOException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     */
    public void delUsuario(String nombreCampus, String idParqueadero, String cedula)
            throws ParqueaderoNoExistenteException, UsuarioNoExistenteException,
            UsuarioNoAgregadoException, CampusNoExistenteException, IOException,
            ClassNotFoundException, ObjectSizeException;
    
    /**
     * Retorna un set con todos los usuarios pertenecientes a un
     * parqueadero dado su id.
     * @param nombreCampus
     * @param idParqueadero
     * @return
     * @throws ParqueaderoNoExistenteException
     * @throws UsuarioNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public Set<Usuario> getUsuarios(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, UsuarioNoExistenteException,
            CampusNoExistenteException;
}
