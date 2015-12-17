/**
 * Clase que permite el acceso a los usuarios registrados en el sistema
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.interfaces.UsuariosDAOInterface;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public class UsuariosDAO implements UsuariosDAOInterface{
    private static Map<String, Usuario> usuarios; //Mapa<Cedula, Usuario>>
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
    @Override
    public void addUsuario(Usuario usuario) throws UsuarioYaExistenteException{
        if(usuarios.get(usuario.getCedula()) != null)
            throw new UsuarioYaExistenteException(usuario.getCedula());
        usuarios.put(usuario.getCedula(), usuario);
    }
    
    @Override
    public void delUsuario(String cedula) throws UsuarioNoExistenteException{
        if(usuarios.get(cedula) == null)
            throw new UsuarioNoExistenteException(cedula);
        usuarios.get(cedula).setActivo(false);
    }
    
    @Override
    public Usuario getUsuario(String cedula) throws UsuarioNoExistenteException{
        if(usuarios.get(cedula) == null)
            throw new UsuarioNoExistenteException(cedula);
        return usuarios.get(cedula);
    }
    
    @Override
    public void modUsuario(String cedula, String nombres, String apellidos, boolean activo) throws UsuarioNoExistenteException{
        if(usuarios.get(cedula) == null)
            throw new UsuarioNoExistenteException(cedula);
        Usuario usuario = usuarios.get(cedula);
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setActivo(activo);
    }
        
    @Override
    public Set<Usuario> getUsuarios(){
        return (Set<Usuario>) usuarios.values();
    }
    /*
    addUsuario
    delUsuario
    getUsuario
    modUsuario
    */

    @Override
    public Set<Usuario> getUsuarios(TipoUsuario tipoUsuario) {
        Set<Usuario> usuarios = new HashSet<>();
        
        for(Usuario u : getUsuarios()){
            if(u.getTipoUsuario() == tipoUsuario)
                usuarios.add(u);
        }
        return usuarios;
    }
}
