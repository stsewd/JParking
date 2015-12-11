/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.srv.registros.Registro;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public class RegistrosDAO {
    private static RegistrosDAO instance;
    private static Set<Registro> registros;
    
    private RegistrosDAO(){
        registros = new LinkedHashSet<>();
    }

    public static RegistrosDAO getInstance() {
        if (instance == null)
            instance = new RegistrosDAO();
        return instance;
    }
    
    //Funciones CRUD
    
    public void addRegistro(Registro registro){
        registros.add(registro);
    }
    
    public Set getRegistros(Calendar fechaInicial, Calendar fechaFinal){
        //Implementar, retorna un set de los registros creados entre
        //fechaInicial y fechaFinal
        Set<Registro> registros = new LinkedHashSet();
        return registros;
    }    
    
    public Set getRegistros(){
        return this.registros;
    }
    
}
