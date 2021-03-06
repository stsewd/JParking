/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.PorterosDAO;
import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PersonaYaRegistradaComoUsuarioException;
import edu.ucue.jparking.dao.excepciones.PorteroNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PorteroYaExistenteException;
import edu.ucue.jparking.dao.interfaces.PorterosDAOInterface;
import edu.ucue.jparking.srv.enums.TipoModificacion;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.TelefonoNoValidoException;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Portero;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

/**
 *
 * @author Franklin Lara
 */
class PorterosService {
    Validaciones validar = new Validaciones();
    PorterosDAOInterface porterosDAO = PorterosDAO.getInstance();
    RegistroService registroService;
    CampusService campusService;

    public PorterosService() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        registroService = new RegistroService();
        campusService  = new CampusService();
    }

    public void addPortero(String nombreCampus, String cedula, String nombre, String apellido, String direccion, String telefono) 
            throws CedulaNoValidaException, CampusNoExistenteException, 
            PorteroYaExistenteException, TelefonoNoValidoException,
            PersonaYaRegistradaComoUsuarioException, IOException, ClassNotFoundException, ObjectSizeException
    {
        registroService = new RegistroService();
        validar.validarCedula(cedula);
        validar.ValidarDatos(cedula, nombre, apellido, direccion, telefono);
        Campus campus = campusService.getCampus(nombreCampus);
        Portero portero = new Portero(campus, cedula, nombre, apellido, direccion, telefono);
        porterosDAO.addPortero(nombreCampus, portero);
        //Registro
        registroService.add(getPortero(cedula).getRegistro(TipoModificacion.CREACION));
    }
    
    public void modPortero(String cedula, String nombre, String apellido, String direccion, String telefono,boolean estado)
            throws CedulaNoValidaException, PorteroNoExistenteException, TelefonoNoValidoException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException, CampusNoExistenteException{
        registroService = new RegistroService();
        validar.validarCedula(cedula);
        validar.ValidarDatos(cedula, nombre, apellido,direccion,telefono);
        porterosDAO.modPortero(cedula, nombre, apellido,direccion,telefono, estado);
        //Registro
        registroService.add(getPortero(cedula).getRegistro(TipoModificacion.MODIFICACION));
    }
    
    public void delPortero(String cedula) 
            throws CedulaNoValidaException, PorteroNoExistenteException, CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
        registroService = new RegistroService();
        validar.validarCedula(cedula);
        //Registro
        registroService.add(getPortero(cedula).getRegistro(TipoModificacion.ELIMINACION));
        //Fin registro
        
        porterosDAO.delPortero(cedula);
    }
    
    public Portero getPortero(String cedula)
            throws CedulaNoValidaException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        validar.validarCedula(cedula);
        return porterosDAO.getPortero(cedula);
    }
    
    public Set<Portero> getPorteros()
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        return porterosDAO.getPorteros();
    }
    
    public Set<Portero> getPorteros(String nombreCampus)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        return porterosDAO.getPorteros(nombreCampus);
    }
}
