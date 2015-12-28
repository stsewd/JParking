/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.excepciones.RegistroNoExistenteException;
import edu.ucue.jparking.dao.interfaces.RegistrosDAOInterface;
import edu.ucue.jparking.srv.enums.TipoRegistro;
import edu.ucue.jparking.srv.objetos.registros.Registro;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public class RegistrosDAO implements RegistrosDAOInterface {
    private static RegistrosDAO instance;
    private static Map<Integer, Registro> registros;
    
    private RegistrosDAO(){
        registros = new HashMap<>();
    }

    public static RegistrosDAO getInstance() {
        if (instance == null)
            instance = new RegistrosDAO();
        return instance;
    }
    
    @Override
    public void addRegistro(Registro registro){
        registro.setNumeroRegistro(registros.size());
        registros.put(registro.getNumeroRegistro(), registro);
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
        return new HashSet<>(this.registros.values());
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

    @Override
    public Registro getRegistro(Integer numeroRegistro) throws RegistroNoExistenteException {
        Registro registro = registros.get(numeroRegistro);
        if(registro == null)
            throw new RegistroNoExistenteException(numeroRegistro);
        return registro;
    }
}
