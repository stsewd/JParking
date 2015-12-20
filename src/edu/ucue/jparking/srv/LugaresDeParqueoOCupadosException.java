/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

/**
 *
 * @author Santos Gallegos
 */
public class LugaresDeParqueoOCupadosException extends Exception {

    public LugaresDeParqueoOCupadosException(int numLugaresOcupados) {
        super("Actualmente " + numLugaresOcupados + " lugares de parqueo se encuentran ocupados.");
    }
    
}
