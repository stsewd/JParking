/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.bptree;

import java.io.File;

/**
 *
 * @author Santos Gallegos
 */
public class ObjectSizeException extends Exception {

    public ObjectSizeException(File path) {
        super("Tamaño de objeto mayor al asignado. " + path);
    }
    
}
