/**
 * 
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.excepciones.PuertaNoAgregadaException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoAgregadoException;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoYaExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaYaAgregadaException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.interfaces.ParqueaderosDAOInterface;
import edu.ucue.jparking.dao.excepciones.UsuarioYaAgregadoException;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Puerta;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Santos Gallegos
 */
public class ParqueaderosDAO implements ParqueaderosDAOInterface {
        
    private static ParqueaderosDAO instance;
    
    private ParqueaderosDAO(){
    }

    public static ParqueaderosDAO getInstance() {
        if (instance == null)
            instance = new ParqueaderosDAO();
        return instance;
    }

    @Override
    public void addParqueadero(String nombreCampus, Parqueadero parqueadero) throws ParqueaderoYaExistenteException, CampusNoExistenteException {
        if(getParqueadero(nombreCampus, parqueadero.getId()) != null)
            throw new ParqueaderoYaExistenteException(parqueadero.getId());
        CampusDAO.getInstancia().getCampus(nombreCampus).getParqueaderos().put(parqueadero.getId(), parqueadero);
    }

    @Override
    public void delParqueadero(String nombreCampus, String idParqueadero) throws ParqueaderoNoExistenteException, CampusNoExistenteException, UsuarioNoExistenteException, UsuarioNoAgregadoException {
        if(getParqueadero(nombreCampus, idParqueadero) == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        
        /***************************************************************
         * Eliminar dependencias
         * Eliminar parqueadero de todos los usuarios de este parqueadero.
         ****************************************************************/
        for(Usuario usuario : getUsuarios(nombreCampus, idParqueadero)){
            ParqueaderosDAO.getInstance().delUsuario(nombreCampus, idParqueadero, usuario.getCedula());
        }
        
        CampusDAO.getInstancia().getCampus(nombreCampus).getParqueaderos().remove(idParqueadero);
    }

    @Override
    public Parqueadero getParqueadero(String nombreCampus, String idParqueadero) throws CampusNoExistenteException {
        Campus campus = CampusDAO.getInstancia().getCampus(nombreCampus);
        if(campus == null)
            throw new CampusNoExistenteException(nombreCampus);
        return campus.getParqueaderos().get(idParqueadero);
    }

    @Override
    public Set<Parqueadero> getParqueaderos() {
        Set<Parqueadero> parqueaderos = new TreeSet<>();
        for(Campus c : CampusDAO.getInstancia().getCampus()){
            parqueaderos.addAll(c.getParqueaderos().values());
        }
        return parqueaderos;
    }

    @Override
    public Set<Parqueadero> getParqueaderos(String nombreCampus) throws CampusNoExistenteException {
        return new TreeSet<>(CampusDAO.getInstancia().getCampus(nombreCampus).getParqueaderos().values());
    }

    @Override
    public void modParqueadero(String nombreCampus, String idParqueadero, String ubicacion, int numLugares,boolean estado) throws ParqueaderoNoExistenteException, CampusNoExistenteException {
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        parqueadero.setUbicacion(ubicacion);
        parqueadero.setNumeroLugares(numLugares);
        parqueadero.setActivo(estado);
    }

    @Override
    public void addPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CampusNoExistenteException, PuertaYaAgregadaException {
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        if(PuertasDAO.getInstance().getPuerta(nombreCampus, idPuerta) == null)
            throw new PuertaNoExistenteException(idPuerta);
        if(parqueadero.getPuertasEntrada().contains(idPuerta))
            throw new PuertaYaAgregadaException(idPuerta);
        parqueadero.getPuertasEntrada().add(idPuerta);
    }

    @Override
    public void addPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CampusNoExistenteException, PuertaYaAgregadaException {
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        if(PuertasDAO.getInstance().getPuerta(nombreCampus, idPuerta) == null)
            throw new PuertaNoExistenteException(idPuerta);
        if(parqueadero.getPuertasSalida().contains(idPuerta))
            throw new PuertaYaAgregadaException(idPuerta);
        parqueadero.getPuertasSalida().add(idPuerta);
    }

    @Override
    public void delPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta)
            throws PuertaNoExistenteException, ParqueaderoNoExistenteException,
            CampusNoExistenteException, PuertaNoAgregadaException {
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        if(PuertasDAO.getInstance().getPuerta(nombreCampus, idPuerta) == null)
            throw new PuertaNoExistenteException(idPuerta);
        if(!parqueadero.getPuertasEntrada().contains(idPuerta))
            throw new PuertaNoAgregadaException(idPuerta);
        parqueadero.getPuertasEntrada().remove(idPuerta);
    }

    @Override
    public void delPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CampusNoExistenteException, PuertaNoAgregadaException {
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        if(PuertasDAO.getInstance().getPuerta(nombreCampus, idPuerta) == null)
            throw new PuertaNoExistenteException(idPuerta);
        if(!parqueadero.getPuertasSalida().contains(idPuerta))
            throw new PuertaNoAgregadaException(idPuerta);
        parqueadero.getPuertasSalida().remove(idPuerta);
    }

    @Override
    public void addUsuario(String nombreCampus, String idParqueadero, String cedula) throws ParqueaderoNoExistenteException, UsuarioNoExistenteException, UsuarioYaAgregadoException, CampusNoExistenteException {
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        
        UsuariosDAO.getInstance().getUsuario(cedula);
        
        if(parqueadero.getUsuarios().contains(cedula))
            throw new UsuarioYaAgregadoException(cedula);
        
        parqueadero.getUsuarios().add(cedula);
        UsuariosDAO.getInstance().addPaqueadero(cedula, idParqueadero);
    }

    @Override
    public void delUsuario(String nombreCampus, String idParqueadero, String cedula) throws UsuarioNoExistenteException, UsuarioNoAgregadoException, CampusNoExistenteException {
        UsuariosDAO.getInstance().getUsuario(cedula);
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(!parqueadero.getUsuarios().contains(cedula))
            throw new UsuarioNoAgregadoException(cedula);
        
        parqueadero.getUsuarios().remove(cedula);
        
        UsuariosDAO.getInstance().delParqueadero(cedula, idParqueadero);
    }

    @Override
    public Set<Puerta> getPuertasEntrada(String nombreCampus, String idParqueadero) throws ParqueaderoNoExistenteException, CampusNoExistenteException {
        Set<Puerta> puertas = new TreeSet<>();
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        for(String idPuerta : parqueadero.getPuertasEntrada())
            puertas.add(PuertasDAO.getInstance().getPuerta(nombreCampus, idPuerta));
        return puertas;
    }

    @Override
    public Set<Puerta> getPuertasSalida(String nombreCampus, String idParqueadero) throws ParqueaderoNoExistenteException, CampusNoExistenteException {
        Set<Puerta> puertas = new TreeSet<>();
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        for(String idPuerta : parqueadero.getPuertasSalida())
            puertas.add(PuertasDAO.getInstance().getPuerta(nombreCampus, idPuerta));
        return puertas;
    }

    @Override
    public Set<Usuario> getUsuarios(String nombreCampus, String idParqueadero) throws ParqueaderoNoExistenteException, UsuarioNoExistenteException, CampusNoExistenteException {
        Set<Usuario> usuarios = new TreeSet<>();
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        for(String idUsuario : parqueadero.getUsuarios())
            usuarios.add(UsuariosDAO.getInstance().getUsuario(idUsuario));
        return usuarios;
    }
}
