/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.interfaces.RegistrosDAOInterface;
import edu.ucue.jparking.srv.enums.TipoRegistro;
import edu.ucue.jparking.srv.registros.Registro;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public class RegistrosDAO implements RegistrosDAOInterface {
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
    
    @Override
    public void addRegistro(Registro registro){
        registros.add(registro);
    }
    
    @Override
    public Set<Registro> getRegistros(Calendar fechaInicial, Calendar fechaFinal){
        Set<Registro> registros = new LinkedHashSet();
        for(Registro r : getRegistros()){
            if(r.getFecha().before(fechaFinal) && r.getFecha().after(fechaInicial))
                registros.add(r);
            if(r.getFecha().after(fechaFinal))
                break;
        }
        return registros;
    }    
    
    @Override
    public Set<Registro> getRegistros(){
        return this.registros;
    }

    @Override
    public Set<Registro> getRegistros(TipoRegistro tipoRegistro) {
        Set<Registro> registros = new LinkedHashSet();
        for(Registro r : getRegistros()){
            if(r.getTipoRegistro() == tipoRegistro)
                registros.add(r);
        }
        return registros;
    }
        
    @Override
    public Set<Registro> getRegistros(TipoRegistro tipoRegistro, Calendar fechaInicial, Calendar fechaFinal) {
        Set<Registro> registros = new LinkedHashSet();
        for(Registro r : getRegistros(fechaInicial, fechaFinal)){
            if(r.getTipoRegistro() == tipoRegistro)
                registros.add(r);
        }
        return registros;
    }
    
}
