/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.excepciones.PorteroYaExistenteException;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PorteroNoExistenteException;
import edu.ucue.jparking.dao.interfaces.PorterosDAOInterface;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Portero;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public class PorterosDAO implements PorterosDAOInterface {
    private static PorterosDAO instance;
    
    private PorterosDAO(){
    }

    public static PorterosDAO getInstance() {
        if(instance == null)
            instance = new PorterosDAO();
        return instance;
    }

    @Override
    public void addPortero(String nombreCampus, Portero portero)
            throws CampusNoExistenteException, PorteroYaExistenteException {
        
        if(getPortero(portero.getCedula()) == null){
            CampusDAO.getInstancia().getCampus(nombreCampus).getPorteros().put(portero.getCedula(), portero);
        }else {
            throw new PorteroYaExistenteException(portero.getCedula());
        }
    }

    @Override
    public void delPortero(String cedula)
            throws PorteroNoExistenteException, CampusNoExistenteException {
        
        Portero portero = getPortero(cedula);
        if(portero == null)
            throw new PorteroNoExistenteException(cedula);
        String campus = portero.getCampus();
        CampusDAO.getInstancia().getCampus(portero.getCampus()).getPorteros().remove(cedula);
    }

    @Override
    public void modPortero(String cedula, String nombres, String apellidos, String direccion, String telefono, boolean activo) throws PorteroNoExistenteException {
        Portero portero = getPortero(cedula);
        if(portero == null)
            throw new PorteroNoExistenteException(cedula);
        portero.setActivo(activo);
        portero.setApellidos(apellidos);
        portero.setNombres(nombres);
        portero.setDireccion(direccion);
        portero.setTelefono(telefono);
    }

    @Override
    public Portero getPortero(String cedula){
        Portero portero;
        for(Campus c : CampusDAO.getInstancia().getCampus()){
            portero = c.getPorteros().get(cedula);
            if(portero != null)
                return portero;
        }
        return null;
    }

    @Override
    public Set<Portero> getPorteros() {
        Set<Portero> porteros = new HashSet<>(); 
        for(Campus c : CampusDAO.getInstancia().getCampus())
            porteros.addAll((Collection<? extends Portero>) c.getPorteros());
        return porteros;
    }

    @Override
    public Set<Portero> getPorteros(String nombreCampus) throws CampusNoExistenteException {
        return new HashSet<>(CampusDAO.getInstancia().getCampus(nombreCampus).getPorteros().values());
    }
}
