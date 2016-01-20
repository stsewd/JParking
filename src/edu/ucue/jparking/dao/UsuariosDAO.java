/**
 * Clase que permite el acceso a los usuarios registrados en el sistema
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.bptree.ApellidoNombreGenerator;
import edu.ucue.jparking.dao.bptree.BPTreeMap;
import edu.ucue.jparking.dao.bptree.ComparatorString;
import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PersonaYaRegistradoComoPorteroException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoAgregadoException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.interfaces.UsuariosDAOInterface;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Santos Gallegos
 */
public class UsuariosDAO implements UsuariosDAOInterface {
    // private static Map<String, Usuario> usuarios; //Mapa<Cedula, Usuario>>
    private static BPTreeMap<String, Usuario> usuarios;
    private static final String dataPath = "data/usuarios.dat";
    private static final String indiceCedulaPath = "data/usuarios_cedula_index.dat";
    private static final String indiceApellidoPath = "data/usuarios_apellido_nombre_index.dat";
    private static final int objSize = 9999; // 674 usuario sin parqueaderos.
    
    private static UsuariosDAO instance;

    private UsuariosDAO() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        // usuarios = new TreeMap<>();
        usuarios = BPTreeMap.getTree(3, new ComparatorString(), dataPath, indiceCedulaPath, objSize, 1500);
        usuarios.addSecIndex(indiceApellidoPath, new ApellidoNombreGenerator(), 1500);
    }
    
    public static UsuariosDAO getInstance() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
        if (instance == null)
            instance = new UsuariosDAO();
        return instance;
    }
    
    @Override
    public void addUsuario(Usuario usuario) 
            throws UsuarioYaExistenteException, PersonaYaRegistradoComoPorteroException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        if(usuarios.get(usuario.getCedula()) != null)
            throw new UsuarioYaExistenteException(usuario.getCedula());
        if(PorterosDAO.getInstance().getPortero(usuario.getCedula()) != null)
            throw new PersonaYaRegistradoComoPorteroException(usuario.getCedula());
        usuarios.put(usuario.getCedula(), usuario);
    }
    
    @Override
    public void delUsuario(String cedula)
            throws UsuarioNoExistenteException, CampusNoExistenteException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        if(usuarios.get(cedula) == null)
            throw new UsuarioNoExistenteException(cedula);
        /******************************
         * Eliminar dependencias:
         * Eliminar usuario de todos los parqueaderos.
         ******************************/
        for(Campus c : CampusDAO.getInstancia().getCampus()){
            for(Parqueadero p : ParqueaderosDAO.getInstance().getParqueaderos(c.getNombre())){
                try{
                    ParqueaderosDAO.getInstance().delUsuario(c.getNombre(), p.getId(), cedula);
                }catch (UsuarioNoAgregadoException ex){}
            }
        }
        
        usuarios.remove(cedula);
    }
    
    @Override
    public Usuario getUsuario(String cedula) throws UsuarioNoExistenteException,
            IOException, FileNotFoundException, ClassNotFoundException
    {
        if(usuarios.get(cedula) == null)
            throw new UsuarioNoExistenteException(cedula);
        return usuarios.get(cedula);
    }
    
    @Override
    public void modUsuario(String cedula, String nombres, String apellidos, String direccion, String telefono, boolean activo)
            throws UsuarioNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        if(usuarios.get(cedula) == null)
            throw new UsuarioNoExistenteException(cedula);
        Usuario usuario = usuarios.get(cedula);
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setDireccion(direccion);
        usuario.setTelefono(telefono);
        usuario.setActivo(activo);
        
        usuarios.update(cedula, usuario);
    }
        
    @Override
    public Set<Usuario> getUsuarios() throws IOException, FileNotFoundException, ClassNotFoundException{
        return new LinkedHashSet<>(usuarios.valuesOf(0));
    }

    @Override
    public Set<Usuario> getUsuarios(TipoUsuario tipoUsuario) throws IOException, FileNotFoundException, ClassNotFoundException {
        Set<Usuario> usuarios = new LinkedHashSet<>();
        
        for(Usuario u : getUsuarios()){
            if(u.getTipoUsuario() == tipoUsuario)
                usuarios.add(u);
        }
        return usuarios;
    }
    
    @Override
    public void setFechaContrato(String cedula, Calendar calendar)
            throws UsuarioNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        if(usuarios.get(cedula) == null)
            throw new UsuarioNoExistenteException(cedula);
        Usuario usuario = usuarios.get(cedula);
        usuario.setFechaContrato(calendar);
        
        usuarios.update(cedula, usuario);
    }

    @Override
    public Set<Parqueadero> getParqueaderos(String cedula) throws UsuarioNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException {
        return getUsuario(cedula).getParqueaderos();
    }

    @Override
    public void addPaqueadero(String cedula, String nombreCampus, String idParqueadero)
            throws UsuarioNoExistenteException, CampusNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        Usuario u = getUsuario(cedula);
        u.addParqueadero(ParqueaderosDAO.getInstance().getParqueadero(nombreCampus, idParqueadero));
        
        usuarios.update(cedula, u);
    }
    
    @Override
    public void delParqueadero(String cedula, String nombreCampus, String idParqueadero)
            throws UsuarioNoExistenteException, CampusNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        Usuario u = getUsuario(cedula);
        u.delParqueadero(ParqueaderosDAO.getInstance().getParqueadero(nombreCampus, idParqueadero));
        
        usuarios.update(cedula, u);
    }
    
    @Override
    public void setIn(String cedula, boolean in)
            throws UsuarioNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        Usuario u = getUsuario(cedula);
        u.setIn(in);
        
        usuarios.update(cedula, u);
    }
    
    @Override
    public void update(String cedula, Usuario usuario)
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        usuarios.update(cedula, usuario);
    }
}
