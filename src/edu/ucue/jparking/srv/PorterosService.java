/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.PorterosDAO;
import edu.ucue.jparking.dao.excepciones.PorteroNoExistenteException;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.objetos.Portero;

/**
 *
 * @author Franklin
 */
public class PorterosService {
    Validaciones validar = new Validaciones();
    public void addPortero(String cedula, String nombre, String apellido, String direccion, String telefono) throws CedulaNoValidaException
    {
        validar.validarCedula(cedula);
        validar.ValidarDatos(cedula, nombre, apellido,direccion,telefono);
        Portero portero = new Portero(cedula, nombre, apellido, direccion, telefono, nombre);
        PorterosDAO.getInstance();
    }
    
    public void modPortero(String cedula, String nombre, String apellido, String direccion, String telefono,boolean estado) throws CedulaNoValidaException, PorteroNoExistenteException{
        validar.validarCedula(cedula);
        validar.ValidarDatos(cedula, nombre, apellido,direccion,telefono);
        PorterosDAO.getInstance().modPortero(cedula, nombre, apellido,direccion,telefono, estado);
        
    }
    /*
    public void delPortero(String cedula) throws CedulaNoValidaException{
        validar.validarCedula(cedula);
        PorterosDAO.getInstance();
    }
    public Portero getPortero(String cedula) throws CedulaNoValidaException{
        validar.validarCedula(cedula);
        return PorterosDAO.getInstance();
    }*/
}
