/**
 * 
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.bptree.ObjectSizeException;
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
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Puerta;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    public void addParqueadero(String nombreCampus, Parqueadero parqueadero)
            throws ParqueaderoYaExistenteException, CampusNoExistenteException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        CampusDAO.getInstancia().addParqueadero(nombreCampus, parqueadero);
    }

    @Override
    public void delParqueadero(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, CampusNoExistenteException,
            UsuarioNoExistenteException, UsuarioNoAgregadoException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        CampusDAO.getInstancia().delParqueadero(nombreCampus, idParqueadero);
    }

    @Override
    public Parqueadero getParqueadero(String nombreCampus, String idParqueadero)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        return CampusDAO.getInstancia().getParqueadero(nombreCampus, idParqueadero);
    }

    @Override
    public Set<Parqueadero> getParqueaderos()
            throws IOException, FileNotFoundException, ClassNotFoundException,
            ObjectSizeException
    {
        return CampusDAO.getInstancia().getParqueaderos();
    }

    @Override
    public Set<Parqueadero> getParqueaderos(String nombreCampus)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        return CampusDAO.getInstancia().getParqueaderos(nombreCampus);
    }

    @Override
    public void modParqueadero(String nombreCampus, String idParqueadero, String ubicacion, int numLugares, boolean estado)
            throws ParqueaderoNoExistenteException, CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        CampusDAO.getInstancia().modParqueadero(nombreCampus, idParqueadero, ubicacion, numLugares, estado);
    }

    @Override
    public void addPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta)
            throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CampusNoExistenteException,
            PuertaYaAgregadaException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        CampusDAO.getInstancia().addPuertaEntradaParqueadero(nombreCampus, idParqueadero, idPuerta);
    }

    @Override
    public void addPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta)
            throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CampusNoExistenteException,
            PuertaYaAgregadaException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        CampusDAO.getInstancia().addPuertaSalidaParqueadero(nombreCampus, idParqueadero, idPuerta);
    }

    @Override
    public void delPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta)
            throws PuertaNoExistenteException, ParqueaderoNoExistenteException,
            CampusNoExistenteException, PuertaNoAgregadaException, IOException, 
            FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        CampusDAO.getInstancia().delPuertaEntradaParqueadero(nombreCampus, idParqueadero, idPuerta);
    }

    @Override
    public void delPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta)
            throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CampusNoExistenteException,
            PuertaNoAgregadaException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        CampusDAO.getInstancia().delPuertaSalidaParqueadero(nombreCampus, idParqueadero, idPuerta);
    }

    @Override
    public void addUsuario(String nombreCampus, String idParqueadero, String cedula)
            throws ParqueaderoNoExistenteException, UsuarioNoExistenteException, UsuarioYaAgregadoException,
            CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException,
            ObjectSizeException
    {
        CampusDAO.getInstancia().addUsuarioParqueadero(nombreCampus, idParqueadero, cedula);
    }

    @Override
    public void delUsuario(String nombreCampus, String idParqueadero, String cedula)
            throws UsuarioNoExistenteException, UsuarioNoAgregadoException, CampusNoExistenteException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException 
    {
        CampusDAO.getInstancia().delUsuarioParqueadero(nombreCampus, idParqueadero, cedula);
    }

    @Override
    public Set<Puerta> getPuertasEntrada(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, CampusNoExistenteException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        return CampusDAO.getInstancia().getPuertasEntradaParqueadero(nombreCampus, idParqueadero);
    }

    @Override
    public Set<Puerta> getPuertasSalida(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, CampusNoExistenteException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        return CampusDAO.getInstancia().getPuertasSalida(nombreCampus, idParqueadero);
    }

    @Override
    public Set<Usuario> getUsuarios(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, UsuarioNoExistenteException,
            CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        return CampusDAO.getInstancia().getUsuariosParqueadero(nombreCampus, idParqueadero);
    }
}
