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
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public class PuertasDAO implements PuertasDAOInterface {
    /*
    //Clase innecesaria?
    //Mapa <Campus, Mapa<Id Parqueadero, Id de puerta>>
    private static Map<String, Map<String, String>> puertasEntrada;
    //Mapa <Campus, Mapa<Id Parqueadero, Id de puerta>>
    private static Map<String, Map<String, String>> puertasSalida;
    
    //Mapa <Id de puerta, puerta>
    private static Map<String, Puerta> puertas;
    */
    
    private static PuertasDAO instance;
    
    private PuertasDAO(){
        /*
        puertas = new HashMap<>();
        puertasEntrada = new HashMap<>();
        puertasSalida = new HashMap<>();
        */
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
    public void delPuerta(String id) throws PuertaNoExistenteException, CampusNoExistenteException {
        Puerta puerta = getPuerta(id);
        if(puerta == null)
            throw new PuertaNoExistenteException(id);
        CampusDAO.getInstancia().getCampus(puerta.getIdCampus()).getPuertas().remove(id);
    }

    @Override
    public Set<Puerta> getPuertas() {
        Set<Puerta> puertas = new HashSet<>();
        for(Campus c : CampusDAO.getInstancia().getCampus()){
            puertas.addAll((Collection<? extends Puerta>) c.getPuertas());
        }
        return puertas;
    }

    @Override
    public Set<Puerta> getPuertas(String nombreCampus) throws CampusNoExistenteException {
        return (Set<Puerta>) CampusDAO.getInstancia().getCampus(nombreCampus).getPuertas().values();
    }

    @Override
    public void modPuerta(String id, String ubicacion, boolean activa) throws PuertaNoExistenteException, CampusNoExistenteException {
        Puerta puerta = getPuerta(id);
        if(puerta == null)
            throw new PuertaNoExistenteException(id);
        puerta.setUbicacion(ubicacion);
        puerta.setActiva(activa);
    }

    @Override
    public Puerta getPuerta(String id) {
        Puerta puerta;
        for(Campus c : CampusDAO.getInstancia().getCampus()){
            puerta = c.getPuertas().get(id);
            if(puerta != null)
                return puerta;
        }
        return null;
    }
}
