/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.srv.excepciones.ClaveNoValidaException;
import edu.ucue.jparking.srv.excepciones.PagoNoCanceladoException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.CampusExistenteExeption;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoYaExistenteException;
import edu.ucue.jparking.dao.excepciones.PersonaYaRegistradaComoUsuarioException;
import edu.ucue.jparking.dao.excepciones.PersonaYaRegistradoComoPorteroException;
import edu.ucue.jparking.dao.excepciones.PorteroNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PorteroYaExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoAgregadaException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaYaAgregadaException;
import edu.ucue.jparking.dao.excepciones.PuertaYaExistenteException;
import edu.ucue.jparking.dao.excepciones.RegistroNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoAgregadoException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaAgregadoException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.dao.excepciones.OrdenPagoNoExistenteException;
import edu.ucue.jparking.srv.enums.TipoRegistro;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.excepciones.AccesoNoAutorizadoException;
import edu.ucue.jparking.srv.excepciones.CampusInactivoException;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.excepciones.ContratoNoEstablecidoException;
import edu.ucue.jparking.srv.excepciones.FechaFinalMenorAFechaInicialException;
import edu.ucue.jparking.srv.excepciones.FechaInicialIgualAFechaFinalException;
import edu.ucue.jparking.srv.excepciones.FechaInicialMayorAFechaFinalException;
import edu.ucue.jparking.srv.excepciones.FueraDelDiaDePagoException;
import edu.ucue.jparking.srv.excepciones.LugaresDeParqueoOCupadosException;
import edu.ucue.jparking.srv.excepciones.NumeroLugaresDeParqueoInsuficientesException;
import edu.ucue.jparking.srv.excepciones.NumeroParqueaderosNoDisponiblesException;
import edu.ucue.jparking.srv.excepciones.PagoYaRealizadoException;
import edu.ucue.jparking.srv.excepciones.ParquaderoInactivoException;
import edu.ucue.jparking.srv.excepciones.PorteroInactivoException;
import edu.ucue.jparking.srv.excepciones.PuertaInactivaException;
import edu.ucue.jparking.srv.excepciones.TelefonoNoValidoException;
import edu.ucue.jparking.srv.excepciones.UsuarioInactivoException;
import edu.ucue.jparking.srv.excepciones.UsuarioNoRegistradoEnUnParqueaderoException;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.OrdenPago;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Portero;
import edu.ucue.jparking.srv.objetos.Puerta;
import edu.ucue.jparking.srv.objetos.Usuario;
import edu.ucue.jparking.srv.objetos.registros.Registro;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Santos Gallegos
 */
public interface JPInterface {
    
    /**
     * Agrega un nuevo campus al sistema.
     * @param nombre
     * @param direccion
     * @throws CampusExistenteExeption 
     */
    public void addCampus(String nombre, String direccion)
            throws CampusExistenteExeption;
    /**
     * recupera un capus por el nombre
     * @param nombre
     * @return
     * @throws CampusNoExistenteException 
     */
    public Campus getCampus(String nombre) 
            throws CampusNoExistenteException;

    /**
     * modifica un campus
     * @param nombre
     * @param direccion
     * @param estado
     * @throws CampusNoExistenteException 
     */
    public void modCampus(String nombre,String direccion,boolean estado) 
            throws CampusNoExistenteException;

    /**
     * elemina un campus por su nombre
     * @param nombre
     * @throws CampusNoExistenteException
     * @throws ParqueaderoNoExistenteException
     * @throws UsuarioNoExistenteException
     * @throws UsuarioNoAgregadoException 
     */
    public void delCampus(String nombre)
            throws CampusNoExistenteException, ParqueaderoNoExistenteException,
            UsuarioNoExistenteException, UsuarioNoAgregadoException,
            IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    /**
     * retorna todo los campus
     * @return 
     */
    public Set<Campus> getCampus();
    
