/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.interfaces;

import edu.ucue.jparking.srv.objetos.Puerta;

/**
 *
 * @author ESTUDIANTE
 */
public interface PuertasDAOInterface {
    
    public abstract void addPuerta(Puerta puerta);
    public abstract void delPuerta(String Ubicacion);
    //completar interfce
}
