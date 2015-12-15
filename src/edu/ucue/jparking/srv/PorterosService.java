/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.PorterosDAO;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.objetos.Portero;

/**
 *
 * @author Franklin
 */
public class PorterosService {
    Validaciones validar = new Validaciones();
    public void addPortero(String cedula, String nombre, String apellido) throws CedulaNoValidaException
    {
        validar.validarCedula(cedula);
        validar.ValidarDatos(cedula, nombre, apellido);
        Portero portero = new Portero(cedula, nombre, apellido, nombre);
        PorterosDAO.getInstance();
    }
    
    public void modPortero(String cedula, String nombre, String apellido,boolean estado) throws CedulaNoValidaException{
        validar.validarCedula(cedula);
        validar.ValidarDatos(cedula, nombre, apellido);
        PorterosDAO.getInstance();
        
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