    /**
     * saca una orden de pago por el numero de cedula
     * @param cedula
     * @return
     * @throws CedulaNoValidaException
     * @throws UsuarioNoExistenteException
     * @throws ContratoNoEstablecidoException
     * @throws FueraDelDiaDePagoException 
     * @throws edu.ucue.jparking.srv.excepciones.UsuarioNoRegistradoEnUnParqueaderoException 
     */
    public OrdenPago getOrdenPago(String cedula) 
            throws CedulaNoValidaException, UsuarioNoExistenteException,
            ContratoNoEstablecidoException, FueraDelDiaDePagoException,
            UsuarioNoRegistradoEnUnParqueaderoException,IOException, 
            ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * permite el pago de una orden de pago por medio de la cedula
     * @param cedula
     * @throws CedulaNoValidaException
     * @throws UsuarioNoExistenteException
     * @throws PagoYaRealizadoException 
     * @throws edu.ucue.jparking.srv.excepciones.ContratoNoEstablecidoException 
     * @throws edu.ucue.jparking.srv.excepciones.FueraDelDiaDePagoException 
     * @throws edu.ucue.jparking.srv.excepciones.UsuarioNoRegistradoEnUnParqueaderoException 
     */
    public void pagarOrdenPago(String cedula) 
            throws CedulaNoValidaException,
            UsuarioNoExistenteException, PagoYaRealizadoException,
            ContratoNoEstablecidoException, FueraDelDiaDePagoException,
            UsuarioNoRegistradoEnUnParqueaderoException,
            IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;

    /**
     * crea un parqueadero
     * @param ubicacion
     * @param numeroLugares
     * @param id
     * @param nombreCampus
     * @throws ParqueaderoYaExistenteException
     * @throws CampusNoExistenteException
     * @throws CodigoNoValidoException
     * @throws CampusInactivoException 
     */
    public void addParqueadero(String ubicacion, int numeroLugares, String id, String nombreCampus)
            throws ParqueaderoYaExistenteException, CampusNoExistenteException,
            CodigoNoValidoException, CampusInactivoException;
    
