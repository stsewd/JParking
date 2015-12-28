/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.srv.excepciones.FechaFinalMenorAFechaInicialException;
import edu.ucue.jparking.srv.excepciones.FechaInicialIgualAFechaFinalException;
import edu.ucue.jparking.srv.excepciones.FechaInicialMayorAFechaFinalException;
import edu.ucue.jparking.dao.RegistrosDAO;
import edu.ucue.jparking.dao.excepciones.RegistroNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.srv.enums.TipoRegistro;
import edu.ucue.jparking.srv.enums.TipoTramite;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.objetos.registros.Registro;
import edu.ucue.jparking.srv.objetos.registros.RegistroPagos;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * @author lara
 */
public class RegistroService {
    /**
     * 
     * @param cedula
     * @param tipoRegistro
     * @param tipoTramite
     * @throws UsuarioNoExistenteException 
     */
    public void add(String cedula,TipoRegistro tipoRegistro,TipoTramite tipoTramite) throws UsuarioNoExistenteException, CedulaNoValidaException{
        if(tipoRegistro==TipoRegistro.USUARIO){
            RegistroUsuarioService registroUsuarioService = new RegistroUsuarioService();
            registroUsuarioService.addRegistroUsuario(cedula);
        }else if(tipoRegistro==TipoRegistro.ACCESO_PARQUEADERO){
            RegistroAccesoParqueaderoService accesoParqueaderoService = new RegistroAccesoParqueaderoService();
            accesoParqueaderoService.addAcceso(cedula);
        }else if(tipoRegistro==TipoRegistro.PAGOS){
            RegistroPagosService registroPagosService = new RegistroPagosService();
            registroPagosService.add(cedula, tipoTramite);
        }else{
            throw new IllegalArgumentException("Argumento no valido");
        }
    }
    /**
     * 
     * @param tipoRegistro
     * @param fechaInicio
     * @param fechaFinal
     * @return 
     * @throws edu.ucue.jparking.srv.FechaInicialMayorAFechaFinalException 
     * @throws edu.ucue.jparking.srv.FechaFinalMenorAFechaInicialException 
     */
    public Set<Registro> get(TipoRegistro tipoRegistro,Calendar fechaInicio,Calendar fechaFinal) throws FechaInicialMayorAFechaFinalException, FechaFinalMenorAFechaInicialException, FechaInicialIgualAFechaFinalException{
        
        Validaciones.validarFecha(fechaInicio, fechaFinal);
        
        if(tipoRegistro==TipoRegistro.USUARIO){
            RegistroUsuarioService registroUsuarioService = new RegistroUsuarioService();
            return registroUsuarioService.getRegistroUsuarios(fechaInicio, fechaFinal);
        }else if(tipoRegistro==TipoRegistro.ACCESO_PARQUEADERO){
            RegistroAccesoParqueaderoService accesoParqueaderoService = new RegistroAccesoParqueaderoService();
            return accesoParqueaderoService.getAcceso(fechaInicio, fechaFinal);
        }else if(tipoRegistro==TipoRegistro.PAGOS){
            RegistroPagosService registroPagosService = new RegistroPagosService();
            return registroPagosService.getRegistro(fechaInicio, fechaFinal);
        }else{
            throw new IllegalArgumentException("Argumento no valido");
        }
    }
    
    /**
     * 
     * @param tipoRegistro
     * @return 
     */
    public Set<Registro> get(TipoRegistro tipoRegistro){
        if(tipoRegistro==TipoRegistro.USUARIO){
            RegistroUsuarioService registroUsuarioService = new RegistroUsuarioService();
            return registroUsuarioService.getRegistroUsuarios();
        }else if(tipoRegistro==TipoRegistro.ACCESO_PARQUEADERO){
            RegistroAccesoParqueaderoService accesoParqueaderoService = new RegistroAccesoParqueaderoService();
            return accesoParqueaderoService.getAcceso();
        }else if(tipoRegistro==TipoRegistro.PAGOS){
            RegistroPagosService registroPagosService = new RegistroPagosService();
            return registroPagosService.getRegistro();
        }else{
            throw new IllegalArgumentException("Argumento no valido");
        }
    }
    
    /**
     * 
     * @return 
     */
    public Set<Registro> get(){
        return RegistrosDAO.getInstance().getRegistros();
    }
    
    /**
     * 
     * @param fechaInicio
     * @param fechaFinal
     * @return 
     */
    public Set<Registro>  get(Calendar fechaInicio, Calendar fechaFinal) throws FechaInicialMayorAFechaFinalException, FechaFinalMenorAFechaInicialException, FechaInicialIgualAFechaFinalException{
        
        Validaciones.validarFecha(fechaInicio, fechaFinal);
        
        return RegistrosDAO.getInstance().getRegistros(fechaInicio, fechaFinal);
    }

    public Registro get(String idRegistro) throws RegistroNoExistenteException {
        int id = 0;
        try{
            id = Integer.parseInt(idRegistro);
        }
        catch (Exception ex){
            throw new IllegalArgumentException("Id de registro no valido.");
        }
        return RegistrosDAO.getInstance().getRegistro(id);
    }
}
