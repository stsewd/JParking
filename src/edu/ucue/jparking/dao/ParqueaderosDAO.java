/**
 * 
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoYaExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.interfaces.ParqueaderosDAOInterface;
import edu.ucue.jparking.dao.excepciones.UsuarioYaAgregadoException;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Puerta;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public class ParqueaderosDAO implements ParqueaderosDAOInterface {
    
    //Clase innecesaria?
    //Mapa <Nombre de campus, Mapa<Id de parqueadero, parqueadero>>
    private static Map<String, Map<String, Parqueadero>> parqueaderos;
    
    private static ParqueaderosDAO instance;
    
    private ParqueaderosDAO(){
        parqueaderos = new HashMap<>();
    }

    public static ParqueaderosDAO getInstance() {
        if (instance == null)
            instance = new ParqueaderosDAO();
        return instance;
    }

    @Override
    public void addParqueadero(String nombreCampus, Parqueadero parqueadero) throws ParqueaderoYaExistenteException, CampusNoExistenteException {
        if(getParqueadero(parqueadero.getId()) != null)
            throw new ParqueaderoYaExistenteException(parqueadero.getId());
        CampusDAO.getInstancia().getCampus(nombreCampus).getParqueaderos().put(parqueadero.getId(), parqueadero);
    }

    @Override
    public void delParqueadero(String idParqueadero) throws ParqueaderoNoExistenteException, CampusNoExistenteException {
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        CampusDAO.getInstancia().getCampus(parqueadero.getNombreCampus()).getParqueaderos().remove(idParqueadero);
    }

    @Override
    public Parqueadero getParqueadero(String idParqueadero) {
        Parqueadero parqueadero;
        for(Campus c : CampusDAO.getInstancia().getCampus()){
            parqueadero = c.getParqueaderos().get(idParqueadero);
            if(parqueadero != null)
                return parqueadero;
        }
        return null;
    }

    @Override
    public Set<Parqueadero> getParqueaderos() {
        Set<Parqueadero> parqueaderos = new HashSet<>();
        for(Campus c : CampusDAO.getInstancia().getCampus()){
            parqueaderos.add((Parqueadero) c.getParqueaderos().values());
        }
        return parqueaderos;
    }

    @Override
    public Set<Parqueadero> getParqueaderos(String nombreCampus) throws CampusNoExistenteException {
        return (Set<Parqueadero>) CampusDAO.getInstancia().getCampus(nombreCampus).getParqueaderos().values();
    }

    @Override
    public void modParqueadero(String idParqueadero, String ubicacion) throws ParqueaderoNoExistenteException {
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        parqueadero.setUbicacion(ubicacion);
    }

    @Override
    public void addPuertaEntrada(String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException {
        Puerta puerta = PuertasDAO.getInstance().getPuerta(idPuerta);
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(puerta == null)
            throw new PuertaNoExistenteException(idPuerta);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        parqueadero.getPuertasEntrada().add(idPuerta);
    }

    @Override
    public void addPuertaSalida(String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException {
        Puerta puerta = PuertasDAO.getInstance().getPuerta(idPuerta);
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(puerta == null)
            throw new PuertaNoExistenteException(idPuerta);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        parqueadero.getPuertasSalida().add(idPuerta);
    }

    @Override
    public void delPuertaEntrada(String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException {
        Puerta puerta = PuertasDAO.getInstance().getPuerta(idPuerta);
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(puerta == null)
            throw new PuertaNoExistenteException(idPuerta);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        parqueadero.getPuertasEntrada().remove(idPuerta);
    }

    @Override
    public void delPuertaSalida(String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException {
        Puerta puerta = PuertasDAO.getInstance().getPuerta(idPuerta);
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(puerta == null)
            throw new PuertaNoExistenteException(idPuerta);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        parqueadero.getPuertasSalida().remove(idPuerta);
    }

    @Override
    public void addUsuario(String idParqueadero, String cedula) throws ParqueaderoNoExistenteException, UsuarioNoExistenteException, UsuarioYaAgregadoException {
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        UsuariosDAO.getInstance().getUsuario(cedula);
        
        if(parqueadero.getUsuarios().contains(cedula))
            throw new UsuarioYaAgregadoException(cedula);
        
        parqueadero.getUsuarios().add(cedula);
    }

    @Override
    public void delUsuario(String idParqueadero, String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Puerta> getPuertasEntrada(String idParqueadero) throws ParqueaderoNoExistenteException {
        Set<Puerta> puertas = new HashSet<>();
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        for(String s : parqueadero.getPuertasEntrada()){
            puertas.add(PuertasDAO.getInstance().getPuerta(s));
        }
        return puertas;
    }

    @Override
    public Set<Puerta> getPuertasSalida(String idParqueadero) throws ParqueaderoNoExistenteException {
        Set<Puerta> puertas = new HashSet<>();
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        for(String s : parqueadero.getPuertasSalida()){
            puertas.add(PuertasDAO.getInstance().getPuerta(s));
        }
        return puertas;
    }

    @Override
    public Set<Usuario> getUsuarios(String idParqueadero) throws ParqueaderoNoExistenteException, UsuarioNoExistenteException {
        Set<Usuario> usuarios = new HashSet<>();
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        for(String s : parqueadero.getUsuarios()){
            usuarios.add(UsuariosDAO.getInstance().getUsuario(s));
        }
        return usuarios;
    }
}
