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
import edu.ucue.jparking.dao.interfaces.UsuariosDAOInterface;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.excepciones.TelefonoNoValidoException;
import edu.ucue.jparking.srv.objetos.Estudiante;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.util.Set;

/**
 *
 * @author Franklin Lara
 */
public class UsuarioService {
    UsuariosDAOInterface usuariosDAO = UsuariosDAO.getInstance();
    Validaciones validaciones = new Validaciones();
    
    public void add(String cedula, String nombre, String apellido,String direccion,String telefono, String tipoUsuario) throws UsuarioYaExistenteException, CedulaNoValidaException, TelefonoNoValidoException{
        if(tipoUsuario.equalsIgnoreCase("ESTUDIANTE")){
            EstudianteService estudianteService = new EstudianteService();
            estudianteService.add(cedula, nombre, apellido,direccion,telefono);
        }else if(tipoUsuario.equalsIgnoreCase("DOCENTE")){
            DocenteService docenteService = new DocenteService();
            docenteService.add(cedula, nombre, apellido,direccion,telefono);
        }else if(tipoUsuario.equalsIgnoreCase("EMPLEADO")){
            EmpleadoService empleadoService = new EmpleadoService();
            empleadoService.add(cedula, nombre, apellido,direccion,telefono);
        }else{
            throw new IllegalArgumentException("El argumento tipo usuario no puede estar vacio");
        }
    }
    
    public void del(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException, IllegalArgumentException{
        if(cedula == null || cedula.trim().length() == 0)
            throw new IllegalArgumentException("El argumendo cedula no puede ser vacio.");
        validaciones.validarCedula(cedula);
        usuariosDAO.delUsuario(cedula);
        /*
        String tipoUsuario = usuariosDAO.getUsuario(cedula).getTipoUsuarioString();
        if(tipoUsuario.equalsIgnoreCase("ESTUDIANTE")){
            EstudianteService estudianteService = new EstudianteService();
            estudianteService.del(cedula);
        }else if(tipoUsuario.equalsIgnoreCase("DOCENTE")){
            DocenteService docenteService = new DocenteService();
            docenteService.del(cedula);
        }else if(tipoUsuario.equalsIgnoreCase("EMPLEADO")){
            EmpleadoService empleadoService = new EmpleadoService();
            empleadoService.del(cedula);
        }else{
            throw new IllegalArgumentException("El argumento tipo usuario no puede estar vacio");
        }
        */
    }
    
    public void mod(String cedula, String nombre, String apellido,String direccion,String telefono,boolean estado) throws CedulaNoValidaException, UsuarioNoExistenteException {
        validaciones.validarCedula(cedula);
        usuariosDAO.modUsuario(cedula, nombre, apellido, direccion, telefono, estado);
    }
    
    public Usuario get(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException{
        validaciones.validarCedula(cedula);
        Usuario u =usuariosDAO.getUsuario(cedula); 
        if (u == null)        
            throw new UsuarioNoExistenteException(cedula);
        return u;
        
        /*
        if(tipoUsuario.equalsIgnoreCase("ESTUDIANTE")){
            EstudianteService estudianteService = new EstudianteService();
            return estudianteService.get(cedula);
        }else if(tipoUsuario.equalsIgnoreCase("DOCENTE")){
            DocenteService docenteService = new DocenteService();
            return docenteService.get(cedula);
        }else if(tipoUsuario.equalsIgnoreCase("EMPLEADO")){
            EmpleadoService empleadoService = new EmpleadoService();
            return empleadoService.get(cedula);
        }else{
            throw new IllegalArgumentException("El argumento tipo usuario no puede estar vacio");
        } */
    }
       
    public Set<Usuario> getLista(){
        return UsuariosDAO.getInstance().getUsuarios();
    }
    
    public Set<Usuario> getLista(TipoUsuario tipoUsuario){
        return UsuariosDAO.getInstance().getUsuarios(tipoUsuario);
    }
  
}
