/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.PuertasDAO;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaYaExistenteException;
import edu.ucue.jparking.dao.interfaces.PuertasDAOInterface;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.objetos.Puerta;
import java.util.Set;

/**
 *
 * @author Franklin
 */
public class PuertaService {
    PuertasDAOInterface puertasDAO = PuertasDAO.getInstance();
    Validaciones validaciones = new Validaciones();
    
    /**
     * 
     * @param ubicacion
     * @param id
     * @param idCampus
     * @throws CodigoNoValidoException 
     */
    public void addpuerta(String ubicacion, String id, String idCampus) throws CodigoNoValidoException, PuertaYaExistenteException, CampusNoExistenteException{
        validaciones.ValidarPuerta(ubicacion, id, idCampus);
        validaciones.validarCodigo(id);
        Puerta puerta = new Puerta(ubicacion, id, idCampus);
        puertasDAO.addPuerta(idCampus, puerta);
    }
    
    /**
     * 
     * @param nombreCampus
     * @param id
     * @throws CodigoNoValidoException
     * @throws PuertaNoExistenteException
     * @throws CampusNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException 
     */
    public void delpuerta(String nombreCampus, String id)
            throws CodigoNoValidoException, PuertaNoExistenteException,
            CampusNoExistenteException, ParqueaderoNoExistenteException
    {
        validaciones.validarCodigo(id);
        puertasDAO.delPuerta(nombreCampus, id);
    }
    
    /**
     * 
     * @param nombreCampus
     * @param id
     * @return
     * @throws CodigoNoValidoException
     * @throws PuertaNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public Puerta getPuerta(String nombreCampus, String id)
            throws CodigoNoValidoException, PuertaNoExistenteException,
            CampusNoExistenteException
    {
        validaciones.validarCodigo(id);
        if(puertasDAO.getPuerta(nombreCampus, id) == null)
            throw new PuertaNoExistenteException(id);
        return puertasDAO.getPuerta(nombreCampus, id);
    }
    
    /**
     * 
     * @param nombreCampus
     * @param ubicacion
     * @param id
     * @param activo
     * @throws CodigoNoValidoException
     * @throws PuertaNoExistenteException
     * @throws CampusNoExistenteException 
     */
    public void modPuerta(String nombreCampus, String id, String ubicacion, boolean activo) throws CodigoNoValidoException, PuertaNoExistenteException, CampusNoExistenteException{
        validaciones.ValidarPuerta(ubicacion, id, nombreCampus);
        validaciones.validarCodigo(id);
        puertasDAO.modPuerta(nombreCampus, id, ubicacion, activo);
    }
    
    /**
     * 
     * @param nombreCampus
     * @return
     * @throws CampusNoExistenteException 
     */
    public Set<Puerta> getPuertas(String nombreCampus) throws CampusNoExistenteException{
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El argumento campus no puede ser nulo.");
        return puertasDAO.getPuertas(nombreCampus);
    }
    
    /**
     * 
     * @return 
     */
    public Set<Puerta> getPuertas(){
        return puertasDAO.getPuertas();
    }
}
