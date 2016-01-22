/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.PuertasDAO;
import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaYaExistenteException;
import edu.ucue.jparking.dao.interfaces.PuertasDAOInterface;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Puerta;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

/**
 *
 * @author Franklin
 */
class PuertaService {
    PuertasDAOInterface puertasDAO = PuertasDAO.getInstance();
    Validaciones validaciones = new Validaciones();
    CampusService campusService;

    public PuertaService() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        campusService = new CampusService();
    }
    
    public void addpuerta(String ubicacion, String id, String idCampus)
            throws CodigoNoValidoException, PuertaYaExistenteException, CampusNoExistenteException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        validaciones.ValidarPuerta(ubicacion, id, idCampus);
        validaciones.validarCodigo(id);
        Campus campus = campusService.getCampus(idCampus);
        Puerta puerta = new Puerta(ubicacion, id, campus);
        puertasDAO.addPuerta(idCampus, puerta);
        campusService = new CampusService();
    }
    
    public void delpuerta(String nombreCampus, String id)
            throws CodigoNoValidoException, PuertaNoExistenteException,
            CampusNoExistenteException, ParqueaderoNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        validaciones.validarCodigo(id);
        puertasDAO.delPuerta(nombreCampus, id);
    }
    
    public Puerta getPuerta(String nombreCampus, String id)
            throws CodigoNoValidoException, PuertaNoExistenteException,
            CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        validaciones.validarCodigo(id);
        if(puertasDAO.getPuerta(nombreCampus, id) == null)
            throw new PuertaNoExistenteException(id);
        return puertasDAO.getPuerta(nombreCampus, id);
    }
    
    public void modPuerta(String nombreCampus, String id, String ubicacion, boolean activo)
            throws CodigoNoValidoException, PuertaNoExistenteException, CampusNoExistenteException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        validaciones.ValidarPuerta(ubicacion, id, nombreCampus);
        validaciones.validarCodigo(id);
        puertasDAO.modPuerta(nombreCampus, id, ubicacion, activo);
    }
    
    public Set<Puerta> getPuertas(String nombreCampus)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El argumento campus no puede ser nulo.");
        return puertasDAO.getPuertas(nombreCampus);
    }
    
    public Set<Puerta> getPuertas()
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        return puertasDAO.getPuertas();
    }
}
