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
import edu.ucue.jparking.dao.interfaces.PorterosDAOInterface;
import edu.ucue.jparking.srv.objetos.Portero;
import java.io.FileNotFoundException;
import java.io.IOException;
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
            throws CampusNoExistenteException, PorteroYaExistenteException,PersonaYaRegistradaComoUsuarioException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        CampusDAO.getInstancia().addPortero(nombreCampus, portero);
    }

    @Override
    public void delPortero(String cedula)
            throws PorteroNoExistenteException, CampusNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        CampusDAO.getInstancia().delPortero(cedula);
    }

    @Override
    public void modPortero(String cedula, String nombres, String apellidos, String direccion, String telefono, boolean activo)
            throws PorteroNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException, CampusNoExistenteException
    {
        CampusDAO.getInstancia().modPortero(cedula, nombres, apellidos, direccion, telefono, activo);
    }

    @Override
    public Portero getPortero(String cedula)
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        return CampusDAO.getInstancia().getPortero(cedula);
    }

    @Override
    public Set<Portero> getPorteros()
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
       return CampusDAO.getInstancia().getPorteros();
    }

    @Override
    public Set<Portero> getPorteros(String nombreCampus)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        return CampusDAO.getInstancia().getPorteros(nombreCampus);
    }
}
