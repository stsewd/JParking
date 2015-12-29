/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaYaExistenteException;
import edu.ucue.jparking.dao.interfaces.PuertasDAOInterface;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Puerta;
import java.util.Collection;
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
    public void addPuerta(String nombreCampus, Puerta puerta) throws PuertaYaExistenteException, CampusNoExistenteException {
        Campus campus = CampusDAO.getInstancia().getCampus(nombreCampus);
        if(campus.getPuertas().get(puerta.getId()) != null)
            throw new PuertaYaExistenteException(puerta.getId());
        campus.getPuertas().put(puerta.getId(), puerta);
    }

    @Override
    public void delPuerta(String nombreCampus, String id) throws PuertaNoExistenteException, CampusNoExistenteException {
        Puerta puerta = getPuerta(nombreCampus, id);
        if(puerta == null)
            throw new PuertaNoExistenteException(id);
        /*******************************
         * Eliminar dependencias
         * 
        ***********************************/
        CampusDAO.getInstancia().getCampus(puerta.getIdCampus()).getPuertas().remove(id);
    }

    @Override
    public Set<Puerta> getPuertas() {
        Set<Puerta> puertas = new TreeSet<>();
        for(Campus c : CampusDAO.getInstancia().getCampus()){
            puertas.addAll((Collection<? extends Puerta>) c.getPuertas());
        }
        return puertas;
    }

    @Override
    public Set<Puerta> getPuertas(String nombreCampus) throws CampusNoExistenteException {
        return new TreeSet<>(CampusDAO.getInstancia().getCampus(nombreCampus).getPuertas().values());
    }

    @Override
    public void modPuerta(String nombreCampus, String id, String ubicacion, boolean activa) throws PuertaNoExistenteException, CampusNoExistenteException {
        Puerta puerta = getPuerta(nombreCampus, id);
        if(puerta == null)
            throw new PuertaNoExistenteException(id);
        puerta.setUbicacion(ubicacion);
        puerta.setActiva(activa);
    }

    @Override
    public Puerta getPuerta(String nombreCampus, String id) throws CampusNoExistenteException {
        Campus campus = CampusDAO.getInstancia().getCampus(nombreCampus);
        Puerta puerta = campus.getPuertas().get(id);
        return puerta;
    }
}
