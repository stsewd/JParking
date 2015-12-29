/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.srv.excepciones.PuertaInactivaException;
import edu.ucue.jparking.srv.excepciones.CampusInactivoException;
import edu.ucue.jparking.srv.excepciones.UsuarioInactivoException;
import edu.ucue.jparking.srv.excepciones.NumeroParqueaderosNoDisponiblesException;
import edu.ucue.jparking.dao.ParqueaderosDAO;
import edu.ucue.jparking.dao.UsuariosDAO;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoYaExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaYaAgregadaException;
import edu.ucue.jparking.dao.excepciones.PuertaYaExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoAgregadoException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaAgregadoException;
import edu.ucue.jparking.dao.interfaces.ParqueaderosDAOInterface;
import edu.ucue.jparking.srv.excepciones.ParquaderoInactivoException;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.excepciones.LugaresDeParqueoOCupadosException;
import edu.ucue.jparking.srv.excepciones.NumeroLugaresDeParqueoInsuficientesException;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Puerta;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.util.Calendar;
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
     * @throws edu.ucue.jparking.srv.excepciones.CodigoNoValidoException 
     * @throws edu.ucue.jparking.srv.excepciones.CampusInactivoException 
     */
    public void addParqueadero(String ubicacion, int numeroLugares, String id, String nombreCampus) throws ParqueaderoYaExistenteException, CampusNoExistenteException, CodigoNoValidoException, CampusInactivoException{
        validaciones.ValidarParqueadero(ubicacion, numeroLugares, id, nombreCampus);
        validaciones.validarCodigo(id);
        validaciones.ComprobarCampus(nombreCampus);
        Parqueadero parqueadero = new Parqueadero(ubicacion, numeroLugares, id, nombreCampus);
        parqueaderoDAO.addParqueadero(nombreCampus, parqueadero);
    }
    
    /**
     * 
     * @param nombreCampus
     * @param idParqueadero
     * @throws ParqueaderoNoExistenteException
     * @throws CampusNoExistenteException 
     * @throws edu.ucue.jparking.srv.excepciones.CodigoNoValidoException 
     */
    public void delParqueadero(String nombreCampus, String idParqueadero) throws ParqueaderoNoExistenteException, CampusNoExistenteException, CodigoNoValidoException{
        validaciones.validarCodigo(idParqueadero);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        parqueaderoDAO.delParqueadero(nombreCampus, idParqueadero);
    }
    
    /**
     * 
     * @param nombreCampus
     * @param idParqueadero
     * @return
     * @throws ParqueaderoNoExistenteException 
     * @throws edu.ucue.jparking.srv.excepciones.CodigoNoValidoException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public Parqueadero getParqueadero(String nombreCampus, String idParqueadero) throws ParqueaderoNoExistenteException, CodigoNoValidoException, CampusNoExistenteException{
        validaciones.validarCodigo(idParqueadero);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        return parqueaderoDAO.getParqueadero(nombreCampus, idParqueadero);
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
     * @param nombreCampus
     * @param idParqueadero
     * @param ubicacion
     * @param numLugares
     * @param estado
     * @throws ParqueaderoNoExistenteException 
     * @throws edu.ucue.jparking.srv.excepciones.CodigoNoValidoException 
     * @throws edu.ucue.jparking.srv.excepciones.LugaresDeParqueoOCupadosException 
     * @throws edu.ucue.jparking.srv.excepciones.NumeroLugaresDeParqueoInsuficientesException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public void modParqueadero(String nombreCampus, String idParqueadero, String ubicacion, int numLugares,boolean estado) throws ParqueaderoNoExistenteException, CodigoNoValidoException, LugaresDeParqueoOCupadosException, NumeroLugaresDeParqueoInsuficientesException, CampusNoExistenteException{
        validaciones.validarCodigo(idParqueadero);
        if(ubicacion == null || ubicacion.trim().length()==0)
            throw new IllegalArgumentException("La ubicacion no puede ser nula");
        if(numLugares < 0)
            throw new IllegalArgumentException("El numero de lugares no puede ser negativo");
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        
        int numLugaresOcupados = parqueaderoDAO.getParqueadero(nombreCampus, idParqueadero).getNumeroLugaresOcupados();
        if(numLugares < numLugaresOcupados)
            throw new LugaresDeParqueoOCupadosException(numLugaresOcupados);
        
        int numUsuarios = parqueaderoDAO.getParqueadero(nombreCampus, idParqueadero).getUsuarios().size();
        if(numLugares < numUsuarios * 1.05)
            throw new NumeroLugaresDeParqueoInsuficientesException(numUsuarios);
        
        //Validar que el numero de lugares nuevo sea mayor o igual al 105%
        //del numero de usuarios registrados en ese parqueadero
        
        parqueaderoDAO.modParqueadero(nombreCampus, idParqueadero, ubicacion, numLugares, estado);
    }
    
    /**
     * 
     * @param nombreCampus
     * @param idParqueadero
     * @param idPuerta
     * @throws ParqueaderoNoExistenteException
     * @throws PuertaNoExistenteException
     * @throws PuertaYaAgregadaException 
     * @throws edu.ucue.jparking.srv.excepciones.CodigoNoValidoException 
     * @throws edu.ucue.jparking.srv.excepciones.ParquaderoInactivoException 
     * @throws edu.ucue.jparking.dao.excepciones.PuertaYaExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     * @throws edu.ucue.jparking.srv.excepciones.PuertaInactivaException 
     */
    public void addPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta) throws ParqueaderoNoExistenteException, PuertaNoExistenteException, PuertaYaAgregadaException, CodigoNoValidoException, ParquaderoInactivoException, PuertaYaExistenteException, CampusNoExistenteException, PuertaInactivaException{
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCodigo(idPuerta);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        validaciones.ComprobarParqueadero(nombreCampus, idParqueadero);
        validaciones.ComprobarPuerta(nombreCampus, idParqueadero, idPuerta);
        parqueaderoDAO.addPuertaEntrada(nombreCampus, idParqueadero, idPuerta);
    }
    
    /**
     * 
     * @param nombreCampus
     * @param idParqueadero
     * @param idPuerta
     * @throws ParqueaderoNoExistenteException
     * @throws PuertaNoExistenteException
     * @throws PuertaYaAgregadaException 
     * @throws edu.ucue.jparking.srv.excepciones.CodigoNoValidoException 
     * @throws edu.ucue.jparking.srv.excepciones.ParquaderoInactivoException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     * @throws edu.ucue.jparking.srv.excepciones.PuertaInactivaException 
     */
    public void addPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta) throws ParqueaderoNoExistenteException, PuertaNoExistenteException, PuertaYaAgregadaException, CodigoNoValidoException, ParquaderoInactivoException, CampusNoExistenteException, PuertaInactivaException{
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCodigo(idPuerta);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");        
        validaciones.ComprobarParqueadero(nombreCampus, idParqueadero);
        validaciones.ComprobarPuerta(nombreCampus, idParqueadero, idPuerta);
        
        parqueaderoDAO.addPuertaSalida(nombreCampus, idParqueadero, idPuerta);
    }
    
    /**
     * 
     * @param nombreCampus
     * @param idParqueadero
     * @param idPuerta
     * @throws PuertaNoExistenteException
     * @throws ParqueaderoNoExistenteException
     * @throws CodigoNoValidoException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public void delPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CodigoNoValidoException, CampusNoExistenteException{
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCodigo(idPuerta);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        parqueaderoDAO.delPuertaEntrada(nombreCampus, idParqueadero, idPuerta);
    }
    /**
     * 
     * @param nombreCampus
     * @param idParqueadero
     * @param idPuerta
     * @throws PuertaNoExistenteException
     * @throws ParqueaderoNoExistenteException
     * @throws CodigoNoValidoException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public void delPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta) throws PuertaNoExistenteException, 
            ParqueaderoNoExistenteException, CodigoNoValidoException, CampusNoExistenteException {
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCodigo(idPuerta);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        parqueaderoDAO.delPuertaSalida(nombreCampus, idParqueadero, idPuerta);
    }
   
    private void AgregarEspacioParqueo(String nombreCampus, String idParqueadero, String cedula, Calendar fecha) throws ParqueaderoNoExistenteException, UsuarioNoExistenteException, CodigoNoValidoException, CampusNoExistenteException, CedulaNoValidaException{
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        parqueadero.setNumeroLugaresOcupados(parqueadero.getNumeroLugaresOcupados()+1);
            UsuariosDAO.getInstance().fechaContrato(cedula, fecha);
    }
    
    private void AgregarEspacioParqueo(String nombreCampus, String idParqueadero, String cedula) throws ParqueaderoNoExistenteException, UsuarioNoExistenteException, CodigoNoValidoException, CampusNoExistenteException, CedulaNoValidaException{
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        parqueadero.setNumeroLugaresOcupados(parqueadero.getNumeroLugaresOcupados()+1);
    }
    
    private void EliminarEspacioParqueo(String nombreCampus, String idParqueadero, String cedula) throws ParqueaderoNoExistenteException, UsuarioNoExistenteException, CodigoNoValidoException, CampusNoExistenteException, CedulaNoValidaException{
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        parqueadero.setNumeroLugaresOcupados(parqueadero.getNumeroLugaresOcupados()-1);
        UsuariosDAO.getInstance().fechaContrato(cedula, null);
    }
    
    private void EliminarEspacioParqueo(String nombreCampus, String idParqueadero, String cedula,int aux) throws ParqueaderoNoExistenteException, UsuarioNoExistenteException, CodigoNoValidoException, CampusNoExistenteException, CedulaNoValidaException{
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        parqueadero.setNumeroLugaresOcupados(parqueadero.getNumeroLugaresOcupados()-1);
    }
    
    
    /**
     * 
     * @param nombreCampus
     * @param idParqueadero
     * @param cedula
     * @throws CedulaNoValidaException
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException
     * @throws UsuarioYaAgregadoException
     * @throws UsuarioNoExistenteException 
     * @throws edu.ucue.jparking.srv.excepciones.ParquaderoInactivoException 
     * @throws edu.ucue.jparking.srv.excepciones.NumeroParqueaderosNoDisponiblesException 
     * @throws edu.ucue.jparking.srv.excepciones.UsuarioInactivoException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public void addUsuario(String nombreCampus, String idParqueadero, String cedula) throws CedulaNoValidaException,
            CodigoNoValidoException, ParqueaderoNoExistenteException, UsuarioYaAgregadoException, UsuarioNoExistenteException, ParquaderoInactivoException, NumeroParqueaderosNoDisponiblesException, UsuarioInactivoException, CampusNoExistenteException{
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        validaciones.validarCedula(cedula);
        validaciones.validarCodigo(idParqueadero);
        validaciones.ComprobarParqueadero(nombreCampus, idParqueadero);
        validaciones.ComprobarUsuario(cedula);
        Calendar fecha = Calendar.getInstance();
        Parqueadero p = getParqueadero(nombreCampus, idParqueadero);
        if(p.getNumeroLugaresOcupados() >= p.getNumeroLugares()) {
            throw new NumeroParqueaderosNoDisponiblesException();
        } else {
            if(validaciones.ComprobarUsuarioAsignadoParqueadero(cedula)){
                parqueaderoDAO.addUsuario(nombreCampus, idParqueadero, cedula);
                AgregarEspacioParqueo(nombreCampus, idParqueadero, cedula);
            }else{
                parqueaderoDAO.addUsuario(nombreCampus, idParqueadero, cedula);
                AgregarEspacioParqueo(nombreCampus, idParqueadero, cedula, fecha);
            }
        }
    }
    /**
     * 
     * @param nombreCampus
     * @param idParqueadero
     * @param cedula
     * @throws CedulaNoValidaException
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException
     * @throws UsuarioNoExistenteException
     * @throws UsuarioNoAgregadoException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public void delUsuario(String nombreCampus, String idParqueadero, String cedula) throws CedulaNoValidaException, CodigoNoValidoException, ParqueaderoNoExistenteException, UsuarioNoExistenteException, UsuarioNoAgregadoException, CampusNoExistenteException{
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCedula(cedula);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        parqueaderoDAO.delUsuario(nombreCampus, idParqueadero, cedula);
        if(validaciones.ComprobarUsuarioAsignadoParqueadero(cedula)==false){
            EliminarEspacioParqueo(nombreCampus, idParqueadero, cedula);
        }else{
            EliminarEspacioParqueo(nombreCampus, idParqueadero, cedula, 1);
        }
    }
    
    /**
     * 
     * @param nombreCampus
     * @param idParqueadero
     * @return
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public Set<Puerta> getPuertasEntrada(String nombreCampus, String idParqueadero) throws CodigoNoValidoException, ParqueaderoNoExistenteException, CampusNoExistenteException{
        validaciones.validarCodigo(idParqueadero);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        return parqueaderoDAO.getPuertasEntrada(nombreCampus, idParqueadero);
    }
    
    /**
     * 
     * @param nombreCampus
     * @param idParqueadero
     * @return
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public Set<Puerta> getPuertasSalida(String nombreCampus, String idParqueadero) throws CodigoNoValidoException,
            ParqueaderoNoExistenteException, CampusNoExistenteException {
        validaciones.validarCodigo(idParqueadero);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        return parqueaderoDAO.getPuertasSalida(nombreCampus, idParqueadero);
    }
    
    /**
     * 
     * @param nombreCampus
     * @param idParqueadero
     * @return
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException
     * @throws UsuarioNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public Set<Usuario> getUsuarios(String nombreCampus, String idParqueadero) throws CodigoNoValidoException, ParqueaderoNoExistenteException, UsuarioNoExistenteException, CampusNoExistenteException{
        validaciones.validarCodigo(idParqueadero);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        return parqueaderoDAO.getUsuarios(nombreCampus, idParqueadero);
    }
}
