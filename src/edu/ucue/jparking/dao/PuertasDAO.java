/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.srv.objetos.Puerta;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Santos Gallegos
 */
public class PuertasDAO {
    //Clase innecesaria?
    //Mapa <Campus, Mapa<Id Parqueadero, Id de puerta>>
    private static Map<String, Map<String, String>> puertasEntrada;
    //Mapa <Campus, Mapa<Id Parqueadero, Id de puerta>>
    private static Map<String, Map<String, String>> puertasSalida;
    
    //Mapa <Id de puerta, puerta>
    private static Map<String, Puerta> puertas;
    
    private static PuertasDAO instance;
    
    private PuertasDAO(){
        puertas = new HashMap<>();
        puertasEntrada = new HashMap<>();
        puertasSalida = new HashMap<>();
    }

    public static PuertasDAO getInstance() {
        if(instance == null)
            instance = new PuertasDAO();
        return instance;
    }
    
}
