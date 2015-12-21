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
import edu.ucue.jparking.dao.interfaces.UsuariosDAOInterface;
import edu.ucue.jparking.srv.objetos.Empleado;
import edu.ucue.jparking.srv.Validaciones;
import edu.ucue.jparking.srv.excepciones.TelefonoNoValidoException;
import java.util.Set;

/**
 *
 * @author ESTUDIANTE
 */
public class EmpleadoService {
    UsuariosDAOInterface usuariosDAO = UsuariosDAO.getInstance();
    Validaciones validar = new Validaciones();
    /**
     * 
     * @param cedula
     * @param nombre
     * @param apellido
     * @param direccion
     * @param telefono
     * @throws UsuarioYaExistenteException
     * @throws CedulaNoValidaException 
     */
    public void add(String cedula, String nombre, String apellido, String direccion, String telefono) throws UsuarioYaExistenteException, CedulaNoValidaException, TelefonoNoValidoException {
        
        validar.ValidarDatos(cedula, nombre, apellido,direccion,telefono);
        validar.validarCedula(cedula);
        Empleado empleado = new Empleado(cedula, nombre, apellido, direccion, telefono);
        usuariosDAO.addUsuario(empleado);
           
    }

    /**
     * 
     * @param cedula
     * @throws UsuarioNoExistenteException
     * @throws CedulaNoValidaException 
     */
    public void del(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException {
        validar.validarCedula(cedula);
        usuariosDAO.delUsuario(cedula);
    }

    /**
     * 
     * @param cedula
     * @return
     * @throws UsuarioNoExistenteException
     * @throws CedulaNoValidaException 
     */
    public Empleado get(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException {
        validar.validarCedula(cedula);
        return (Empleado) usuariosDAO.getUsuario(cedula);
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
     * @throws UsuarioNoExistenteException 
     */    
    public void mod(String cedula, String nombre, String apellido, String direccion, String telefono,boolean estado) throws CedulaNoValidaException, UsuarioNoExistenteException, TelefonoNoValidoException{
        validar.validarCedula(cedula);
        validar.ValidarDatos(cedula, nombre, apellido,direccion, telefono);
        usuariosDAO.modUsuario(cedula, nombre, apellido,direccion,telefono, estado);
    }

    /**
     * 
     * @return 
     */
    public Set getLista() {
        return (Set) usuariosDAO.getUsuarios();
    }
}
