/**
 * 
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoYaExistenteException;
import edu.ucue.jparking.dao.interfaces.ParqueaderosDAOInterface;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import java.util.HashMap;
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
    public void delParqueadero(String idParqueadero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Parqueadero> getParqueaderos(String nombreCampus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modParqueadero(String idParqueadero, String ubicacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPuertaEntrada(String idParqueadero, String idPuerta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPuertaSalida(String idParqueadero, String idPuerta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delPuertaEntrada(String idParqueadero, String idPuerta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delPuertaSalida(String idParqueadero, String idPuerta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addUsuario(String idParqueadero, String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delUsuario(String idParqueadero, String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
