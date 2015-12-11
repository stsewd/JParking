/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.excepciones;

/**
 *
 * @author ESTUDIANTE
 */
public class CampusNoExistenteException extends Exception {
    private String nombre;
    public CampusNoExistenteException(String nombre) {
        super("El capus "+nombre+" No existe");
        this.nombre=nombre;
    }
    
}
