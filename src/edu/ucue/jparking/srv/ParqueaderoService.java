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
import edu.ucue.jparking.srv.excepciones.NumeroLugaresDeParqueoInsuficientesException;
import edu.ucue.jparking.srv.excepciones.LugaresDeParqueoOCupadosException;
import edu.ucue.jparking.dao.ParqueaderosDAO;
import edu.ucue.jparking.dao.PuertasDAO;
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
import edu.ucue.jparking.srv.Validaciones;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.excepciones.LugaresDeParqueoOCupadosException;
import edu.ucue.jparking.srv.excepciones.NumeroLugaresDeParqueoInsuficientesException;
import edu.ucue.jparking.srv.objetos.Campus;
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
     * @param numLugares
     * @throws ParqueaderoNoExistenteException 
     */
    public void modParqueadero(String idParqueadero, String ubicacion, int numLugares,boolean estado) throws ParqueaderoNoExistenteException, CodigoNoValidoException, LugaresDeParqueoOCupadosException, NumeroLugaresDeParqueoInsuficientesException{
        validaciones.validarCodigo(idParqueadero);
        if(ubicacion == null || ubicacion.trim().length()==0)
            throw new IllegalArgumentException("La ubicacion no puede ser nula");
        if(numLugares < 0)
            throw new IllegalArgumentException("El numero de lugares no puede ser negativo");
        
        int numLugaresOcupados = parqueaderoDAO.getParqueadero(idParqueadero).getNumeroLugaresOcupados();
        if(numLugares < numLugaresOcupados)
            throw new LugaresDeParqueoOCupadosException(numLugaresOcupados);
        
        int numUsuarios = parqueaderoDAO.getParqueadero(idParqueadero).getUsuarios().size();
        if(numLugares < numUsuarios * 1.05)
            throw new NumeroLugaresDeParqueoInsuficientesException(numUsuarios);
        
        //Validar que el numero de lugares nuevo sea mayor o igual al 105%
        //del numero de usuarios registrados en ese parqueadero
        
        parqueaderoDAO.modParqueadero(idParqueadero, ubicacion, numLugares,estado);
    }
    
    /**
     * 
     * @param idParqueadero
     * @param idPuerta
     * @throws ParqueaderoNoExistenteException
     * @throws PuertaNoExistenteException
     * @throws PuertaYaAgregadaException 
     */
    public void addPuertaEntrada(String idParqueadero, String idPuerta) throws ParqueaderoNoExistenteException, PuertaNoExistenteException, PuertaYaAgregadaException, CodigoNoValidoException, ParquaderoInactivoException, PuertaYaExistenteException, CampusNoExistenteException, PuertaInactivaException{
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCodigo(idPuerta);
        validaciones.ComprobarParqueadero(idParqueadero);
        validaciones.ComprobarPuerta(idParqueadero, idPuerta);
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
    public void addPuertaSalida(String idParqueadero, String idPuerta) throws ParqueaderoNoExistenteException, PuertaNoExistenteException, PuertaYaAgregadaException, CodigoNoValidoException, ParquaderoInactivoException, CampusNoExistenteException, PuertaInactivaException{
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCodigo(idPuerta);
        validaciones.ComprobarParqueadero(idParqueadero);
        validaciones.ComprobarPuerta(idParqueadero, idPuerta);
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
    public void delPuertaEntrada(String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CodigoNoValidoException, CampusNoExistenteException{
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
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public void delPuertaSalida(String idParqueadero, String idPuerta) throws PuertaNoExistenteException, 
            ParqueaderoNoExistenteException, CodigoNoValidoException, CampusNoExistenteException{
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCodigo(idPuerta);
        parqueaderoDAO.delPuertaSalida(idParqueadero, idPuerta);
    }
   
    private void AgregarEspacioParqueo(String idParqueadero, String cedula, Calendar fecha) throws ParqueaderoNoExistenteException, UsuarioNoExistenteException, CodigoNoValidoException, CampusNoExistenteException, CedulaNoValidaException{
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        parqueadero.setNumeroLugaresOcupados(parqueadero.getNumeroLugaresOcupados()+1);
            UsuariosDAO.getInstance().fechaContrato(cedula, fecha);
    }
    
    private void AgregarEspacioParqueo(String idParqueadero, String cedula) throws ParqueaderoNoExistenteException, UsuarioNoExistenteException, CodigoNoValidoException, CampusNoExistenteException, CedulaNoValidaException{
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        parqueadero.setNumeroLugaresOcupados(parqueadero.getNumeroLugaresOcupados()+1);
    }
    
    private void EliminarEspacioParqueo(String idParqueadero, String cedula) throws ParqueaderoNoExistenteException, UsuarioNoExistenteException, CodigoNoValidoException, CampusNoExistenteException, CedulaNoValidaException{
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        parqueadero.setNumeroLugaresOcupados(parqueadero.getNumeroLugaresOcupados()-1);
        UsuariosDAO.getInstance().fechaContrato(cedula, null);
    }
    
    private void EliminarEspacioParqueo(String idParqueadero, String cedula,int aux) throws ParqueaderoNoExistenteException, UsuarioNoExistenteException, CodigoNoValidoException, CampusNoExistenteException, CedulaNoValidaException{
        Parqueadero parqueadero = getParqueadero(idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        parqueadero.setNumeroLugaresOcupados(parqueadero.getNumeroLugaresOcupados()-1);
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
            CodigoNoValidoException, ParqueaderoNoExistenteException, UsuarioYaAgregadoException, UsuarioNoExistenteException, ParquaderoInactivoException, NumeroParqueaderosNoDisponiblesException, UsuarioInactivoException, CampusNoExistenteException{
        
        validaciones.validarCedula(cedula);
        validaciones.validarCodigo(idParqueadero);
        validaciones.ComprobarParqueadero(idParqueadero);
        validaciones.ComprobarUsuario(cedula);
        Calendar fecha = Calendar.getInstance();
        Parqueadero p = getParqueadero(idParqueadero);
        if(p.getNumeroLugaresOcupados()>=p.getNumeroLugares()) {
            throw new NumeroParqueaderosNoDisponiblesException();
        } else {
            if(validaciones.ComprobarUsuarioAsignadoParqueadero(cedula)==true){
                parqueaderoDAO.addUsuario(idParqueadero, cedula);
                AgregarEspacioParqueo(idParqueadero, cedula);
            }else{
                parqueaderoDAO.addUsuario(idParqueadero, cedula);
                AgregarEspacioParqueo(idParqueadero, cedula, fecha);
            }
            
        }
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
    public void delUsuario(String idParqueadero, String cedula) throws CedulaNoValidaException, CodigoNoValidoException, ParqueaderoNoExistenteException, UsuarioNoExistenteException, UsuarioNoAgregadoException, CampusNoExistenteException{
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCedula(cedula);
        parqueaderoDAO.delUsuario(idParqueadero, cedula);
        if(validaciones.ComprobarUsuarioAsignadoParqueadero(cedula)==false){
            EliminarEspacioParqueo(idParqueadero, cedula);
        }else{
            EliminarEspacioParqueo(idParqueadero, cedula, 1);
        }
    }
    /**
     * 
     * @param idParqueadero
     * @return
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException 
     */
    public Set<Puerta> getPuertasEntrada(String idParqueadero) throws CodigoNoValidoException, ParqueaderoNoExistenteException, CampusNoExistenteException{
        validaciones.validarCodigo(idParqueadero);
        return parqueaderoDAO.getPuertasEntrada(idParqueadero);
    }
    /**
     * 
     * @param idParqueadero
     * @return
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException 
     * @throws edu.ucue.jparking.dao.excepciones.CampusNoExistenteException 
     */
    public Set<Puerta> getPuertasSalida(String idParqueadero) throws CodigoNoValidoException,
            ParqueaderoNoExistenteException, CampusNoExistenteException {
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
