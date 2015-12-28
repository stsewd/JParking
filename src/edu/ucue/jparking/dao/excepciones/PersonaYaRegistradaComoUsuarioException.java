/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao.excepciones;

/**
 *
 * @author Santos Gallegos
 */
public class PersonaYaRegistradaComoUsuarioException extends Exception {

    public PersonaYaRegistradaComoUsuarioException(String cedula) {
        super("La persona de cedula " + cedula + " ya ha sido registrada como usuario.");
    }
    
}
