/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaYaExistenteException;
import edu.ucue.jparking.dao.interfaces.PuertasDAOInterface;
import edu.ucue.jparking.srv.objetos.Puerta;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public class PuertasDAO implements PuertasDAOInterface {
    
    private static PuertasDAO instance;
    
    private PuertasDAO(){
    }

    public static PuertasDAO getInstance() {
        if(instance == null)
            instance = new PuertasDAO();
        return instance;
    }

    @Override
    public void addPuerta(String nombreCampus, Puerta puerta)
            throws PuertaYaExistenteException, CampusNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        CampusDAO.getInstancia().addPuerta(nombreCampus, puerta);
    }

    @Override
    public void delPuerta(String nombreCampus, String idPuerta)
            throws PuertaNoExistenteException, CampusNoExistenteException,
            ParqueaderoNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        CampusDAO.getInstancia().delPuerta(nombreCampus, idPuerta);
    }

    @Override
    public Set<Puerta> getPuertas()
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        return CampusDAO.getInstancia().getPuertas();
    }

    @Override
    public Set<Puerta> getPuertas(String nombreCampus)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        return CampusDAO.getInstancia().getPuertas(nombreCampus);
    }

    @Override
    public void modPuerta(String nombreCampus, String id, String ubicacion, boolean activa)
            throws PuertaNoExistenteException, CampusNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        CampusDAO.getInstancia().modPuerta(nombreCampus, id, ubicacion, activa);
    }

    @Override
    public Puerta getPuerta(String nombreCampus, String id)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        return CampusDAO.getInstancia().getPuerta(nombreCampus, id);
    }
}
