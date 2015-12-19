/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.RegistrosDAO;
import edu.ucue.jparking.dao.UsuariosDAO;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.srv.enums.TipoRegistro;
import edu.ucue.jparking.srv.enums.TipoTramite;
import edu.ucue.jparking.srv.objetos.Usuario;
import edu.ucue.jparking.srv.registros.Registro;
import edu.ucue.jparking.srv.registros.RegistroPagos;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * @author lara
 */
public class RegistroPagosService {
    /**
     * 
     * @param cedula
     * @param tipoTramite
     * @throws UsuarioNoExistenteException 
     */
    public void add(String cedula,TipoTramite tipoTramite) throws UsuarioNoExistenteException{
        Usuario persona = UsuariosDAO.getInstance().getUsuario(cedula);
        RegistroPagos registro =new RegistroPagos(persona, tipoTramite);
        RegistrosDAO.getInstance().addRegistro(registro);
        
    }
    /**
     * 
     * @param tipoRegistro
     * @param fechaInicial
     * @param fechaFinal
     * @return 
     */
    public Set<RegistroPagos> getRegistro(TipoRegistro tipoRegistro,Calendar fechaInicial, Calendar fechaFinal){
        return RegistrosDAO.getInstance().getRegistros(tipoRegistro., fechaInicial, fechaFinal);
    }
    
    //public Set<RegistroPagos>
    
    
}

