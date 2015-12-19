/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.PuertasDAO;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
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
        validaciones.validarCodigo(idCampus);
        Puerta puerta = new Puerta(ubicacion, id, idCampus);
        puertasDAO.addPuerta(idCampus, puerta);
    }
    /**
     * 
     * @param id
     * @throws CodigoNoValidoException
     * @throws PuertaNoExistenteException
     * @throws CampusNoExistenteException 
     */
    public void delpuerta(String id) throws CodigoNoValidoException, PuertaNoExistenteException, CampusNoExistenteException{
        validaciones.validarCodigo(id);
        puertasDAO.delPuerta(id);
    }
    /**
     * 
     * @param id
     * @return
     * @throws CodigoNoValidoException
     * @throws PuertaNoExistenteException 
     */
    public Puerta getPuerta(String id) throws CodigoNoValidoException, PuertaNoExistenteException{
        validaciones.validarCodigo(id);
        if(puertasDAO.getPuerta(id)==null)
            throw new PuertaNoExistenteException(id);
        return puertasDAO.getPuerta(id);
    }
    /**
     * 
     * @param ubicacion
     * @param id
     * @param idCampus
     * @param activo
     * @throws CodigoNoValidoException
     * @throws PuertaNoExistenteException
     * @throws CampusNoExistenteException 
     */
    public void modPuerta(String ubicacion, String id, String idCampus,boolean activo) throws CodigoNoValidoException, PuertaNoExistenteException, CampusNoExistenteException{
        validaciones.ValidarPuerta(ubicacion, id, idCampus);
        validaciones.validarCodigo(id);
        puertasDAO.modPuerta(id, ubicacion, activo);
    }
    /**
     * 
     * @param idCampus
     * @return
     * @throws CampusNoExistenteException 
     */
    public Set<Puerta> getPuertas(String idCampus) throws CampusNoExistenteException, CodigoNoValidoException{
        validaciones.validarCodigo(idCampus);
        return puertasDAO.getPuertas(idCampus);
    }
    /**
     * 
     * @return 
     */
    public Set<Puerta> getPuertas(){
        return puertasDAO.getPuertas();
    }
}
