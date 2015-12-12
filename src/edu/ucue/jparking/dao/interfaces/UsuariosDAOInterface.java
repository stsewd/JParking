/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.util.Set;

/**
 *
 * @author ESTUDIANTE
 */
public interface UsuariosDAOInterface {
    
    public abstract void addUsuario(Usuario usuario) throws UsuarioYaExistenteException;
    public abstract void delUsuario(String cedula) throws UsuarioNoExistenteException;
    public abstract Usuario getUsuario(String cedula) throws UsuarioNoExistenteException;
    public abstract void modUsuario(String cedula, String nombres, String apellidos, boolean activo) throws UsuarioNoExistenteException;
    public abstract Set<Usuario> getUsuarios();
    
}
