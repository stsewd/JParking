/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.RegistrosDAO;
import edu.ucue.jparking.dao.UsuariosDAO;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.srv.enums.TipoModificacion;
import edu.ucue.jparking.srv.enums.TipoRegistro;
import edu.ucue.jparking.srv.enums.TipoTramite;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.objetos.Persona;
import edu.ucue.jparking.srv.registros.Registro;
import edu.ucue.jparking.srv.registros.RegistroUsuario;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * @author lara
 */
public class RegistroUsuarioService {
    /**
     * 
     * @param cedula
     * @throws UsuarioNoExistenteException 
     */
    public void addRegistroUsuario(String cedula) throws UsuarioNoExistenteException{
        Persona persona = UsuariosDAO.getInstance().getUsuario(cedula);
        Registro registro = new RegistroUsuario(persona, TipoModificacion.CREACION);
        RegistrosDAO.getInstance().addRegistro(registro);
    }
    /**
     * 
     * @param fechaInicial
     * @param fechaFinal
     * @return 
     */
    public Set<Registro> getRegistroUsuarios(Calendar fechaInicial,Calendar fechaFinal){
        return RegistrosDAO.getInstance().getRegistros(TipoRegistro.USUARIO, fechaInicial, fechaFinal);
    }
    /**
     * 
     * @return 
     */
    public Set<Registro> getRegistroUsuarios(){
        return RegistrosDAO.getInstance().getRegistros(TipoRegistro.USUARIO);
    }
    
}