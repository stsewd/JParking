/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.RegistrosDAO;
import edu.ucue.jparking.dao.UsuariosDAO;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.interfaces.RegistrosDAOInterface;
import edu.ucue.jparking.srv.enums.TipoRegistro;
import edu.ucue.jparking.srv.enums.TipoTramite;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.objetos.Usuario;
import edu.ucue.jparking.srv.objetos.registros.Registro;
import edu.ucue.jparking.srv.objetos.registros.RegistroPagos;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * @author Franklin Lara
 */
public class RegistroPagosService {
    /**
     * 
     * @param cedula
     * @param tipoTramite
     * @throws UsuarioNoExistenteException 
     */
    RegistrosDAOInterface registrosDAO = RegistrosDAO.getInstance();
    Validaciones validaciones = new Validaciones();
    public void add(String cedula,TipoTramite tipoTramite) throws UsuarioNoExistenteException, CedulaNoValidaException{
        validaciones.validarCedula(cedula);
        Usuario persona = UsuariosDAO.getInstance().getUsuario(cedula);
        RegistroPagos registro =new RegistroPagos(persona, tipoTramite);
        registrosDAO.addRegistro(registro);
        
    }
    
    /**
     * 
     * @param fechaInicial
     * @param fechaFinal
     * @return 
     */
    public Set<Registro> getRegistro(Calendar fechaInicial, Calendar fechaFinal){
        return registrosDAO.getRegistros(TipoRegistro.PAGOS, fechaInicial, fechaFinal);
    }
    /**
     * retorna todos los 
     * @return 
     */
    public Set<Registro> getRegistro(){
        return registrosDAO.getRegistros(TipoRegistro.PAGOS);
    }
    
    
}