    /**
     * borra un parqueadero por el nombre delUsuario campus y el identificador delUsuario parqueadero 
     * @param nombreCampus
     * @param idParqueadero
     * @throws ParqueaderoNoExistenteException
     * @throws CampusNoExistenteException
     * @throws CodigoNoValidoException
     * @throws UsuarioNoExistenteException
     * @throws UsuarioNoAgregadoException 
     * @throws edu.ucue.jparking.srv.excepciones.CampusInactivoException 
     */
    public void delParqueadero(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, CampusNoExistenteException,
            CodigoNoValidoException, UsuarioNoExistenteException, UsuarioNoAgregadoException,
            UsuarioNoAgregadoException, CampusInactivoException,
            IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;

    /**
     * estrae un parqueadero por el nombre delUsuario campus y su identificador
     * @param nombreCampus
     * @param idParqueadero
     * @return
     * @throws ParqueaderoNoExistenteException
     * @throws CodigoNoValidoException
     * @throws CampusNoExistenteException 
     */
    public Parqueadero getParqueadero(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, CodigoNoValidoException,
            CampusNoExistenteException;

    /**
     * extrae todos los parqueaderos
     * @return
     * @throws CampusNoExistenteException 
     */
    public Set<Parqueadero> getParqueaderos() throws CampusNoExistenteException;

    /**
     * extrae todos los parqueaderos de un campus
     * @param idCampus
     * @return
     * @throws CampusNoExistenteException 
     */
    public Set<Parqueadero> getParqueaderos(String idCampus) throws CampusNoExistenteException;

    /**
     * modifica los datos de un parqueadero
     * @param nombreCampus
     * @param idParqueadero
     * @param ubicacion
     * @param numLugares
     * @param estado
     * @throws ParqueaderoNoExistenteException
     * @throws CodigoNoValidoException
     * @throws LugaresDeParqueoOCupadosException
     * @throws NumeroLugaresDeParqueoInsuficientesException
     * @throws CampusNoExistenteException 
     */
    public void modParqueadero(String nombreCampus, String idParqueadero, String ubicacion, int numLugares,boolean estado) 
            throws ParqueaderoNoExistenteException, CodigoNoValidoException, LugaresDeParqueoOCupadosException, 
            NumeroLugaresDeParqueoInsuficientesException, CampusNoExistenteException;

    /**
     * agrega un puerta de entrada a un parqueadero
     * @param nombreCampus
     * @param idParqueadero
     * @param idPuerta
     * @throws ParqueaderoNoExistenteException
     * @throws PuertaNoExistenteException
     * @throws PuertaYaAgregadaException
     * @throws CodigoNoValidoException
     * @throws ParquaderoInactivoException
     * @throws PuertaYaExistenteException
     * @throws CampusNoExistenteException
     * @throws PuertaInactivaException
     * @throws CampusInactivoException 
     */
    public void addPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta) 
            throws ParqueaderoNoExistenteException, PuertaNoExistenteException,
            PuertaYaAgregadaException, CodigoNoValidoException, ParquaderoInactivoException,
            PuertaYaExistenteException, CampusNoExistenteException, PuertaInactivaException,
            CampusInactivoException;
    /**
     * agrega un puerta de salida a un parqueadero
     * @param nombreCampus
     * @param idParqueadero
     * @param idPuerta
     * @throws ParqueaderoNoExistenteException
     * @throws PuertaNoExistenteException
     * @throws PuertaYaAgregadaException
     * @throws CodigoNoValidoException
     * @throws ParquaderoInactivoException
     * @throws CampusNoExistenteException
     * @throws PuertaInactivaException
     * @throws CampusInactivoException 
     */
    public void addPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta)
            throws ParqueaderoNoExistenteException, PuertaNoExistenteException, 
            PuertaYaAgregadaException, CodigoNoValidoException, ParquaderoInactivoException,
            CampusNoExistenteException, PuertaInactivaException, CampusInactivoException;
    
