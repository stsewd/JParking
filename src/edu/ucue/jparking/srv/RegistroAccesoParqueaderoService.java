/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.RegistrosDAO;
import edu.ucue.jparking.dao.UsuariosDAO;
import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.interfaces.RegistrosDAOInterface;
import edu.ucue.jparking.srv.enums.TipoAcceso;
import edu.ucue.jparking.srv.enums.TipoRegistro;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.objetos.Persona;
import edu.ucue.jparking.srv.objetos.registros.Registro;
import edu.ucue.jparking.srv.objetos.registros.RegistroAccesoParqueadero;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * @author lara
 */
class RegistroAccesoParqueaderoService {
    /**
     * 
     * @param cedula
     * @throws UsuarioNoExistenteException 
     */
    RegistrosDAOInterface registrosDAO = null;
    Validaciones validaciones = new Validaciones();
    
    public void addAcceso(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
        registrosDAO = RegistrosDAO.getInstance();
        validaciones.validarCedula(cedula);
        Persona persona = UsuariosDAO.getInstance().getUsuario(cedula);
        Registro registro = new RegistroAccesoParqueadero(persona, TipoAcceso.ACCESO);
        registrosDAO.addRegistro(registro);
    }
    
    public Set<Registro> getAcceso() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
        registrosDAO = RegistrosDAO.getInstance();
        return registrosDAO.getRegistros(TipoRegistro.ACCESO_PARQUEADERO);
    }
    
    public Set<Registro> getAcceso(Calendar fechaInicio,Calendar fechaFinal) throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
        //cambiar esta parte para mes dia a;o 
        registrosDAO = RegistrosDAO.getInstance();
        return registrosDAO.getRegistros(TipoRegistro.ACCESO_PARQUEADERO, fechaInicio, fechaFinal);
    }
}

