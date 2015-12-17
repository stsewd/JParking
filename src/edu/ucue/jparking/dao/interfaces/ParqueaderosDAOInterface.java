/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Puerta;
import java.util.Set;

/**
 *
 * @author stsewd
 */
public interface ParqueaderosDAOInterface {
    //NO USAR AÚNNNN!!!!!
    public void addParqueadero();
    public void delParqueadero();
    public Parqueadero getParqueadero();
    public Set<Parqueadero> getParqueaderos();
    public Set<Parqueadero> getParqueaderos(String nombreCampus);
    public void modParqueadero();
    public void addPuertaEntrada(String idCampus, String idPuerta);
    
}
