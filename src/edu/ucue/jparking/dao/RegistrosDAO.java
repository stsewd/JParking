/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.srv.Registro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santos Gallegos
 */
public class RegistrosDAO {
    private static RegistrosDAO instance;
    private static List<Registro> registros;
    
    private RegistrosDAO(){
        registros = new ArrayList<>();
    }

    public static RegistrosDAO getInstance() {
        if (instance == null)
            instance = new RegistrosDAO();
        return instance;
    }
    
    //Crear CRUD
    
}
