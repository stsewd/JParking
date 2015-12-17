/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.srv.objetos.Portero;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Santos Gallegos
 */
public class PorterosDAO {
    //Mapa <Clave de campus, Mapa<Cedula, portero>>
    private static Map<String, Portero> porteros;
    private static PorterosDAO instance;
    
    private PorterosDAO(){
        porteros = new HashMap<>();
    }

    public static PorterosDAO getInstance() {
        if(instance == null)
            instance = new PorterosDAO();
        return instance;
    }
}
