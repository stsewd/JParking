/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv.service;

import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.dao.UsuariosDAO;
import edu.ucue.jparking.srv.Estudiante;
import edu.ucue.jparking.srv.Validaciones;
import java.util.Set;

/**
 *
 * @author ESTUDIANTE
 */
public class EstudianteService {
    
    Validaciones validar = new Validaciones();
    
    
    
    public void add(String cedula, String nombre, String apellido) throws UsuarioYaExistenteException, CedulaNoValidaException {
        
        validar.ValidarDatos(cedula, nombre, apellido);
        validar.validarCedula(cedula);
        Estudiante estudiante = new Estudiante(cedula, nombre, apellido);
        UsuariosDAO.getInstance().addUsuario(estudiante);
           
    }

    
    public void del(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException {
        validar.validarCedula(cedula);
        UsuariosDAO.getInstance().delUsuario(cedula);
    }

    
    public Estudiante get(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException {
        validar.validarCedula(cedula);
        return (Estudiante) UsuariosDAO.getInstance().getUsuario(cedula);
    }
        
    public void mod(String cedula, String nombre, String apellido,boolean estado) throws CedulaNoValidaException, UsuarioNoExistenteException{
        validar.validarCedula(cedula);
        validar.ValidarDatos(cedula, nombre, apellido);
        UsuariosDAO.getInstance().modUsuario(cedula, nombre, apellido, estado);
        
        
    }

    
    public Set getLista() {
        return (Set) UsuariosDAO.getInstance().getUsuarios();
    }

}