/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PersonaYaRegistradoComoPorteroException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * @author Franklin Lara
 */
public interface UsuariosDAOInterface {
    /**
     * Agrega un usuario al sistema, en caso de ya
     * existir el usuario se lanza una excepcion.
     * @param usuario Usuarion a ser agregado
     * @throws UsuarioYaExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.PersonaYaRegistradoComoPorteroException 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     */
    public abstract void addUsuario(Usuario usuario)
            throws UsuarioYaExistenteException, PersonaYaRegistradoComoPorteroException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException;
    
    /**
     * Eliminar un usuario dado su numero de cedula.
     * @param cedula
     * @throws UsuarioNoExistenteException
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException
     */
    public abstract void delUsuario(String cedula)
            throws UsuarioNoExistenteException, CampusNoExistenteException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException;
    
    /**
     * Obtiene un usuario dado su numero de cedula
     * @param cedula
     * @return
     * @throws UsuarioNoExistenteException 
     * @throws java.io.IOException 
     * @throws java.lang.ClassNotFoundException 
     */
    public Usuario getUsuario(String cedula)
            throws UsuarioNoExistenteException, IOException, ClassNotFoundException;
    
    /**
     * Modifica los campos nombres, apellidos, y estado
     * de un usuario dado su numero de cedula.
     * @param cedula
     * @param nombres
     * @param apellidos
     * @param direccion
     * @param telefono
     * @param activo
     * @throws UsuarioNoExistenteException 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.jparking.dao.bptree.ObjectSizeException 
     */
    public void modUsuario(String cedula, String nombres, String apellidos, String direccion, String telefono, boolean activo)
            throws UsuarioNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException,
            ObjectSizeException;
    
    /**
     * Retorna todos los usuarios registrados
     * @return 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     */
    public Set<Usuario> getUsuarios()
            throws IOException, FileNotFoundException, ClassNotFoundException;
    
    /**
     * Retorna una lista del tipo de usuario especificado
     * @param tipoUsuario
     * @return 
     * @throws java.io.IOException 
     * @throws java.lang.ClassNotFoundException 
     */
    public Set<Usuario> getUsuarios(TipoUsuario tipoUsuario) throws IOException,
            ClassNotFoundException;
    
    /**
     * Retorna todos los parqueaderos asociado a un usuario
     * @param cedula
     * @return 
     * @throws edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException 
     * @throws java.io.IOException 
     * @throws java.lang.ClassNotFoundException 
     */
    public Set<Parqueadero> getParqueaderos(String cedula)
            throws UsuarioNoExistenteException, IOException, ClassNotFoundException;
    
    /**
     * 
     * @param cedula
     * @param nombreCampus
     * @param idParqueadero
     * @throws UsuarioNoExistenteException
     * @throws CampusNoExistenteException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws ObjectSizeException 
     */
    public void addPaqueadero(String cedula, String nombreCampus, String idParqueadero)
        throws UsuarioNoExistenteException, CampusNoExistenteException, IOException,
        FileNotFoundException, ClassNotFoundException, ObjectSizeException;

    /**
     * Elimina un parqueadero de la lista de parqueaderos del usuario.
     * @param cedula
     * @param nombreCampus
     * @param idParqueadero
     * @throws UsuarioNoExistenteException
     * @throws CampusNoExistenteException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws ObjectSizeException 
     */
    public void delParqueadero(String cedula, String nombreCampus, String idParqueadero)
            throws UsuarioNoExistenteException, CampusNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException;
    
    /**
     * Establece una nueva fecha de contrato del usuario.
     * @param cedula
     * @param calendar
     * @throws UsuarioNoExistenteException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws ObjectSizeException 
     */
    public void setFechaContrato(String cedula, Calendar calendar)
            throws UsuarioNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException;
    
    /**
     * Establece si un usuario está dentro o fuera de un parqueadero.
     * @param cedula
     * @param in
     * @throws UsuarioNoExistenteException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws ObjectSizeException 
     */
    public void setIn(String cedula, boolean in)
            throws UsuarioNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException;
    
    public void update(String cedula, Usuario usuario)
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException;
}
