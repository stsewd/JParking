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
import edu.ucue.jparking.srv.enums.TipoModificacion;
import edu.ucue.jparking.srv.enums.TipoRegistro;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.objetos.Persona;
import edu.ucue.jparking.srv.objetos.registros.Registro;
import edu.ucue.jparking.srv.objetos.registros.RegistroUsuario;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * @author lara
 */
class RegistroUsuarioService {

    RegistrosDAOInterface registrosDAO = null;
    Validaciones validaciones = new Validaciones();
    public void addRegistroUsuario(String cedula) 
            throws UsuarioNoExistenteException, CedulaNoValidaException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
        registrosDAO = RegistrosDAO.getInstance();
        validaciones.validarCedula(cedula);
        Persona persona = UsuariosDAO.getInstance().getUsuario(cedula);
        Registro registro = new RegistroUsuario(persona, TipoModificacion.CREACION);
        registrosDAO.addRegistro(registro);
    }
    /**
     * 
     * @param fechaInicial
     * @param fechaFinal
     * @return 
     */
    public Set<Registro> getRegistroUsuarios(Calendar fechaInicial,Calendar fechaFinal)  throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
        registrosDAO = RegistrosDAO.getInstance();
        return registrosDAO.getRegistros(TipoRegistro.PERSONA, fechaInicial, fechaFinal);
    }
    /**
     * 
     * @return 
     */
    public Set<Registro> getRegistroUsuarios()  throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
        registrosDAO = RegistrosDAO.getInstance();
        return registrosDAO.getRegistros(TipoRegistro.PERSONA);
    }
    
}
