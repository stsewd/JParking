/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.excepciones;

/**
 *
 * @author Franklin Lara
 */
public class CampusExistenteExeption extends Exception {
    private final String nombre;

    public CampusExistenteExeption(String nombre) {
        super("Campus "+ nombre + " Ya existente");
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    
}
