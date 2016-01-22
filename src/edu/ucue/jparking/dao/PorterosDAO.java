/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.PersonaYaRegistradaComoUsuarioException;
import edu.ucue.jparking.dao.excepciones.PorteroYaExistenteException;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PorteroNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.interfaces.PorterosDAOInterface;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Portero;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

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
            throws CampusNoExistenteException, PorteroYaExistenteException,PersonaYaRegistradaComoUsuarioException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        CampusDAO.getInstancia().addPortero(nombreCampus, portero);
        /*
        if(getPortero(portero.getCedula()) != null)
            throw new PorteroYaExistenteException(portero.getCedula());
        try{
            UsuariosDAO.getInstance().getUsuario(portero.getCedula());
            throw new PersonaYaRegistradaComoUsuarioException(portero.getCedula());
        }catch (UsuarioNoExistenteException ex){
            CampusDAO.getInstancia().getCampus(nombreCampus).addPortero(portero.getCedula(), portero);
        }
        */
    }

    @Override
    public void delPortero(String cedula)
            throws PorteroNoExistenteException, CampusNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        CampusDAO.getInstancia().delPortero(cedula);
        /*
        Portero portero = getPortero(cedula);
        if(portero == null)
            throw new PorteroNoExistenteException(cedula);
        portero.getCampus().delPortero(cedula);
        */
    }

    @Override
    public void modPortero(String cedula, String nombres, String apellidos, String direccion, String telefono, boolean activo)
            throws PorteroNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException, CampusNoExistenteException
    {
        CampusDAO.getInstancia().modPortero(cedula, nombres, apellidos, direccion, telefono, activo);
        /*
        Portero portero = getPortero(cedula);
        if(portero == null)
            throw new PorteroNoExistenteException(cedula);
        portero.setActivo(activo);
        portero.setApellidos(apellidos);
        portero.setNombres(nombres);
        portero.setDireccion(direccion);
        portero.setTelefono(telefono);
        */
    }

    @Override
    public Portero getPortero(String cedula)
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        return CampusDAO.getInstancia().getPortero(cedula);
        /*
        Portero portero;
        for(Campus c : CampusDAO.getInstancia().getCampus()){
            portero = c.getPortero(cedula);
            if(portero != null)
                return portero;
        }
        return null;
        */
    }

    @Override
    public Set<Portero> getPorteros()
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
       return CampusDAO.getInstancia().getPorteros();
        /*
        Set<Portero> porteros = new TreeSet<>(); 
        for(Campus c : CampusDAO.getInstancia().getCampus())
            porteros.addAll(c.getPorteros());
        return porteros;
        */
    }

    @Override
    public Set<Portero> getPorteros(String nombreCampus)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        return CampusDAO.getInstancia().getPorteros(nombreCampus);
        /*
        return new TreeSet<>(CampusDAO.getInstancia().getCampus(nombreCampus).getPorteros());
        */
    }
}
