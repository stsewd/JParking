/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.PuertaNoAgregadaException;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaYaExistenteException;
import edu.ucue.jparking.dao.interfaces.PuertasDAOInterface;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Puerta;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

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
        /*
        Campus campus = CampusDAO.getInstancia().getCampus(nombreCampus);
        if(campus.getPuerta(puerta.getId()) != null)
            throw new PuertaYaExistenteException(puerta.getId());
        campus.addPuerta(puerta.getId(), puerta);
        */
    }

    @Override
    public void delPuerta(String nombreCampus, String idPuerta)
            throws PuertaNoExistenteException, CampusNoExistenteException,
            ParqueaderoNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        CampusDAO.getInstancia().delPuerta(nombreCampus, idPuerta);
        /*
        Puerta puerta = getPuerta(nombreCampus, idPuerta);
        if(puerta == null)
            throw new PuertaNoExistenteException(idPuerta);
        for(Parqueadero p : ParqueaderosDAO.getInstance().getParqueaderos(nombreCampus)){
            try{
                ParqueaderosDAO.getInstance().delPuertaEntrada(nombreCampus, p.getId(), idPuerta);
            }catch (PuertaNoAgregadaException ex){}
            
            try{
                ParqueaderosDAO.getInstance().delPuertaSalida(nombreCampus, p.getId(), idPuerta);
            }catch (PuertaNoAgregadaException ex){}
        }
        
        puerta.getCampus().delPuerta(idPuerta);
        */
    }

    @Override
    public Set<Puerta> getPuertas()
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        return CampusDAO.getInstancia().getPuertas();
        /*
        Set<Puerta> puertas = new TreeSet<>();
        for(Campus c : CampusDAO.getInstancia().getCampus()){
            puertas.addAll(c.getPuertas());
        }
        return puertas;
        */
    }

    @Override
    public Set<Puerta> getPuertas(String nombreCampus)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        return CampusDAO.getInstancia().getPuertas(nombreCampus);
        /*
        return new TreeSet<>(CampusDAO.getInstancia().getCampus(nombreCampus).getPuertas());
        */
    }

    @Override
    public void modPuerta(String nombreCampus, String id, String ubicacion, boolean activa)
            throws PuertaNoExistenteException, CampusNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        CampusDAO.getInstancia().modPuerta(nombreCampus, id, ubicacion, activa);
        /*
        Puerta puerta = getPuerta(nombreCampus, id);
        if(puerta == null)
            throw new PuertaNoExistenteException(id);
        puerta.setUbicacion(ubicacion);
        puerta.setActiva(activa);
        */
    }

    @Override
    public Puerta getPuerta(String nombreCampus, String id)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        return CampusDAO.getInstancia().getPuerta(nombreCampus, id);
        /*
        Campus campus = CampusDAO.getInstancia().getCampus(nombreCampus);
        Puerta puerta = campus.getPuerta(id);
        return puerta;
        */
    }
}
