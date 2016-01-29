/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.bptree;

import edu.ucue.jparking.srv.objetos.Persona;
import java.util.Comparator;

/**
 *
 * @author Santos Gallegos
 */
public class ApellidoNombreGenerator implements IndexGenerator<Persona, String>{

    @Override
    public String getKey(Persona obj) {
        return obj.getApellidos() + obj.getNombres() + obj.getCedula();
    }

    @Override
    public Comparator<String> getComparator() {
        return new ComparatorString();
    }
    
}
