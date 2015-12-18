/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.dao.UsuariosDAO;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.objetos.Estudiante;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.util.Set;

/**
 *
 * @author Franklin Lara
 */
public class UsuarioService {
    
    public void add(String cedula, String nombre, String apellido,String direccion,String telefono, TipoUsuario tipoUsuario) throws UsuarioYaExistenteException, CedulaNoValidaException{
        if(tipoUsuario==TipoUsuario.ESTUDIANTE){
            EstudianteService estudianteService = new EstudianteService();
            estudianteService.add(cedula, nombre, apellido,direccion,telefono);
        }else if(tipoUsuario==TipoUsuario.DOCENTE){
            DocenteService docenteService = new DocenteService();
            docenteService.add(cedula, nombre, apellido,direccion,telefono);
        }else if(tipoUsuario==TipoUsuario.EMPLEADO){
            EmpleadoService empleadoService = new EmpleadoService();
            empleadoService.add(cedula, nombre, apellido,direccion,telefono);
        }else{
            throw new IllegalArgumentException("El argumento tipo usuario no puede estar vacio");
        } 
    }
    
    public void del(String cedula,TipoUsuario tipoUsuario) throws UsuarioNoExistenteException, CedulaNoValidaException{
        if(tipoUsuario==TipoUsuario.ESTUDIANTE){
            EstudianteService estudianteService = new EstudianteService();
            estudianteService.del(cedula);
        }else if(tipoUsuario==TipoUsuario.DOCENTE){
            DocenteService docenteService = new DocenteService();
            docenteService.del(cedula);
        }else if(tipoUsuario==TipoUsuario.EMPLEADO){
            EmpleadoService empleadoService = new EmpleadoService();
            empleadoService.del(cedula);
        }else{
            throw new IllegalArgumentException("El argumento tipo usuario no puede estar vacio");
        } 
    }
    
    public void mod(String cedula, String nombre, String apellido,String direccion,String telefono,boolean estado, TipoUsuario tipoUsuario) throws CedulaNoValidaException, UsuarioNoExistenteException {
        if(tipoUsuario==TipoUsuario.ESTUDIANTE){
            EstudianteService estudianteService = new EstudianteService();
            estudianteService.mod(cedula, nombre, apellido,direccion,telefono,estado);
        }else if(tipoUsuario==TipoUsuario.DOCENTE){
            DocenteService docenteService = new DocenteService();
            docenteService.mod(cedula, nombre, apellido,direccion,telefono,estado);
        }else if(tipoUsuario==TipoUsuario.EMPLEADO){
            EmpleadoService empleadoService = new EmpleadoService();
            empleadoService.mod(cedula, nombre, apellido,direccion,telefono,estado);
        }else{
            throw new IllegalArgumentException("El argumento tipo usuario no puede estar vacio");
        } 
    }
    
    public Usuario get(String cedula,TipoUsuario tipoUsuario) throws UsuarioNoExistenteException, CedulaNoValidaException{
        if(tipoUsuario==TipoUsuario.ESTUDIANTE){
            EstudianteService estudianteService = new EstudianteService();
            return estudianteService.get(cedula);
        }else if(tipoUsuario==TipoUsuario.DOCENTE){
            DocenteService docenteService = new DocenteService();
            return docenteService.get(cedula);
        }else if(tipoUsuario==TipoUsuario.EMPLEADO){
            EmpleadoService empleadoService = new EmpleadoService();
            return empleadoService.get(cedula);
        }else{
            throw new IllegalArgumentException("El argumento tipo usuario no puede estar vacio");
        } 
    }
      /*      
    public Set<Usuario> getLista(){
    }
   */
}
