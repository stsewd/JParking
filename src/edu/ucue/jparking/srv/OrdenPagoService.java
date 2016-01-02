/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.UsuariosDAO;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.interfaces.UsuariosDAOInterface;
import edu.ucue.jparking.srv.enums.TipoTramite;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.ContratoNoEstablecidoException;
import edu.ucue.jparking.srv.excepciones.PagoYaRealizadoException;
import edu.ucue.jparking.srv.excepciones.FueraDelDiaDePagoException;
import edu.ucue.jparking.srv.objetos.OrdenPago;
import edu.ucue.jparking.srv.excepciones.UsuarioNoRegistradoEnUnParqueaderoException;
import java.util.Calendar;

/**
 *
 * @author Franklin Lara
 */
public class OrdenPagoService {
    
    private final static RegistroService registroService = new RegistroService();
    
    /**
     * 
     */
    UsuariosDAOInterface usuariosDAO = UsuariosDAO.getInstance();
    Calendar fechaActual= Calendar.getInstance();
    Validaciones validaciones = new Validaciones();

    
    
    public OrdenPago getOrdenPago(String cedula) throws CedulaNoValidaException, UsuarioNoExistenteException, ContratoNoEstablecidoException, FueraDelDiaDePagoException, UsuarioNoRegistradoEnUnParqueaderoException{

        validaciones.validarCedula(cedula);
        OrdenPago ordenPago = usuariosDAO.getUsuario(cedula).generarOrdenPago();
        //Registro
        registroService.add(usuariosDAO.getUsuario(cedula).getRegistro(TipoTramite.EMISION));
        //Fin registro
        return ordenPago;
    }
    
    public void pagarOrdenPago(String cedula) throws CedulaNoValidaException, UsuarioNoExistenteException, PagoYaRealizadoException{
        validaciones.validarCedula(cedula);
        usuariosDAO.getUsuario(cedula).cancelarPago();
        //Registro
        registroService.add(usuariosDAO.getUsuario(cedula).getRegistro(TipoTramite.COBRO));
    }
}
