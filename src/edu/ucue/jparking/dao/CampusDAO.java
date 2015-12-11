/**
 * Clase que permite el acceso a los campus registrados en el sistema
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.srv.Campus;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Lara
 */
public class CampusDAO {

    private static CampusDAO instancia;
    private static Map<String,Campus> mapCampus;

    private CampusDAO() {
        mapCampus= new HashMap<>();
    }
    
    public static CampusDAO getInstancia(){
        if(instancia==null){
            instancia = new CampusDAO();
        }
            return instancia;
        
        
    }
    
    public void addCampus(Campus campus ) throws CampusExistenteExeption
    {
        if (mapCampus.get(campus.getNombre()) != null){
            throw new CampusExistenteExeption(campus.getNombre());
        }
        
        mapCampus.put(campus.getNombre(), campus);
        
    }
    
    public void delCampus(String nombre) throws CampusNoExistenteException{
        if (mapCampus.get(nombre) == null)
            throw new CampusNoExistenteException(nombre);
        mapCampus.remove(nombre);
    }
    
    public Campus getCampus(String nombre) throws CampusNoExistenteException{
        if (mapCampus.get(nombre) != null){
            throw new CampusNoExistenteException(nombre);
        }
        return mapCampus.get(nombre);
    }
    
    public void modCampus(String nombre, String ubicacion) throws CampusNoExistenteException {
        if (mapCampus.get(nombre) == null){
            throw new CampusNoExistenteException(nombre);
        }
        Campus cam = mapCampus.get(nombre);
        cam.setDireccion(ubicacion);
        
    }
    
    public Set getCampuss(){
        return (Set) mapCampus.values();
    }

}
