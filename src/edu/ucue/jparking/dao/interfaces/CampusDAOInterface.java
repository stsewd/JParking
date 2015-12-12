/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.srv.objetos.Campus;
import java.util.Set;

/**
 *
 * @author ESTUDIANTE
 */
public interface CampusDAOInterface {
    
    public abstract void addCampus(Campus campus);
    public abstract void delCampus(String nombre);
    public abstract Campus getCampus(String nombre);
    public abstract void modCampus(String nombre,String ubicacion);
    public abstract Set<Campus> getCampus();
}
