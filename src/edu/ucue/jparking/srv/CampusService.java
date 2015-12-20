/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.CampusDAO;
import edu.ucue.jparking.dao.excepciones.CampusExistenteExeption;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.interfaces.CampusDAOInterface;
import edu.ucue.jparking.srv.objetos.Campus;
import java.util.Set;


/**
 * 
 * @author Franklin
 */
public class CampusService {
    Validaciones validar = new Validaciones();
    CampusDAOInterface campusDAO = CampusDAO.getInstancia();
    /**
     * Aniade un campus  
     * @param nombre nombre del campus
     * @param direccion
     * @return
     * @throws CampusExistenteExeption 
     */
    public Campus addCampus(String nombre,String direccion) throws CampusExistenteExeption{
        validar.ValidarCampus(nombre, direccion);
        Campus campus = new Campus(nombre, direccion);
        campusDAO.addCampus(campus);
        return campus;
    }
    /**
     * 
     * @param nombre
     * @return
     * @throws CampusNoExistenteException 
     */
    public Campus getCampus(String nombre) throws CampusNoExistenteException{
        if(nombre==null || nombre.trim().length()==0)
            throw new IllegalArgumentException("El nombre del campus no puede estar vacio");
        return campusDAO.getCampus(nombre);
    }
    public void modCampus(String nombre,String direccion,boolean estado) throws CampusNoExistenteException{
        validar.ValidarCampus(nombre, direccion);
        campusDAO.modCampus(nombre, direccion, estado);
        
    }
    /**
     * borra un campus
     * @param nombre
     * @throws CampusNoExistenteException 
     */
    public void delCampus(String nombre) throws CampusNoExistenteException{
        if(nombre==null || nombre.trim().length()==0)
            throw new IllegalArgumentException("El nombre del campus no puede estar vacio");
        campusDAO.delCampus(nombre);
    }
    /**
     * extrae la lista de campus 
     * @return 
     */
    public Set getCampuss(){
        return (Set) campusDAO.getCampus();
    }
    
    
}
