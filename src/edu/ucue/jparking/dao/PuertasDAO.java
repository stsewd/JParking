/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.srv.Puerta;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Santos Gallegos
 */
public class PuertasDAO {
    //Mapa <Campus, Mapa<Id Parqueadero, Puerta>>
    private static Map<String, Map<String, Puerta>> puertasEntrada;
    private static Map<String, Map<String, Puerta>> puertasSalida;
    
    private static PuertasDAO instance;
    
    private PuertasDAO(){
        puertasEntrada = new HashMap<>();
        puertasSalida = new HashMap<>();
    }

    public static PuertasDAO getInstance() {
        if(instance == null)
            instance = new PuertasDAO();
        return instance;
    }
    
}
