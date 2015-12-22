/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.UsuariosDAO;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.interfaces.UsuariosDAOInterface;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.PagoYaRealizadoException;
import edu.ucue.jparking.srv.objetos.OrdenPago;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.util.Calendar;

/**
 *
 * @author lara
 */
public class OrdenPagoService {
    
    /**
     * 
     */
    UsuariosDAOInterface usuariosDAO = UsuariosDAO.getInstance();
    Calendar fechaActual= Calendar.getInstance();
    Validaciones validaciones = new Validaciones();
    /**
     * 
     * @param cedula
     * @return
     * @throws CedulaNoValidaException
     * @throws UsuarioNoExistenteException 
     */
    public OrdenPago getOrdenPago(String cedula) throws CedulaNoValidaException, UsuarioNoExistenteException{
        validaciones.validarCedula(cedula);
        return usuariosDAO.getUsuario(cedula).generarOrdenPago();
    }
    
    /**
     * 
     * @param cedula
     * @throws CedulaNoValidaException
     * @throws UsuarioNoExistenteException
     * @throws PagoYaRealizadoException 
     */
    public void pagarOrdenPago(String cedula) throws CedulaNoValidaException, UsuarioNoExistenteException, PagoYaRealizadoException{
        validaciones.validarCedula(cedula);
        usuariosDAO.getUsuario(cedula).cancelarPago();
    }
    
    
    
}
