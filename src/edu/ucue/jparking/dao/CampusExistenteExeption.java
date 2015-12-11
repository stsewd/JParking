/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

/**
 *
 * @author ESTUDIANTE
 */
public class CampusExistenteExeption extends Exception {
    private String nombre;

    public CampusExistenteExeption(String nombre) {
        super("Campus "+ nombre+" Ya existente");
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    
}
