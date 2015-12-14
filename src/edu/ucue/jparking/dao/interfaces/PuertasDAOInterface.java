/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.srv.objetos.Puerta;
import java.util.Set;

/**
 *
 * @author ESTUDIANTE
 */
public interface PuertasDAOInterface {
    
    public abstract void addPuerta(Puerta puerta,String nombreCampus,String idParqueadero);
    public abstract void delPuerta(String id);
    public abstract Set<Puerta> getPuertas();
    public abstract Set<Puerta> getPuertas(String nombreCampus); 
    public abstract void modPuerta(String id,String ubicacion,boolean activa);
    
}
