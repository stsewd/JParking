/**
 * 
 */
package edu.ucue.jparking.dao;

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
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

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
        return new HashSet<>(CampusDAO.getInstancia().getCampus(nombreCampus).getParqueaderos().values());
    }

    @Override
    public void modParqueadero(String idParqueadero, String ubicacion, int numLugares,boolean estado) throws ParqueaderoNoExistenteException {
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        parqueadero.setUbicacion(ubicacion);
        parqueadero.setNumeroLugares(numLugares);
        parqueadero.setActivo(estado);
    }

    @Override
    public void addPuertaEntrada(String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CampusNoExistenteException, PuertaYaAgregadaException {
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        Puerta puerta = PuertasDAO.getInstance().getPuerta(parqueadero.getNombreCampus(), idPuerta);
        if(puerta == null)
            throw new PuertaNoExistenteException(idPuerta);
        if(parqueadero.getPuertasEntrada().contains(idPuerta))
            throw new PuertaYaAgregadaException(idPuerta);
        parqueadero.getPuertasEntrada().add(idPuerta);
    }

    @Override
    public void addPuertaSalida(String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CampusNoExistenteException, PuertaYaAgregadaException {
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        Puerta puerta = PuertasDAO.getInstance().getPuerta(parqueadero.getNombreCampus(), idPuerta);
        if(puerta == null)
            throw new PuertaNoExistenteException(idPuerta);
        if(parqueadero.getPuertasSalida().contains(idPuerta))
            throw new PuertaYaAgregadaException(idPuerta);
        parqueadero.getPuertasSalida().add(idPuerta);
    }

    @Override
    public void delPuertaEntrada(String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CampusNoExistenteException {
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        Puerta puerta = PuertasDAO.getInstance().getPuerta(parqueadero.getNombreCampus(), idPuerta);
        if(puerta == null)
            throw new PuertaNoExistenteException(idPuerta);
        parqueadero.getPuertasEntrada().remove(idPuerta);
    }

    @Override
    public void delPuertaSalida(String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CampusNoExistenteException {
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        Puerta puerta = PuertasDAO.getInstance().getPuerta(parqueadero.getNombreCampus(), idPuerta);
        if(puerta == null)
            throw new PuertaNoExistenteException(idPuerta);
        parqueadero.getPuertasSalida().remove(idPuerta);
    }

    @Override
    public void addUsuario(String idParqueadero, String cedula) throws ParqueaderoNoExistenteException, UsuarioNoExistenteException, UsuarioYaAgregadoException {
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        
        Usuario u = UsuariosDAO.getInstance().getUsuario(cedula);
        
        if(parqueadero.getUsuarios().contains(cedula))
            throw new UsuarioYaAgregadoException(cedula);
        
        parqueadero.getUsuarios().add(cedula);
        UsuariosDAO.getInstance().addPaqueadero(cedula, parqueadero.getId());
    }

    @Override
    public void delUsuario(String idParqueadero, String cedula) throws UsuarioNoExistenteException, UsuarioNoAgregadoException {
        UsuariosDAO.getInstance().getUsuario(cedula);
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(!parqueadero.getUsuarios().contains(cedula))
            throw new UsuarioNoAgregadoException(cedula);
        parqueadero.getUsuarios().remove(cedula);
        UsuariosDAO.getInstance().delParqueadero(cedula, idParqueadero);
    }

    @Override
    public Set<Puerta> getPuertasEntrada(String idParqueadero) throws ParqueaderoNoExistenteException, CampusNoExistenteException {
        Set<Puerta> puertas = new HashSet<>();
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        for(String s : parqueadero.getPuertasEntrada()){
            puertas.add(PuertasDAO.getInstance().getPuerta(parqueadero.getNombreCampus(), s));
        }
        return puertas;
    }

    @Override
    public Set<Puerta> getPuertasSalida(String idParqueadero) throws ParqueaderoNoExistenteException, CampusNoExistenteException {
        Set<Puerta> puertas = new HashSet<>();
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        for(String s : parqueadero.getPuertasSalida()){
            puertas.add(PuertasDAO.getInstance().getPuerta(parqueadero.getNombreCampus(), s));
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
