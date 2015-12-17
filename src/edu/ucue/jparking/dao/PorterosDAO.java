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
//import java.util.HashMap;
//import java.util.Map;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public class PorterosDAO implements PorterosDAOInterface {
    //Mapa <Clave de campus, Mapa<Cedula, portero>>
    //private static Map<String, Portero> porteros;
    private static PorterosDAO instance;
    
    private PorterosDAO(){
        //porteros = new HashMap<>();
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
        }else{
            throw new PorteroYaExistenteException(portero.getCedula());
        }
    }

    @Override
    public void delPortero(String cedula)
            throws PorteroNoExistenteException, CampusNoExistenteException {
        
        Portero portero = getPortero(cedula);
        if(portero == null)
            throw new PorteroNoExistenteException(cedula);
        CampusDAO.getInstancia().getCampus(portero.getCampus()).getPorteros().remove(cedula);
    }

    @Override
    public void modPortero(String cedula, String nombres, String apellidos, boolean activo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Portero> getPorteros(String nombreCampus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
