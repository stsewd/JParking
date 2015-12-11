/**
 * Clase que permite el acceso a los campus registrados en el sistema
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.srv.Campus;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Lara
 */
public class CampusDAO {
    
    private static CampusDAO instancia;
    private Map<String,Campus> mapCampus;

    public CampusDAO() {
        mapCampus= new HashMap<>();
    }
    
    private static CampusDAO getInstancia(){
        if(instancia==null){
            instancia = new CampusDAO();
        }
            return instancia;
        
        
    }
    
    public void addCampus(Campus campus ) throws CampusExistenteExeption
    {
        if (campus == null)
            throw new IllegalArgumentException("El argumento campus no puede estra vacio");
        if (mapCampus.get(campus.getNombre()) != null){
            throw new CampusExistenteExeption(campus.getNombre());
        }
        
        mapCampus.put(campus.getNombre(), campus);
        
    }
    
    public void delCampus(String nombre) throws CampusNoExistenteException{
        
        if (nombre == null)
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        if (mapCampus.get(nombre) == null)
            throw new CampusNoExistenteException(nombre);
        mapCampus.remove(nombre);
    }
    
    public Campus getCampus(String nombre) throws CampusNoExistenteException{
        if (nombre == null)
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        if (mapCampus.get(nombre) != null){
            throw new CampusNoExistenteException(nombre);
        }
        return mapCampus.get(nombre);
    }
    
    public void modCampus(Campus campus) throws CampusNoExistenteException {
        if (campus == null)
            throw new IllegalArgumentException("El argumento campus no puede estra vacio");
        if (mapCampus.get(campus.getNombre()) == null){
            throw new CampusNoExistenteException(campus.getNombre());
        }
        mapCampus.put(campus.getNombre(), campus);
        }
    
    public Map getCampuss(){
        return mapCampus;
    }
}
