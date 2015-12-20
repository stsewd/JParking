/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.ParqueaderosDAO;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoYaExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaYaAgregadaException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoAgregadoException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaAgregadoException;
import edu.ucue.jparking.dao.interfaces.ParqueaderosDAOInterface;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Puerta;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.util.Set;

/**
 *
 * @author lara
 */
public class ParqueaderoService {
    /**
     * 
     */
    Validaciones validaciones = new Validaciones();
    ParqueaderosDAOInterface parqueaderoDAO = ParqueaderosDAO.getInstance();
    /**
     * 
     * @param ubicacion
     * @param numeroLugares
     * @param id
     * @param nombreCampus
     * @throws ParqueaderoYaExistenteException
     * @throws CampusNoExistenteException 
     */
    public void addParqueadero( String ubicacion, int numeroLugares, String id, String nombreCampus) throws ParqueaderoYaExistenteException, CampusNoExistenteException, CodigoNoValidoException{
        validaciones.ValidarParqueadero(ubicacion, numeroLugares, id, nombreCampus);
        validaciones.validarCodigo(id);
        Parqueadero parqueadero = new Parqueadero(ubicacion, numeroLugares, id, nombreCampus);
        parqueaderoDAO.addParqueadero(nombreCampus, parqueadero);
    }
    /**
     * 
     * @param idParqueadero
     * @throws ParqueaderoNoExistenteException
     * @throws CampusNoExistenteException 
     */
    public void delParqueadero(String idParqueadero) throws ParqueaderoNoExistenteException, CampusNoExistenteException, CodigoNoValidoException{
        validaciones.validarCodigo(idParqueadero);
        parqueaderoDAO.delParqueadero(idParqueadero);
    }
    /**
     * 
     * @param idParqueadero
     * @return
     * @throws ParqueaderoNoExistenteException 
     */
    public Parqueadero getParqueadero(String idParqueadero) throws ParqueaderoNoExistenteException, CodigoNoValidoException{
        validaciones.validarCodigo(idParqueadero);
        return parqueaderoDAO.getParqueadero(idParqueadero);
    }
    /**
     * 
     * @return
     * @throws CampusNoExistenteException 
     */
    public Set<Parqueadero> getParqueaderos() throws CampusNoExistenteException{
        return parqueaderoDAO.getParqueaderos();
    }
    /**
     * 
     * @param idCampus
     * @return
     * @throws CampusNoExistenteException 
     */
    public Set<Parqueadero> getParqueaderos(String idCampus) throws CampusNoExistenteException{
        if(idCampus==null || idCampus.trim().length() == 0)
            throw new IllegalArgumentException("El id campus no puede estar vacio");
        return parqueaderoDAO.getParqueaderos(idCampus);
    }
    /**
     * 
     * @param idParqueadero
     * @param ubicacion
     * @throws ParqueaderoNoExistenteException 
     */
    public void modParqueadero(String idParqueadero, String ubicacion) throws ParqueaderoNoExistenteException, CodigoNoValidoException{
        validaciones.validarCodigo(ubicacion);
        if(ubicacion==null || ubicacion.trim().length()==0)
            throw new IllegalArgumentException("La ubicacion no puede ser nula");
        parqueaderoDAO.modParqueadero(idParqueadero, ubicacion);
    }
    /**
     * 
     * @param idParqueadero
     * @param idPuerta
     * @throws ParqueaderoNoExistenteException
     * @throws PuertaNoExistenteException
     * @throws PuertaYaAgregadaException 
     */
    public void addPuertaEntrada(String idParqueadero, String idPuerta) throws ParqueaderoNoExistenteException, PuertaNoExistenteException, PuertaYaAgregadaException, CodigoNoValidoException{
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCodigo(idPuerta);
        parqueaderoDAO.addPuertaEntrada(idParqueadero, idPuerta);
    }
    /**
     * 
     * @param idParqueadero
     * @param idPuerta
     * @throws ParqueaderoNoExistenteException
     * @throws PuertaNoExistenteException
     * @throws PuertaYaAgregadaException 
     */
    public void addPuertaSalida(String idParqueadero, String idPuerta) throws ParqueaderoNoExistenteException, PuertaNoExistenteException, PuertaYaAgregadaException, CodigoNoValidoException{
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCodigo(idPuerta);
        parqueaderoDAO.addPuertaSalida(idParqueadero, idPuerta);
    }
    /**
     * 
     * @param idParqueadero
     * @param idPuerta
     * @throws PuertaNoExistenteException
     * @throws ParqueaderoNoExistenteException
     * @throws CodigoNoValidoException 
     */
    public void delPuertaEntrada(String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CodigoNoValidoException{
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCodigo(idPuerta);
        parqueaderoDAO.delPuertaEntrada(idParqueadero, idPuerta);
    }
    /**
     * 
     * @param idParqueadero
     * @param idPuerta
     * @throws PuertaNoExistenteException
     * @throws ParqueaderoNoExistenteException
     * @throws CodigoNoValidoException 
     */
    public void delPuertaSalida(String idParqueadero, String idPuerta) throws PuertaNoExistenteException, 
            ParqueaderoNoExistenteException, CodigoNoValidoException{
        
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCodigo(idPuerta);
        parqueaderoDAO.delPuertaSalida(idParqueadero, idPuerta);
    }
    /**
     * 
     * @param idParqueadero
     * @param cedula
     * @throws CedulaNoValidaException
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException
     * @throws UsuarioYaAgregadoException
     * @throws UsuarioNoExistenteException 
     */
    public void addUsuario(String idParqueadero, String cedula) throws CedulaNoValidaException,
            CodigoNoValidoException, ParqueaderoNoExistenteException, UsuarioYaAgregadoException, UsuarioNoExistenteException{
        
        validaciones.validarCedula(cedula);
        validaciones.validarCodigo(idParqueadero);
        parqueaderoDAO.addUsuario(idParqueadero, cedula);
        
    }
    /**
     * 
     * @param idParqueadero
     * @param cedula
     * @throws CedulaNoValidaException
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException
     * @throws UsuarioNoExistenteException
     * @throws UsuarioNoAgregadoException 
     */
    public void delUsuario(String idParqueadero, String cedula) throws CedulaNoValidaException, CodigoNoValidoException, ParqueaderoNoExistenteException, UsuarioNoExistenteException, UsuarioNoAgregadoException{
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCedula(cedula);
        parqueaderoDAO.delUsuario(idParqueadero, cedula);
    }
    /**
     * 
     * @param idParqueadero
     * @return
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException 
     */
    public Set<Puerta> getPuertasEntrada(String idParqueadero) throws CodigoNoValidoException, ParqueaderoNoExistenteException{
        validaciones.validarCodigo(idParqueadero);
        return parqueaderoDAO.getPuertasEntrada(idParqueadero);
    }
    /**
     * 
     * @param idParqueadero
     * @return
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException 
     */
    public Set<Puerta> getPuertasSalida(String idParqueadero) throws CodigoNoValidoException, ParqueaderoNoExistenteException{
        validaciones.validarCodigo(idParqueadero);
        return parqueaderoDAO.getPuertasSalida(idParqueadero);
    }
    /**
     * 
     * @param idParqueadero
     * @return
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException
     * @throws UsuarioNoExistenteException 
     */
    public Set<Usuario> getUsuarios(String idParqueadero) throws CodigoNoValidoException, ParqueaderoNoExistenteException, UsuarioNoExistenteException{
        validaciones.validarCodigo(idParqueadero);
        return parqueaderoDAO.getUsuarios(idParqueadero);
    }
}
