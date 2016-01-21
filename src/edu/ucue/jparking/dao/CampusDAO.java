/**
 * Clase que permite el acceso a los campus registrados en el sistema
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.bptree.BPTreeMap;
import edu.ucue.jparking.dao.bptree.ComparatorString;
import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.CampusExistenteExeption;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoAgregadoException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.interfaces.CampusDAOInterface;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Franklin Lara
 * @author Santos Gallegos
 */
public class CampusDAO implements CampusDAOInterface {
    private static CampusDAO instancia;
    
    // Mapa <Nombre de campus, campus>
    // private static Map<String, Campus> mapCampus;
    private static BPTreeMap<String, Campus> mapCampus;
    private static final String dataPath = "data/campus.dat";
    private static final String indiceNombreCampusPath = "data/campus_nombre_index.dat";
    private static final int objSize = 9999;
    
    private CampusDAO()
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        mapCampus = BPTreeMap.getTree(3, new ComparatorString(), dataPath, indiceNombreCampusPath, objSize, 2500);
    }

    public static CampusDAO getInstancia()
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        if (instancia == null) {
            instancia = new CampusDAO();
        }
        return instancia;
    }

    @Override
    public void addCampus(Campus campus)
            throws CampusExistenteExeption, IOException, FileNotFoundException, ClassNotFoundException,
            ObjectSizeException
    {
        if (mapCampus.get(campus.getNombre()) != null) {
            throw new CampusExistenteExeption(campus.getNombre());
        }

        mapCampus.put(campus.getNombre(), campus);
    }

    @Override
    public void delCampus(String nombreCampus)
            throws CampusNoExistenteException, ParqueaderoNoExistenteException,
            UsuarioNoExistenteException, UsuarioNoAgregadoException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        
        if (mapCampus.get(nombreCampus) == null) {
            throw new CampusNoExistenteException(nombreCampus);
        }
        
        /*************************
         * Eliminar depencias:
         * 
         * Parqueaderos del campus a eliminar donde esta registrado un usuario.*
         * 
        ***************************/
        for(Parqueadero p : ParqueaderosDAO.getInstance().getParqueaderos(nombreCampus)){
            ParqueaderosDAO.getInstance().delParqueadero(nombreCampus, p.getId());
        }
        
        mapCampus.remove(nombreCampus);
    }

    @Override
    public Campus getCampus(String nombre)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException
    {
        if (mapCampus.get(nombre) == null) {
            throw new CampusNoExistenteException(nombre);
        }
        return mapCampus.get(nombre);
    }

    @Override
    public void modCampus(String nombre, String ubicacion, boolean estado)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException
    {
        if (mapCampus.get(nombre) == null) {
            throw new CampusNoExistenteException(nombre);
        }
        Campus cam = mapCampus.get(nombre);
        cam.setDireccion(ubicacion);
        cam.setActivo(estado);
    }

    @Override
    public Set<Campus> getCampus()
            throws IOException, FileNotFoundException, ClassNotFoundException
    {
        return new TreeSet<>(mapCampus.values());
    }
}
