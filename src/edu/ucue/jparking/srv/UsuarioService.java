/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.UsuarioYaExistenteException;
import edu.ucue.jparking.dao.UsuariosDAO;

/**
 *
 * @author Franklin Lara
 */
public class UsuarioService {
    
    public void addUsuario(String cedula,String nombre, String apellidos) throws UsuarioYaExistenteException{
        Validaciones validar = new Validaciones();
        ValidarDatos(cedula, nombre, apellidos);
        if (validar.ValidarCedula(cedula)){
            Estudiante estudiante = new Estudiante(cedula, nombre, apellidos);
            
            UsuariosDAO.getInstance().addUsuario(estudiante);
            
        }
    }
    
    private void ValidarDatos(String cedula,String nombre, String apellidos)
    {
        if(cedula==null || cedula.trim().length()==0)
            throw new IllegalArgumentException("El campo cedula no puede estar vacio");
        if(nombre==null || nombre.trim().length()==0)
            throw new IllegalArgumentException("El argumento nombre no puede estar vacio");
        if(apellidos==null || apellidos.trim().length()==0)
            throw new IllegalArgumentException("El argumento apellidos no puede estar vacio");
    }
}
