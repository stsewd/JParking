/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.PorterosDAO;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PorteroNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PorteroYaExistenteException;
import edu.ucue.jparking.dao.interfaces.PorterosDAOInterface;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.objetos.Portero;
import java.util.Set;

/**
 *
 * @author Franklin
 */
public class PorterosService {
    Validaciones validar = new Validaciones();
    PorterosDAOInterface porterosDAO = PorterosDAO.getInstance();
    /**
     * 
     * @param cedula
     * @param nombre
     * @param apellido
     * @param direccion
     * @param telefono
     * @throws CedulaNoValidaException 
     */
    public void addPortero(String campus, String cedula, String nombre, String apellido, String direccion, String telefono) throws CedulaNoValidaException, CampusNoExistenteException, PorteroYaExistenteException
    {
        validar.validarCedula(cedula);
        validar.ValidarDatos(cedula, nombre, apellido,direccion,telefono);
        Portero portero = new Portero(cedula, nombre, apellido, direccion, telefono, nombre);
        porterosDAO.addPortero(campus, portero);
    }
    /**
     * 
     * @param cedula
     * @param nombre
     * @param apellido
     * @param direccion
     * @param telefono
     * @param estado
     * @throws CedulaNoValidaException
     * @throws PorteroNoExistenteException 
     */
    public void modPortero(String cedula, String nombre, String apellido, String direccion, String telefono,boolean estado) throws CedulaNoValidaException, PorteroNoExistenteException{
        validar.validarCedula(cedula);
        validar.ValidarDatos(cedula, nombre, apellido,direccion,telefono);
        porterosDAO.modPortero(cedula, nombre, apellido,direccion,telefono, estado);
        
    }
    /**
     * 
     * @param cedula
     * @throws CedulaNoValidaException
     * @throws PorteroNoExistenteException
     * @throws CampusNoExistenteException 
     */
    public void delPortero(String cedula) throws CedulaNoValidaException, PorteroNoExistenteException, CampusNoExistenteException{
        validar.validarCedula(cedula);
        porterosDAO.delPortero(cedula);
    }
    /**
     * 
     * @param cedula
     * @return
     * @throws CedulaNoValidaException 
     */
    public Portero getPortero(String cedula) throws CedulaNoValidaException{
        validar.validarCedula(cedula);
        return porterosDAO.getPortero(cedula);
    }
    /**
     * 
     * @return 
     */
    public Set<Portero> getPorteros(){
        return porterosDAO.getPorteros();
    }
    /**
     * 
     * @param nombreCampus
     * @return
     * @throws CampusNoExistenteException 
     */
    public Set<Portero> getPorteros(String nombreCampus) throws CampusNoExistenteException{
        return porterosDAO.getPorteros(nombreCampus);
    }
}
