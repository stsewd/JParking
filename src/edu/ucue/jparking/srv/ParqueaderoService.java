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
import edu.ucue.jparking.dao.excepciones.PuertaNoAgregadaException;
import edu.ucue.jparking.dao.UsuariosDAO;
import edu.ucue.jparking.dao.bptree.ObjectSizeException;
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
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Puerta;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * @author lara
 */
class ParqueaderoService {
    /**
     * 
     */
    Validaciones validaciones = new Validaciones();
    ParqueaderosDAOInterface parqueaderoDAO = ParqueaderosDAO.getInstance();
    CampusService campusService;

    public ParqueaderoService() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        campusService = new CampusService();
    }
    
    public void addParqueadero(String ubicacion, int numeroLugares, String id, 
            String nombreCampus) throws ParqueaderoYaExistenteException, 
            CampusNoExistenteException, CodigoNoValidoException, CampusInactivoException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        
        validaciones.ValidarParqueadero(ubicacion, numeroLugares, id, nombreCampus);
        validaciones.validarCodigo(id);
        validaciones.ComprobarCampus(nombreCampus);
        Campus campus = campusService.getCampus(nombreCampus);
        Parqueadero parqueadero = new Parqueadero(ubicacion, numeroLugares, id, campus);
        parqueaderoDAO.addParqueadero(nombreCampus, parqueadero);
    }
    

    public void delParqueadero(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, CampusNoExistenteException,
            CodigoNoValidoException, UsuarioNoExistenteException, UsuarioNoAgregadoException,
            CampusInactivoException, IOException, ClassNotFoundException, ObjectSizeException
    {
        validaciones.ComprobarCampus(nombreCampus);
        validaciones.validarCodigo(idParqueadero);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        parqueaderoDAO.delParqueadero(nombreCampus, idParqueadero);
    }
    
    public Parqueadero getParqueadero(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, CodigoNoValidoException,
            CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException 
    {
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
    public Set<Parqueadero> getParqueaderos() throws CampusNoExistenteException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        return parqueaderoDAO.getParqueaderos();
    }
    
    public Set<Parqueadero> getParqueaderos(String idCampus)
            throws CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        if(idCampus==null || idCampus.trim().length() == 0)
            throw new IllegalArgumentException("El id campus no puede estar vacio");
        
        return parqueaderoDAO.getParqueaderos(idCampus);
    }
    
    public void modParqueadero(String nombreCampus, String idParqueadero, String ubicacion, int numLugares,boolean estado) 
            throws ParqueaderoNoExistenteException, CodigoNoValidoException, LugaresDeParqueoOCupadosException, 
            NumeroLugaresDeParqueoInsuficientesException, CampusNoExistenteException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
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
                
        parqueaderoDAO.modParqueadero(nombreCampus, idParqueadero, ubicacion, numLugares, estado);
    }
    
    public void addPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta) 
            throws ParqueaderoNoExistenteException, PuertaNoExistenteException,
            PuertaYaAgregadaException, CodigoNoValidoException, ParquaderoInactivoException,
            PuertaYaExistenteException, CampusNoExistenteException, PuertaInactivaException,
            CampusInactivoException, IOException, FileNotFoundException, ClassNotFoundException,
            ObjectSizeException
    {
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCodigo(idPuerta);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        validaciones.ComprobarParqueadero(nombreCampus, idParqueadero);
        validaciones.ComprobarPuerta(nombreCampus, idParqueadero, idPuerta);
        validaciones.ComprobarCampus(nombreCampus);
        parqueaderoDAO.addPuertaEntrada(nombreCampus, idParqueadero, idPuerta);
    }
    
    public void addPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta)
            throws ParqueaderoNoExistenteException, PuertaNoExistenteException, 
            PuertaYaAgregadaException, CodigoNoValidoException, ParquaderoInactivoException,
            CampusNoExistenteException, PuertaInactivaException, CampusInactivoException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCodigo(idPuerta);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");        
        validaciones.ComprobarParqueadero(nombreCampus, idParqueadero);
        validaciones.ComprobarPuerta(nombreCampus, idParqueadero, idPuerta);
        validaciones.ComprobarCampus(nombreCampus);
        parqueaderoDAO.addPuertaSalida(nombreCampus, idParqueadero, idPuerta);
    }
    
    public void delPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta)
            throws PuertaNoExistenteException, ParqueaderoNoExistenteException,
            CodigoNoValidoException, CampusNoExistenteException, PuertaNoAgregadaException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCodigo(idPuerta);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        parqueaderoDAO.delPuertaEntrada(nombreCampus, idParqueadero, idPuerta);
    }

    public void delPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta)
            throws PuertaNoExistenteException, ParqueaderoNoExistenteException,
            CodigoNoValidoException, CampusNoExistenteException, PuertaNoAgregadaException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException 
    {
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCodigo(idPuerta);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        parqueaderoDAO.delPuertaSalida(nombreCampus, idParqueadero, idPuerta);
    }
   
    private void agregarEspacioParqueo(String nombreCampus, String idParqueadero, String cedula, Calendar fecha)
            throws ParqueaderoNoExistenteException, UsuarioNoExistenteException, CodigoNoValidoException,
            CampusNoExistenteException, CedulaNoValidaException, CampusInactivoException,
            ParquaderoInactivoException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        UsuariosDAO.getInstance().setFechaContrato(cedula, fecha);
    }
    
    private void agregarEspacioParqueo(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, UsuarioNoExistenteException,
            CodigoNoValidoException, CampusNoExistenteException, CedulaNoValidaException,
            CampusInactivoException, ParquaderoInactivoException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
    }
    
    private void eliminarEspacioParqueo(String nombreCampus, String idParqueadero, String cedula)
            throws ParqueaderoNoExistenteException, UsuarioNoExistenteException, CodigoNoValidoException,
            CampusNoExistenteException, CedulaNoValidaException, CampusInactivoException, ParquaderoInactivoException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
        UsuariosDAO.getInstance().setFechaContrato(cedula, null);
    }
    
    private void eliminarEspacioParqueo(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, UsuarioNoExistenteException, CodigoNoValidoException,
            CampusNoExistenteException, CedulaNoValidaException, CampusInactivoException,
            ParquaderoInactivoException, IOException, FileNotFoundException, ClassNotFoundException,
            ObjectSizeException
    {
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        Parqueadero parqueadero = getParqueadero(nombreCampus, idParqueadero);
        if(parqueadero == null)
            throw new ParqueaderoNoExistenteException(idParqueadero);
    }
    
    
    public void addUsuario(String nombreCampus, String idParqueadero, String cedula) 
            throws CedulaNoValidaException,
            CodigoNoValidoException, ParqueaderoNoExistenteException,
            UsuarioYaAgregadoException, UsuarioNoExistenteException,
            ParquaderoInactivoException, NumeroParqueaderosNoDisponiblesException,
            UsuarioInactivoException, CampusNoExistenteException, CampusInactivoException, 
            IOException, ClassNotFoundException, ObjectSizeException
    {
        
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        
        validaciones.validarCedula(cedula);
        validaciones.validarCodigo(idParqueadero);
        validaciones.ComprobarParqueadero(nombreCampus, idParqueadero);
        validaciones.ComprobarUsuario(cedula);
        validaciones.ComprobarCampus(nombreCampus);
        
        Parqueadero p = getParqueadero(nombreCampus, idParqueadero);
        if(p.getNumeroLugaresOcupados() >= p.getNumeroLugares())
            throw new NumeroParqueaderosNoDisponiblesException();
        parqueaderoDAO.addUsuario(nombreCampus, idParqueadero, cedula);
    }
    
    public void delUsuario(String nombreCampus, String idParqueadero, String cedula) 
            throws CedulaNoValidaException, CodigoNoValidoException, 
            ParqueaderoNoExistenteException, UsuarioNoExistenteException, 
            UsuarioNoAgregadoException, CampusNoExistenteException, 
            CampusInactivoException, ParquaderoInactivoException, ClassNotFoundException, ObjectSizeException, IOException
    {
        validaciones.validarCodigo(idParqueadero);
        validaciones.validarCedula(cedula);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        parqueaderoDAO.delUsuario(nombreCampus, idParqueadero, cedula);
        if(!validaciones.ComprobarUsuarioAsignadoParqueadero(cedula)){
            //Eliminar contrato
            eliminarEspacioParqueo(nombreCampus, idParqueadero, cedula);
        }else{
            eliminarEspacioParqueo(nombreCampus, idParqueadero);
        }
    }
    
    public Set<Puerta> getPuertasEntrada(String nombreCampus, String idParqueadero) 
            throws CodigoNoValidoException, ParqueaderoNoExistenteException, CampusNoExistenteException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        validaciones.validarCodigo(idParqueadero);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        return parqueaderoDAO.getPuertasEntrada(nombreCampus, idParqueadero);
    }
    
    public Set<Puerta> getPuertasSalida(String nombreCampus, String idParqueadero)
            throws CodigoNoValidoException, ParqueaderoNoExistenteException, CampusNoExistenteException,
            IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        validaciones.validarCodigo(idParqueadero);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        return parqueaderoDAO.getPuertasSalida(nombreCampus, idParqueadero);
    }
    
    public Set<Usuario> getUsuarios(String nombreCampus, String idParqueadero) 
            throws CodigoNoValidoException, ParqueaderoNoExistenteException, 
            UsuarioNoExistenteException, CampusNoExistenteException, IOException,
            FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        validaciones.validarCodigo(idParqueadero);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El campus no puede ser nulo.");
        return parqueaderoDAO.getUsuarios(nombreCampus, idParqueadero);
    }
}
