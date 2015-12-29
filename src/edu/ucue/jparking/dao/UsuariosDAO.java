/**
 * Clase que permite el acceso a los usuarios registrados en el sistema
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PersonaYaRegistradoComoPorteroException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.interfaces.UsuariosDAOInterface;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
    
    @Override
    public void addUsuario(Usuario usuario) throws UsuarioYaExistenteException, PersonaYaRegistradoComoPorteroException{
        if(usuarios.get(usuario.getCedula()) != null)
            throw new UsuarioYaExistenteException(usuario.getCedula());
        if(PorterosDAO.getInstance().getPortero(usuario.getCedula()) != null)
            throw new PersonaYaRegistradoComoPorteroException(usuario.getCedula());
        usuarios.put(usuario.getCedula(), usuario);
    }
    
    @Override
    public void delUsuario(String cedula) throws UsuarioNoExistenteException{
        if(usuarios.get(cedula) == null)
            throw new UsuarioNoExistenteException(cedula);
        usuarios.remove(cedula);
    }
    
    @Override
    public Usuario getUsuario(String cedula) throws UsuarioNoExistenteException{
        if(usuarios.get(cedula) == null)
            throw new UsuarioNoExistenteException(cedula);
        return usuarios.get(cedula);
    }
    
    @Override
    public void modUsuario(String cedula, String nombres, String apellidos, String direccion, String telefono, boolean activo) throws UsuarioNoExistenteException{
        if(usuarios.get(cedula) == null)
            throw new UsuarioNoExistenteException(cedula);
        Usuario usuario = usuarios.get(cedula);
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setDireccion(direccion);
        usuario.setTelefono(telefono);
        usuario.setActivo(activo);
    }
        
    @Override
    public Set<Usuario> getUsuarios(){
        return new LinkedHashSet<>(usuarios.values());
    }

    @Override
    public Set<Usuario> getUsuarios(TipoUsuario tipoUsuario) {
        Set<Usuario> usuarios = new HashSet<>();
        
        for(Usuario u : getUsuarios()){
            if(u.getTipoUsuario() == tipoUsuario)
                usuarios.add(u);
        }
        return usuarios;
    }
    
    public void fechaContrato(String cedula, Calendar calendar) throws UsuarioNoExistenteException{
        if(usuarios.get(cedula) == null)
            throw new UsuarioNoExistenteException(cedula);
        Usuario usuario = usuarios.get(cedula);
        usuario.setFechaContrato(calendar);
    }

    @Override
    public Set<Parqueadero> getParqueaderos(String cedula) throws UsuarioNoExistenteException {
        Set<Parqueadero> parqueaderos = new HashSet<>();
        
        /****************************************************
         * No te olvides de manear varibles de instancia dentro
         * de la lista de parqueaderos de un usuario
         * en lugar de sólo el id.
        *******************************************************/
        
        for(Campus campus : CampusDAO.getInstancia().getCampus()){
            for(String idParqueadero : getUsuario(cedula).getParqueaderos()){
                try {
                    parqueaderos.add(ParqueaderosDAO.getInstance().getParqueadero(campus.getNombre(), idParqueadero));
                } catch (CampusNoExistenteException ex) {
                }
            }
        }
        return parqueaderos;
    }

    public void addPaqueadero(String cedula, String idParqueadero) throws UsuarioNoExistenteException {
        getUsuario(cedula).getParqueaderos().add(idParqueadero);
    }
    
    public void delParqueadero(String cedula, String idParqueadero) throws UsuarioNoExistenteException{
        getUsuario(cedula).getParqueaderos().remove(idParqueadero);
    }
}
