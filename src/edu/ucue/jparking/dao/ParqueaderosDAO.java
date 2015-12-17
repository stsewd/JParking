/**
 * 
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.srv.objetos.Parqueadero;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Santos Gallegos
 */
public class ParqueaderosDAO {
    
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
}
