/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.RegistrosDAO;
import edu.ucue.jparking.dao.UsuariosDAO;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.srv.enums.TipoAcceso;
import edu.ucue.jparking.srv.enums.TipoRegistro;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.objetos.Persona;
import edu.ucue.jparking.srv.registros.Registro;
import edu.ucue.jparking.srv.registros.RegistroAccesoParqueadero;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * @author lara
 */
public class RegistroAccesoParqueaderoService {
    /**
     * 
     * @param cedula
     * @throws UsuarioNoExistenteException 
     */
    public void addAcceso(String cedula) throws UsuarioNoExistenteException{
        Persona persona = UsuariosDAO.getInstance().getUsuario(cedula);
        Registro registro = new RegistroAccesoParqueadero(persona, TipoAcceso.ENTRADA);
        RegistrosDAO.getInstance().addRegistro(registro);
    }
    /**
     * 
     * 
     * @return 
     */
    public Set<Registro> getAcceso(){
        return RegistrosDAO.getInstance().getRegistros(TipoRegistro.ACCESO_PARQUEADERO);
    }
    /**
     * 
     * @param fechaInicio
     * @param fechaFinal
     * @return 
     */
    public Set<Registro> getAcceso(Calendar fechaInicio,Calendar fechaFinal){
        return RegistrosDAO.getInstance().getRegistros(TipoRegistro.ACCESO_PARQUEADERO, fechaInicio, fechaFinal);
    }
}
