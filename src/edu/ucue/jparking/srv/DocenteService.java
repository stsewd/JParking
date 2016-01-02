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
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PersonaYaRegistradoComoPorteroException;
import edu.ucue.jparking.dao.interfaces.UsuariosDAOInterface;
import edu.ucue.jparking.srv.objetos.Docente;
import edu.ucue.jparking.srv.excepciones.TelefonoNoValidoException;
import java.util.Set;

/**
 *
 * @author Franklin Lara
 */
public class DocenteService {
    
    Validaciones validar = new Validaciones();
    UsuariosDAOInterface usuariosDAO = UsuariosDAO.getInstance();
    
    public void add(String cedula, String nombre, String apellido, String direccion, String telefono) throws UsuarioYaExistenteException, CedulaNoValidaException, TelefonoNoValidoException, PersonaYaRegistradoComoPorteroException {
        
        validar.ValidarDatos(cedula, nombre, apellido, direccion, telefono);
        validar.validarCedula(cedula);
        Docente docente = new Docente(cedula, nombre, apellido, direccion, telefono);
        usuariosDAO.addUsuario(docente);
           
    }

    public void del(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException, CampusNoExistenteException {
        validar.validarCedula(cedula);
        usuariosDAO.delUsuario(cedula);
    }
     
    public Docente get(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException {
        validar.validarCedula(cedula);
        return (Docente) usuariosDAO.getUsuario(cedula);
    }

    public void mod(String cedula, String nombre, String apellido, String direccion, String telefono,boolean estado) throws CedulaNoValidaException, UsuarioNoExistenteException, TelefonoNoValidoException{
        validar.validarCedula(cedula);
        validar.ValidarDatos(cedula, nombre, apellido,direccion,telefono);
        usuariosDAO.modUsuario(cedula, nombre, apellido,direccion,telefono, estado);        
    }

    public Set getLista() {
        return (Set) usuariosDAO.getUsuarios();
    }
}