    /**
     * elimina una puerta de entrada
     * @param nombreCampus
     * @param idParqueadero
     * @param idPuerta
     * @throws PuertaNoExistenteException
     * @throws ParqueaderoNoExistenteException
     * @throws CodigoNoValidoException
     * @throws CampusNoExistenteException
     * @throws PuertaNoAgregadaException 
     */
    public void delPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta)
            throws PuertaNoExistenteException, ParqueaderoNoExistenteException,
            CodigoNoValidoException, CampusNoExistenteException, PuertaNoAgregadaException;

    /**
     * elimina una puerta de salida
     * @param nombreCampus
     * @param idParqueadero
     * @param idPuerta
     * @throws PuertaNoExistenteException
     * @throws ParqueaderoNoExistenteException
     * @throws CodigoNoValidoException
     * @throws CampusNoExistenteException
     * @throws PuertaNoAgregadaException 
     */
    public void delPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta)
            throws PuertaNoExistenteException, ParqueaderoNoExistenteException,
            CodigoNoValidoException, CampusNoExistenteException, PuertaNoAgregadaException; 
    
    /**
     * agrega a un usuario en un parqueadero
     * @param nombreCampus
     * @param idParqueadero
     * @param cedula
     * @throws CedulaNoValidaException
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException
     * @throws UsuarioYaAgregadoException
     * @throws UsuarioNoExistenteException
     * @throws ParquaderoInactivoException
     * @throws NumeroParqueaderosNoDisponiblesException
     * @throws UsuarioInactivoException
     * @throws CampusNoExistenteException
     * @throws CampusInactivoException 
     */
    public void addUsuario(String nombreCampus, String idParqueadero, String cedula) 
            throws CedulaNoValidaException,
            CodigoNoValidoException, ParqueaderoNoExistenteException,
            UsuarioYaAgregadoException, UsuarioNoExistenteException,
            ParquaderoInactivoException, NumeroParqueaderosNoDisponiblesException,
            UsuarioInactivoException, CampusNoExistenteException, CampusInactivoException,
            IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * elimina a un usuario de un parqueadero
     * @param nombreCampus
     * @param idParqueadero
     * @param cedula
     * @throws CedulaNoValidaException
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException
     * @throws UsuarioNoExistenteException
     * @throws UsuarioNoAgregadoException
     * @throws CampusNoExistenteException
     * @throws CampusInactivoException
     * @throws ParquaderoInactivoException 
     */
    public void delUsuario(String nombreCampus, String idParqueadero, String cedula) 
            
            throws CedulaNoValidaException, CodigoNoValidoException, 
            ParqueaderoNoExistenteException, UsuarioNoExistenteException, 
            UsuarioNoAgregadoException, CampusNoExistenteException, 
            CampusInactivoException, ParquaderoInactivoException,
            IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    
    /**
     * extrae todas las puertas de un parquedero por el nombre delUsuario campus y su identificador
     * @param nombreCampus
     * @param idParqueadero
     * @return
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException
     * @throws CampusNoExistenteException 
     */
    public Set<Puerta> getPuertasEntrada(String nombreCampus, String idParqueadero) 
            
            throws CodigoNoValidoException, ParqueaderoNoExistenteException, CampusNoExistenteException;
    
    /**
     * extrae todas las puertas de salida de un parqueadero
     * @param nombreCampus
     * @param idParqueadero
     * @return
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException
     * @throws CampusNoExistenteException 
     */
    public Set<Puerta> getPuertasSalida(String nombreCampus, String idParqueadero)
            throws CodigoNoValidoException,
            ParqueaderoNoExistenteException, CampusNoExistenteException; 
    
    /**
     * saca todo los usuarios de un parqueadero
     * @param nombreCampus
     * @param idParqueadero
     * @return
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException
     * @throws UsuarioNoExistenteException
     * @throws CampusNoExistenteException 
     */
    public Set<Usuario> getUsuariosParqueadero(String nombreCampus, String idParqueadero) 
            throws CodigoNoValidoException, ParqueaderoNoExistenteException, 
            UsuarioNoExistenteException, CampusNoExistenteException;
    
    /**
     * crea un portero
     * @param nombreCampus
     * @param cedula
     * @param nombre
     * @param apellido
     * @param direccion
     * @param telefono
     * @throws CedulaNoValidaException
     * @throws CampusNoExistenteException
     * @throws PorteroYaExistenteException
     * @throws TelefonoNoValidoException
     * @throws PersonaYaRegistradaComoUsuarioException 
     */
    public void addPortero(String nombreCampus, String cedula, String nombre, String apellido, String direccion, String telefono) 
            throws CedulaNoValidaException, CampusNoExistenteException, 
            PorteroYaExistenteException, TelefonoNoValidoException,
            PersonaYaRegistradaComoUsuarioException, IOException, 
            ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * modifica un portero 
     * @param cedula
     * @param nombre
     * @param apellido
     * @param direccion
     * @param telefono
     * @param estado
     * @throws CedulaNoValidaException
     * @throws PorteroNoExistenteException
     * @throws TelefonoNoValidoException 
     */
    public void modPortero(String cedula, String nombre, String apellido, String direccion, String telefono,boolean estado)
            throws CedulaNoValidaException, PorteroNoExistenteException, TelefonoNoValidoException,
            IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * elimina un portero
     * @param cedula
     * @throws CedulaNoValidaException
     * @throws PorteroNoExistenteException
     * @throws CampusNoExistenteException 
     */
    public void delPortero(String cedula) 
            throws CedulaNoValidaException, PorteroNoExistenteException, CampusNoExistenteException,
            IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * extrae un portero
     * @param cedula
     * @return
     * @throws CedulaNoValidaException 
     */
    public Portero getPortero(String cedula) throws CedulaNoValidaException;
    
    
    /**
     * estrae todos los porteros
     * @return 
     */
    public Set<Portero> getPorteros();
    
    /**
     * extrae los portero de un campus
     * @param nombreCampus
     * @return
     * @throws CampusNoExistenteException 
     */
    public Set<Portero> getPorteros(String nombreCampus) throws CampusNoExistenteException;
    
    /**
     * crea una puerta
     * @param ubicacion
     * @param id
     * @param idCampus
     * @throws CodigoNoValidoException
     * @throws PuertaYaExistenteException
     * @throws CampusNoExistenteException 
     */
    public void addpuerta(String ubicacion, String id, String idCampus)
            throws CodigoNoValidoException, PuertaYaExistenteException, 
            CampusNoExistenteException;
    
    /**
     * elimina una puerta
     * @param nombreCampus
     * @param id
     * @throws CodigoNoValidoException
     * @throws PuertaNoExistenteException
     * @throws CampusNoExistenteException
     * @throws ParqueaderoNoExistenteException 
     */
    public void delpuerta(String nombreCampus, String id)
            throws CodigoNoValidoException, PuertaNoExistenteException,
            CampusNoExistenteException, ParqueaderoNoExistenteException;
    
    /**
     * extrae una puerta
     * @param nombreCampus
     * @param id
     * @return
     * @throws CodigoNoValidoException
     * @throws PuertaNoExistenteException
     * @throws CampusNoExistenteException 
     */
    public Puerta getPuerta(String nombreCampus, String id)
            throws CodigoNoValidoException, PuertaNoExistenteException,
            CampusNoExistenteException;
    
    /**
     * modifica una puerta
     * @param nombreCampus
     * @param id
     * @param ubicacion
     * @param activo
     * @throws CodigoNoValidoException
     * @throws PuertaNoExistenteException
     * @throws CampusNoExistenteException 
     */
    public void modPuerta(String nombreCampus, String id, String ubicacion, boolean activo)
            throws CodigoNoValidoException, PuertaNoExistenteException, CampusNoExistenteException;
    
    /**
     * extrae las puertas de un campus
     * @param nombreCampus
     * @return
     * @throws CampusNoExistenteException 
     */
    public Set<Puerta> getPuertas(String nombreCampus) throws CampusNoExistenteException;
    
    /**
     * extrae todas las puertas
     * @return 
     */
    public Set<Puerta> getPuertas();
          
    /**
     * Saca un registro especifico
     * @param tipoRegistro
     * @param fechaInicio
     * @param fechaFinal
     * @return
     * @throws FechaInicialMayorAFechaFinalException
     * @throws FechaFinalMenorAFechaInicialException
     * @throws FechaInicialIgualAFechaFinalException 
     */
    public Set<Registro> getRegistros(TipoRegistro tipoRegistro, Calendar fechaInicio, Calendar fechaFinal) 
            throws FechaInicialMayorAFechaFinalException, FechaFinalMenorAFechaInicialException,
            FechaInicialIgualAFechaFinalException, IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    /**
     * extrea los registro de un tipo
     * @param tipoRegistro
     * @return 
     */
    public Set<Registro> getRegistros(TipoRegistro tipoRegistro) throws IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * extrae todos los registros
     * @return 
     */
    public Set<Registro> getRegistros() throws IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * extrae los registro por fecha
     * @param fechaInicio
     * @param fechaFinal
     * @return
     * @throws FechaInicialMayorAFechaFinalException
     * @throws FechaFinalMenorAFechaInicialException
     * @throws FechaInicialIgualAFechaFinalException 
     */
    public Set<Registro>  getRegistros(Calendar fechaInicio, Calendar fechaFinal) 
            throws FechaInicialMayorAFechaFinalException, 
            FechaFinalMenorAFechaInicialException, FechaInicialIgualAFechaFinalException,
            IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * extrae un registro por su id
     * @param idRegistro
     * @return
     * @throws RegistroNoExistenteException 
     */
    public Registro getRegistro(String idRegistro) throws RegistroNoExistenteException,IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * crea un nuevo usuario
     * @param cedula
     * @param nombre
     * @param apellido
     * @param direccion
     * @param telefono
     * @param tipoUsuario
     * @throws UsuarioYaExistenteException
     * @throws CedulaNoValidaException
     * @throws TelefonoNoValidoException
     * @throws PersonaYaRegistradoComoPorteroException
     * @throws UsuarioNoExistenteException 
     */
    public void addUsuario(String cedula, String nombre, String apellido,String direccion,String telefono, String tipoUsuario) 
            throws UsuarioYaExistenteException, CedulaNoValidaException,
            TelefonoNoValidoException, PersonaYaRegistradoComoPorteroException,
            UsuarioNoExistenteException, IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * elminina a un usuario por el numero de cedula
     * @param cedula
     * @throws UsuarioNoExistenteException
     * @throws CedulaNoValidaException
     * @throws IllegalArgumentException
     * @throws CampusNoExistenteException 
     */
    public void delUsuario(String cedula) 
            throws UsuarioNoExistenteException, CedulaNoValidaException, 
            IllegalArgumentException, CampusNoExistenteException,
            IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * modifica el usuario
     * @param cedula
     * @param nombre
     * @param apellido
     * @param direccion
     * @param telefono
     * @param estado
     * @throws CedulaNoValidaException
     * @throws UsuarioNoExistenteException 
     */
    public void modUsuario(String cedula, String nombre, String apellido,String direccion,String telefono,boolean estado)
            throws CedulaNoValidaException, UsuarioNoExistenteException, TelefonoNoValidoException, IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;


    /**
     * extrae a un usuario por el numerode cedula
     * @param cedula
     * @return
     * @throws UsuarioNoExistenteException
     * @throws CedulaNoValidaException 
     */
    public Usuario getUsuario(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException,IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;

    /**
     * extrae todos los usuarios
     * @return 
     */
    public Set<Usuario> getUsuarios() throws IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;

    /**
     * extarae por el tipo de usuario
     * @param tipoUsuario
     * @return 
     */
    public Set<Usuario> getUsuarios(TipoUsuario tipoUsuario) throws IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * extrae todos los parqueadero de un usuario
     * @param cedula
     * @return
     * @throws CedulaNoValidaException
     * @throws UsuarioNoExistenteException 
     */
    public Set<Parqueadero> getParqueaderosUsuario(String cedula) 
            throws CedulaNoValidaException, UsuarioNoExistenteException
            ,IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * verifica y autentica a un usuario
     * @param nombreCampus
     * @param idPuerta
     * @param cedula
     * @throws CedulaNoValidaException
     * @throws UsuarioNoExistenteException
     * @throws CodigoNoValidoException
     * @throws ParqueaderoNoExistenteException
     * @throws AccesoNoAutorizadoException
     * @throws CampusNoExistenteException 
     * @throws edu.ucue.jparking.srv.excepciones.PagoNoCanceladoException 
     * @throws edu.ucue.jparking.srv.excepciones.PorteroInactivoException 
     * @throws edu.ucue.jparking.srv.excepciones.UsuarioInactivoException 
     */
    public void autenticarUsuario(String nombreCampus, String idPuerta, String cedula) 
            throws CedulaNoValidaException, UsuarioNoExistenteException, 
            CodigoNoValidoException, ParqueaderoNoExistenteException, 
            AccesoNoAutorizadoException, CampusNoExistenteException,
            PagoNoCanceladoException, PorteroInactivoException,
            UsuarioInactivoException, IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * Impresion de la orden de pago
     * @param cedula
     * @return
     * @throws UsuarioNoRegistradoEnUnParqueaderoException
     * @throws DocumentException
     * @throws FileNotFoundException
     * @throws UsuarioNoExistenteException
     * @throws CedulaNoValidaException
     * @throws ContratoNoEstablecidoException
     * @throws FueraDelDiaDePagoException
     * @throws BadElementException
     * @throws IOException 
     */
    public File exportarOrdenPago(String cedula) 
            throws UsuarioNoRegistradoEnUnParqueaderoException, 
            DocumentException, FileNotFoundException, 
            UsuarioNoExistenteException, CedulaNoValidaException,
            ContratoNoEstablecidoException, FueraDelDiaDePagoException, 
            BadElementException, IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * Retorna una orden de pago específica,
     * dado su número.
     * @param numeroOrdenPago
     * @return 
     * @throws OrdenPagoNoExistenteException 
     */
    public OrdenPago getOrdenPago(int numeroOrdenPago)
            throws OrdenPagoNoExistenteException, IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * Retorna todas las ordenes de pago almacenadas en el 
     * sistema.
     * @return 
     */
    public Set<OrdenPago> getOrdenesPago() throws IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * Retorna todas las ordenes de pago generadas entre
     * fechaInicial y fechaFinal.
     * @param fechaInicial
     * @param fechaFinal
     * @return 
     * @throws edu.ucue.jparking.srv.excepciones.FechaInicialMayorAFechaFinalException 
     * @throws edu.ucue.jparking.srv.excepciones.FechaFinalMenorAFechaInicialException 
     * @throws edu.ucue.jparking.srv.excepciones.FechaInicialIgualAFechaFinalException 
     */
    public Set<OrdenPago> getOrdenesPago(Calendar fechaInicial, Calendar fechaFinal)
            throws FechaInicialMayorAFechaFinalException, FechaFinalMenorAFechaInicialException,
            FechaInicialIgualAFechaFinalException, IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * Obtiene el valor del dinero recaudado entre todos los fondos.
     * @return 
     */
    public double getFondos() throws IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * Obtiene el valor del dinero recaudado entre fechaInicial
     * y fechaFinal.
     * @param fechaInicial
     * @param fechaFinal
     * @return 
     * @throws edu.ucue.jparking.srv.excepciones.FechaInicialMayorAFechaFinalException 
     * @throws edu.ucue.jparking.srv.excepciones.FechaFinalMenorAFechaInicialException 
     * @throws edu.ucue.jparking.srv.excepciones.FechaInicialIgualAFechaFinalException 
     */
    public double getFondos(Calendar fechaInicial, Calendar fechaFinal)
            throws FechaInicialMayorAFechaFinalException, FechaFinalMenorAFechaInicialException,
            FechaInicialIgualAFechaFinalException,IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException;
    
    /**
     * genera el archivo .Zip
     * @param fileName
     * @throws IOException
     * @throws FileNotFoundException 
     */
    public void makeZip(String fileName) throws IOException, FileNotFoundException;
    
    /**
     * genera la clave para el algoritmos de AES
     * @throws NoSuchAlgorithmException
     * @throws Exception 
     */
    public void GenerarClave() throws NoSuchAlgorithmException, Exception;
    
    /**
     * cifra la clave ecojida por el usuario
     * @param clave
     * @throws Exception 
     */
    public void cifrar(String clave) throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, ClassNotFoundException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException ;
    
    /**
     * valida el login del usuario
     * @param usuario
     * @param clave
     * @return
     * @throws Exception 
     */
    public boolean validarClave(String usuario,String clave) throws ClaveNoValidaException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, ClassNotFoundException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;

    /**
     * descomprime los archivos
     * @param zipfile
     * @param descDir
     * @throws IOException 
     */
    public void unZipFiles(File zipfile, String descDir) throws IOException;
}
