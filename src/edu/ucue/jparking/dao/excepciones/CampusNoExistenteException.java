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
public class CampusNoExistenteException extends Exception {
    private final String nombre;
    public CampusNoExistenteException(String nombre) {
        super("Campus " + nombre + " no existe");
        this.nombre=nombre;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    
}
