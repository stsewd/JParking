/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.dao.UsuariosDAO;
import edu.ucue.jparking.srv.objetos.Empleado;
import edu.ucue.jparking.srv.Validaciones;
import java.util.Set;

/**
 *
 * @author ESTUDIANTE
 */
public class EmpleadoService {
    
    Validaciones validar = new Validaciones();
    
    
    
    public void add(String cedula, String nombre, String apellido, String direccion, String telefono) throws UsuarioYaExistenteException, CedulaNoValidaException {
        
        validar.ValidarDatos(cedula, nombre, apellido,direccion,telefono);
        validar.validarCedula(cedula);
        Empleado empleado = new Empleado(cedula, nombre, apellido, direccion, telefono);
        UsuariosDAO.getInstance().addUsuario(empleado);
           
    }

    
    public void del(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException {
        validar.validarCedula(cedula);
        UsuariosDAO.getInstance().delUsuario(cedula);
    }

    
    public Empleado get(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException {
        validar.validarCedula(cedula);
        return (Empleado) UsuariosDAO.getInstance().getUsuario(cedula);
    }
        
    public void mod(String cedula, String nombre, String apellido, String direccion, String telefono,boolean estado) throws CedulaNoValidaException, UsuarioNoExistenteException{
        validar.validarCedula(cedula);
        validar.ValidarDatos(cedula, nombre, apellido,direccion, telefono);
        UsuariosDAO.getInstance().modUsuario(cedula, nombre, apellido,direccion,telefono, estado);
        
        
    }

    
    public Set getLista() {
        return (Set) UsuariosDAO.getInstance().getUsuarios();
    }
}
