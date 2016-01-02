/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

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
import edu.ucue.jparking.srv.enums.TipoRegistro;
import edu.ucue.jparking.srv.enums.TipoTramite;
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
import edu.ucue.jparking.srv.excepciones.PuertaInactivaException;
import edu.ucue.jparking.srv.excepciones.TelefonoNoValidoException;
import edu.ucue.jparking.srv.excepciones.UsuarioInactivoException;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.OrdenPago;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Portero;
import edu.ucue.jparking.srv.objetos.Puerta;
import edu.ucue.jparking.srv.objetos.Usuario;
import edu.ucue.jparking.srv.objetos.registros.Registro;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public class JP implements JPInterface {
    private static JP instance;
    private static CampusService campusService = new CampusService();
    
    public JP(){}
    
    public static JP getInstance(){
        if(instance == null)
            instance = new JP();
        return instance;
    }

    @Override
    public void addCampus(String nombre, String direccion) throws CampusExistenteExeption {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Campus getCampus(String nombre) throws CampusNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modCampus(String nombre, String direccion, boolean estado) throws CampusNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delCampus(String nombre) throws CampusNoExistenteException, ParqueaderoNoExistenteException, UsuarioNoExistenteException, UsuarioNoAgregadoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Campus> getCampus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrdenPago getOrdenPago(String cedula) throws CedulaNoValidaException, UsuarioNoExistenteException, ContratoNoEstablecidoException, FueraDelDiaDePagoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pagarOrdenPago(String cedula) throws CedulaNoValidaException, UsuarioNoExistenteException, PagoYaRealizadoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addParqueadero(String ubicacion, int numeroLugares, String id, String nombreCampus) throws ParqueaderoYaExistenteException, CampusNoExistenteException, CodigoNoValidoException, CampusInactivoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delParqueadero(String nombreCampus, String idParqueadero) throws ParqueaderoNoExistenteException, CampusNoExistenteException, CodigoNoValidoException, UsuarioNoExistenteException, UsuarioNoAgregadoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Parqueadero getParqueadero(String nombreCampus, String idParqueadero) throws ParqueaderoNoExistenteException, CodigoNoValidoException, CampusNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Parqueadero> getParqueaderos() throws CampusNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Parqueadero> getParqueaderos(String idCampus) throws CampusNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modParqueadero(String nombreCampus, String idParqueadero, String ubicacion, int numLugares, boolean estado) throws ParqueaderoNoExistenteException, CodigoNoValidoException, LugaresDeParqueoOCupadosException, NumeroLugaresDeParqueoInsuficientesException, CampusNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta) throws ParqueaderoNoExistenteException, PuertaNoExistenteException, PuertaYaAgregadaException, CodigoNoValidoException, ParquaderoInactivoException, PuertaYaExistenteException, CampusNoExistenteException, PuertaInactivaException, CampusInactivoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta) throws ParqueaderoNoExistenteException, PuertaNoExistenteException, PuertaYaAgregadaException, CodigoNoValidoException, ParquaderoInactivoException, CampusNoExistenteException, PuertaInactivaException, CampusInactivoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CodigoNoValidoException, CampusNoExistenteException, PuertaNoAgregadaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CodigoNoValidoException, CampusNoExistenteException, PuertaNoAgregadaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addUsuario(String nombreCampus, String idParqueadero, String cedula) throws CedulaNoValidaException, CodigoNoValidoException, ParqueaderoNoExistenteException, UsuarioYaAgregadoException, UsuarioNoExistenteException, ParquaderoInactivoException, NumeroParqueaderosNoDisponiblesException, UsuarioInactivoException, CampusNoExistenteException, CampusInactivoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delUsuario(String nombreCampus, String idParqueadero, String cedula) throws CedulaNoValidaException, CodigoNoValidoException, ParqueaderoNoExistenteException, UsuarioNoExistenteException, UsuarioNoAgregadoException, CampusNoExistenteException, CampusInactivoException, ParquaderoInactivoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Puerta> getPuertasEntrada(String nombreCampus, String idParqueadero) throws CodigoNoValidoException, ParqueaderoNoExistenteException, CampusNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Puerta> getPuertasSalida(String nombreCampus, String idParqueadero) throws CodigoNoValidoException, ParqueaderoNoExistenteException, CampusNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Usuario> getUsuarios(String nombreCampus, String idParqueadero) throws CodigoNoValidoException, ParqueaderoNoExistenteException, UsuarioNoExistenteException, CampusNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPortero(String nombreCampus, String cedula, String nombre, String apellido, String direccion, String telefono) throws CedulaNoValidaException, CampusNoExistenteException, PorteroYaExistenteException, TelefonoNoValidoException, PersonaYaRegistradaComoUsuarioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modPortero(String cedula, String nombre, String apellido, String direccion, String telefono, boolean estado) throws CedulaNoValidaException, PorteroNoExistenteException, TelefonoNoValidoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delPortero(String cedula) throws CedulaNoValidaException, PorteroNoExistenteException, CampusNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Portero getPortero(String cedula) throws CedulaNoValidaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Portero> getPorteros() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Portero> getPorteros(String nombreCampus) throws CampusNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addpuerta(String ubicacion, String id, String idCampus) throws CodigoNoValidoException, PuertaYaExistenteException, CampusNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delpuerta(String nombreCampus, String id) throws CodigoNoValidoException, PuertaNoExistenteException, CampusNoExistenteException, ParqueaderoNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Puerta getPuerta(String nombreCampus, String id) throws CodigoNoValidoException, PuertaNoExistenteException, CampusNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modPuerta(String nombreCampus, String id, String ubicacion, boolean activo) throws CodigoNoValidoException, PuertaNoExistenteException, CampusNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Puerta> getPuertas(String nombreCampus) throws CampusNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Puerta> getPuertas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Registro registro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(String cedula, TipoRegistro tipoRegistro, TipoTramite tipoTramite) throws UsuarioNoExistenteException, CedulaNoValidaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Registro> get(TipoRegistro tipoRegistro, Calendar fechaInicio, Calendar fechaFinal) throws FechaInicialMayorAFechaFinalException, FechaFinalMenorAFechaInicialException, FechaInicialIgualAFechaFinalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Registro> get(TipoRegistro tipoRegistro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Registro> get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Registro> get(Calendar fechaInicio, Calendar fechaFinal) throws FechaInicialMayorAFechaFinalException, FechaFinalMenorAFechaInicialException, FechaInicialIgualAFechaFinalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Registro getRegistro(String idRegistro) throws RegistroNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(String cedula, String nombre, String apellido, String direccion, String telefono, String tipoUsuario) throws UsuarioYaExistenteException, CedulaNoValidaException, TelefonoNoValidoException, PersonaYaRegistradoComoPorteroException, UsuarioNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void del(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException, IllegalArgumentException, CampusNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mod(String cedula, String nombre, String apellido, String direccion, String telefono, boolean estado) throws CedulaNoValidaException, UsuarioNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario get(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Usuario> getLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Usuario> getLista(TipoUsuario tipoUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Parqueadero> getParqueaderosUsuario(String cedula) throws CedulaNoValidaException, UsuarioNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void autenticarUsuario(String nombreCampus, String idPuerta, String cedula) throws CedulaNoValidaException, UsuarioNoExistenteException, CodigoNoValidoException, ParqueaderoNoExistenteException, AccesoNoAutorizadoException, CampusNoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
