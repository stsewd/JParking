/**
 * Clase que permite el acceso a los usuarios registrados en el sistema
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.srv.Usuario;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public class UsuariosDAO {
    //Mapa<Id de parqueadero, Mapa<Cedula, Usuario>>
    private static Map<String, Map<String, Usuario>> usuarios;
    private static UsuariosDAO instance;

    private UsuariosDAO() {
        usuarios = new HashMap<>();
    }
    
    public static UsuariosDAO getInstance(){
        if (instance == null)
            instance = new UsuariosDAO();
        return instance;
    }
    
    //Funciones CRUD
    
    public void addUsuario(Usuario usuario) throws UsuarioYaExistenteException{
        if(usuarios.get(usuario.getCedula()) != null)
            throw new UsuarioYaExistenteException(usuario.getCedula());
        usuarios.put(usuario.getCedula(), usuario);
    }
    
    public void delUsuario(String cedula) throws UsuarioNoExistenteException{
        if(usuarios.get(cedula) == null)
            throw new UsuarioNoExistenteException(cedula);
        usuarios.get(cedula).setActivo(false);
    }
    
    public Usuario getUsuario(String cedula) throws UsuarioNoExistenteException{
        if(usuarios.get(cedula) == null)
            throw new UsuarioNoExistenteException(cedula);
        return usuarios.get(cedula);
    }
    
    public void modUsuario(String cedula, String nombres, String apellidos, boolean activo) throws UsuarioNoExistenteException{
        if(usuarios.get(cedula) == null)
            throw new UsuarioNoExistenteException(cedula);
        Usuario usuario = usuarios.get(cedula);
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setActivo(activo);
    }
    
    public Set getUsuarios(){
        return (Set) usuarios.values();
    }
    /*
    addUsuario
    delUsuario
    getUsuario
    modUsuario
    */
}
