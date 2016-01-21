/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.bptree.BPTree;
import edu.ucue.jparking.dao.bptree.BPTreeMap;
import edu.ucue.jparking.dao.bptree.ComparatorInt;
import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.RegistroNoExistenteException;
import edu.ucue.jparking.dao.interfaces.RegistrosDAOInterface;
import edu.ucue.jparking.srv.enums.TipoRegistro;
import edu.ucue.jparking.srv.objetos.registros.Registro;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public class RegistrosDAO implements RegistrosDAOInterface {
    private static RegistrosDAO instance;
    // private static Map<Integer, Registro> registros;
    private static BPTreeMap<Integer, Registro> registros;
    private static final String dataPath = "data/registros.dat";
    private static final String indiceNum = "data/registros_num_index.dat";
    private static final int objSize = 9999;
    
    private RegistrosDAO() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        // registros = new LinkedHashMap<>();
        registros = BPTreeMap.getTree(3, new ComparatorInt(), dataPath, indiceNum, objSize, 2500);
    }

    public static RegistrosDAO getInstance() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        if (instance == null)
            instance = new RegistrosDAO();
        return instance;
    }
    
    @Override
    public void addRegistro(Registro registro) throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
        registro.setNumeroRegistro(registros.size());
        registros.put(registro.getNumeroRegistro(), registro);
    }
    
    @Override
    public Set<Registro> getRegistros(Calendar fechaInicial, Calendar fechaFinal) throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
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
    public Set<Registro> getRegistros() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
        return new LinkedHashSet<>(registros.values());
    }

    @Override
    public Set<Registro> getRegistros(TipoRegistro tipoRegistro)throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
        Set<Registro> registros = new LinkedHashSet();
        for(Registro r : getRegistros()){
            if(r.getTipoRegistro() == tipoRegistro)
                registros.add(r);
        }
        return registros;
    }
        
    @Override
    public Set<Registro> getRegistros(TipoRegistro tipoRegistro, Calendar fechaInicial, Calendar fechaFinal) throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        Set<Registro> registros = new LinkedHashSet();
        for(Registro r : getRegistros(fechaInicial, fechaFinal)){
            if(r.getTipoRegistro() == tipoRegistro)
                registros.add(r);
        }
        return registros;
    }

    @Override
    public Registro getRegistro(Integer numeroRegistro) throws RegistroNoExistenteException , IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        Registro registro = registros.get(numeroRegistro);
        if(registro == null)
            throw new RegistroNoExistenteException(numeroRegistro);
        return registro;
    }
}
