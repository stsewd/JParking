/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.util.Set;

/**
 *
 * @author ESTUDIANTE
 */
public interface UsuariosDAOInterface {
    /**
     * Agrega un usuario al sistema, en caso de ya
     * existir el usuario se lanza una excepcion.
     * @param usuario Usuarion a ser agregado
     * @throws UsuarioYaExistenteException 
     */
    public abstract void addUsuario(Usuario usuario)
            throws UsuarioYaExistenteException;
    
    /**
     * Eliminar un usuario dado su numero de cedula.
     * @param cedula
     * @throws UsuarioNoExistenteException
     */
    public abstract void delUsuario(String cedula)
            throws UsuarioNoExistenteException;
    
    /**
     * Obtiene un usuario dado su numero de cedula
     * @param cedula
     * @return
     * @throws UsuarioNoExistenteException 
     */
    public abstract Usuario getUsuario(String cedula)
            throws UsuarioNoExistenteException;
    
    /**
     * Modifica los campos nombres, apellidos, y estado
     * de un usuario dado su numero de cedula.
     * @param cedula
     * @param nombres
     * @param apellidos
     * @param activo
     * @throws UsuarioNoExistenteException 
     */
    public abstract void modUsuario(String cedula, String nombres, String apellidos, boolean activo)
            throws UsuarioNoExistenteException;
    
    /**
     * Retorna todos los usuarios registrados
     * @return 
     */
    public abstract Set<Usuario> getUsuarios();
    
    /**
     * Retorna una lista del tipo de usuario especificado
     * @param tipoUsuario
     * @return 
     */
    public abstract Set<Usuario> getUsuarios(TipoUsuario tipoUsuario);
}
