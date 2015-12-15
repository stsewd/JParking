/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.CampusDAO;
import edu.ucue.jparking.dao.excepciones.CampusExistenteExeption;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.srv.objetos.Campus;
import java.util.Set;

/**
 *
 * @author ESTUDIANTE
 */
public class CampusService {
    Validaciones validar = new Validaciones();
    public Campus addCampus(String nombre,String direccion) throws CampusExistenteExeption{
        
        validar.ValidarCampus(nombre, direccion);
        Campus campus = new Campus(nombre, direccion);
        
        CampusDAO.getInstancia().addCampus(campus);
        return campus;
    }
    
        
    public Campus getCampus(String nombre) throws CampusNoExistenteException{
        return CampusDAO.getInstancia().getCampus(nombre);
    }
    
    public void delCampus(String nombre) throws CampusNoExistenteException{
        CampusDAO.getInstancia().delCampus(nombre);
    }
    
    public Set getCampuss(){
        return (Set) CampusDAO.getInstancia().getCampus();
    }
    
    
}
