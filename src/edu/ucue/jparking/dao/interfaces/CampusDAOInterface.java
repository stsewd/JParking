/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.dao.excepciones.CampusExistenteExeption;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.srv.objetos.Campus;
import java.util.Set;

/**
 *
 * @author Franklin Lara
 */
public interface CampusDAOInterface {
    
    public abstract void addCampus(Campus campus) throws CampusExistenteExeption;
    public abstract void delCampus(String nombre) throws CampusNoExistenteException;
    public abstract Campus getCampus(String nombre)  throws CampusNoExistenteException;
    public abstract void modCampus(String nombre, String ubicacion)  throws CampusNoExistenteException;
    public abstract Set<Campus> getCampus();
}
