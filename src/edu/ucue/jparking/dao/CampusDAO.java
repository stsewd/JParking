/**
 * Clase que permite el acceso a los campus registrados en el sistema
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.excepciones.CampusExistenteExeption;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.interfaces.CampusDAOInterface;
import edu.ucue.jparking.srv.objetos.Campus;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Franklin Lara
 */
public class CampusDAO implements CampusDAOInterface {
    private static CampusDAO instancia;
    
    //Mapa <Nombre de campus, campus>
    private static Map<String, Campus> mapCampus;
    
    private CampusDAO() {
        mapCampus = new TreeMap<>();
    }

    public static CampusDAO getInstancia() {
        if (instancia == null) {
            instancia = new CampusDAO();
        }
        return instancia;
    }

    @Override
    public void addCampus(Campus campus) throws CampusExistenteExeption {
        if (mapCampus.get(campus.getNombre()) != null) {
            throw new CampusExistenteExeption(campus.getNombre());
        }

        mapCampus.put(campus.getNombre(), campus);
    }

    @Override
    public void delCampus(String nombre) throws CampusNoExistenteException {
        if (mapCampus.get(nombre) == null) {
            throw new CampusNoExistenteException(nombre);
        }
        mapCampus.remove(nombre);
    }

    @Override
    public Campus getCampus(String nombre) throws CampusNoExistenteException {
        if (mapCampus.get(nombre) == null) {
            throw new CampusNoExistenteException(nombre);
        }
        return mapCampus.get(nombre);
    }

    @Override
    public void modCampus(String nombre, String ubicacion,boolean estado) throws CampusNoExistenteException {
        if (mapCampus.get(nombre) == null) {
            throw new CampusNoExistenteException(nombre);
        }
        Campus cam = mapCampus.get(nombre);
        cam.setDireccion(ubicacion);
        cam.setActivo(estado);
    }

    @Override
    public Set<Campus> getCampus() {
        return new TreeSet<>(mapCampus.values());
    }
}
