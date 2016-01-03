/**
 * 
 */
package edu.ucue.jparking.srv;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
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
import java.util.Calendar;
import java.util.Set;

/**
 *
 * @author Santos Gallegos
 */
public class JP implements JPInterface {
    private static JP instance;
    
    private static CampusService campusService = new CampusService();
    private static UsuarioService usuarioService = new UsuarioService();
    private static ImpresionOrdenPagosrv impresionOrdenPagosrv = new ImpresionOrdenPagosrv();
    private static OrdenPagoService ordenPagoService = new OrdenPagoService();
    private static ParqueaderoService parqueaderoService = new ParqueaderoService();
    private static PorterosService porterosService = new PorterosService();
    private static PuertaService puertaService = new PuertaService();
    private static RegistroService registroService = new RegistroService();
    
    private JP(){}
    
    public static JP getInstance(){
        if(instance == null)
            instance = new JP();
        return instance;
    }

    @Override
    public void addCampus(String nombre, String direccion) throws CampusExistenteExeption {
        campusService.addCampus(nombre, direccion);
    }

    @Override
    public Campus getCampus(String nombre) throws CampusNoExistenteException {
        return campusService.getCampus(nombre);
    }

    @Override
    public void modCampus(String nombre, String direccion, boolean estado) throws CampusNoExistenteException {
        campusService.modCampus(nombre, direccion, estado);
    }

    @Override
    public void delCampus(String nombre) throws CampusNoExistenteException, ParqueaderoNoExistenteException, UsuarioNoExistenteException, UsuarioNoAgregadoException {
        campusService.delCampus(nombre);
    }

    @Override
    public Set<Campus> getCampus() {
        return campusService.getCampus();
    }

    @Override
    public OrdenPago getOrdenPago(String cedula) 
            throws CedulaNoValidaException, UsuarioNoExistenteException,
            ContratoNoEstablecidoException, FueraDelDiaDePagoException,
            UsuarioNoRegistradoEnUnParqueaderoException
    {
        return ordenPagoService.getOrdenPago(cedula);
    }

    @Override
    public void pagarOrdenPago(String cedula) throws CedulaNoValidaException, UsuarioNoExistenteException, PagoYaRealizadoException {
        ordenPagoService.pagarOrdenPago(cedula);
    }

    @Override
    public void addParqueadero(String ubicacion, int numeroLugares, String id, String nombreCampus) throws ParqueaderoYaExistenteException, CampusNoExistenteException, CodigoNoValidoException, CampusInactivoException {
        parqueaderoService.addParqueadero(ubicacion, numeroLugares, id, nombreCampus);
    }

    @Override
    public void delParqueadero(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, CampusNoExistenteException,
            CodigoNoValidoException, UsuarioNoExistenteException, UsuarioNoAgregadoException,
            UsuarioNoAgregadoException, CampusInactivoException
    {
        parqueaderoService.delParqueadero(nombreCampus, idParqueadero);
    }

    @Override
    public Parqueadero getParqueadero(String nombreCampus, String idParqueadero) throws ParqueaderoNoExistenteException, CodigoNoValidoException, CampusNoExistenteException {
        return parqueaderoService.getParqueadero(nombreCampus, idParqueadero);
    }

    @Override
    public Set<Parqueadero> getParqueaderos() throws CampusNoExistenteException {
        return parqueaderoService.getParqueaderos();
    }

    @Override
    public Set<Parqueadero> getParqueaderos(String idCampus) throws CampusNoExistenteException {
        return parqueaderoService.getParqueaderos(idCampus);
    }

    @Override
    public void modParqueadero(String nombreCampus, String idParqueadero, String ubicacion, int numLugares, boolean estado) throws ParqueaderoNoExistenteException, CodigoNoValidoException, LugaresDeParqueoOCupadosException, NumeroLugaresDeParqueoInsuficientesException, CampusNoExistenteException {
        parqueaderoService.modParqueadero(nombreCampus, idParqueadero, ubicacion, numLugares, estado);
    }

    @Override
    public void addPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta) throws ParqueaderoNoExistenteException, PuertaNoExistenteException, PuertaYaAgregadaException, CodigoNoValidoException, ParquaderoInactivoException, PuertaYaExistenteException, CampusNoExistenteException, PuertaInactivaException, CampusInactivoException {
        parqueaderoService.addPuertaEntrada(nombreCampus, idParqueadero, idPuerta);
    }

    @Override
    public void addPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta) throws ParqueaderoNoExistenteException, PuertaNoExistenteException, PuertaYaAgregadaException, CodigoNoValidoException, ParquaderoInactivoException, CampusNoExistenteException, PuertaInactivaException, CampusInactivoException {
        parqueaderoService.addPuertaSalida(nombreCampus, idParqueadero, idPuerta);
    }

    @Override
    public void delPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CodigoNoValidoException, CampusNoExistenteException, PuertaNoAgregadaException {
        parqueaderoService.delPuertaEntrada(nombreCampus, idParqueadero, idPuerta);
    }

    @Override
    public void delPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta) throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CodigoNoValidoException, CampusNoExistenteException, PuertaNoAgregadaException {
        parqueaderoService.delPuertaSalida(nombreCampus, idParqueadero, idPuerta);
    }

    @Override
    public void addUsuario(String nombreCampus, String idParqueadero, String cedula) throws CedulaNoValidaException, CodigoNoValidoException, ParqueaderoNoExistenteException, UsuarioYaAgregadoException, UsuarioNoExistenteException, ParquaderoInactivoException, NumeroParqueaderosNoDisponiblesException, UsuarioInactivoException, CampusNoExistenteException, CampusInactivoException {
        parqueaderoService.addUsuario(nombreCampus, idParqueadero, cedula);
    }

    @Override
    public void delUsuario(String nombreCampus, String idParqueadero, String cedula) throws CedulaNoValidaException, CodigoNoValidoException, ParqueaderoNoExistenteException, UsuarioNoExistenteException, UsuarioNoAgregadoException, CampusNoExistenteException, CampusInactivoException, ParquaderoInactivoException {
        parqueaderoService.delUsuario(nombreCampus, idParqueadero, cedula);
    }

    @Override
    public Set<Puerta> getPuertasEntrada(String nombreCampus, String idParqueadero) throws CodigoNoValidoException, ParqueaderoNoExistenteException, CampusNoExistenteException {
        return parqueaderoService.getPuertasEntrada(nombreCampus, idParqueadero);
    }

    @Override
    public Set<Puerta> getPuertasSalida(String nombreCampus, String idParqueadero) throws CodigoNoValidoException, ParqueaderoNoExistenteException, CampusNoExistenteException {
        return parqueaderoService.getPuertasSalida(nombreCampus, idParqueadero);
    }

    @Override
    public Set<Usuario> getUsuariosParqueadero(String nombreCampus, String idParqueadero) throws CodigoNoValidoException, ParqueaderoNoExistenteException, UsuarioNoExistenteException, CampusNoExistenteException {
        return parqueaderoService.getUsuarios(nombreCampus, idParqueadero);
    }

    @Override
    public void addPortero(String nombreCampus, String cedula, String nombre, String apellido, String direccion, String telefono) throws CedulaNoValidaException, CampusNoExistenteException, PorteroYaExistenteException, TelefonoNoValidoException, PersonaYaRegistradaComoUsuarioException {
        porterosService.addPortero(nombreCampus, cedula, nombre, apellido, direccion, telefono);
    }

    @Override
    public void modPortero(String cedula, String nombre, String apellido, String direccion, String telefono, boolean estado) throws CedulaNoValidaException, PorteroNoExistenteException, TelefonoNoValidoException {
        porterosService.modPortero(cedula, nombre, apellido, direccion, telefono, estado);
    }

    @Override
    public void delPortero(String cedula) throws CedulaNoValidaException, PorteroNoExistenteException, CampusNoExistenteException {
        porterosService.delPortero(cedula);
    }

    @Override
    public Portero getPortero(String cedula) throws CedulaNoValidaException {
        return porterosService.getPortero(cedula);
    }

    @Override
    public Set<Portero> getPorteros() {
        return porterosService.getPorteros();
    }

    @Override
    public Set<Portero> getPorteros(String nombreCampus) throws CampusNoExistenteException {
        return porterosService.getPorteros(nombreCampus);
    }

    @Override
    public void addpuerta(String ubicacion, String id, String idCampus) throws CodigoNoValidoException, PuertaYaExistenteException, CampusNoExistenteException {
        puertaService.addpuerta(ubicacion, id, idCampus);
    }

    @Override
    public void delpuerta(String nombreCampus, String id) throws CodigoNoValidoException, PuertaNoExistenteException, CampusNoExistenteException, ParqueaderoNoExistenteException {
        puertaService.delpuerta(nombreCampus, id);
    }

    @Override
    public Puerta getPuerta(String nombreCampus, String id) throws CodigoNoValidoException, PuertaNoExistenteException, CampusNoExistenteException {
        return puertaService.getPuerta(nombreCampus, id);
    }

    @Override
    public void modPuerta(String nombreCampus, String id, String ubicacion, boolean activo) throws CodigoNoValidoException, PuertaNoExistenteException, CampusNoExistenteException {
        puertaService.modPuerta(nombreCampus, id, ubicacion, activo);
    }

    @Override
    public Set<Puerta> getPuertas(String nombreCampus) throws CampusNoExistenteException {
        return puertaService.getPuertas(nombreCampus);
    }

    @Override
    public Set<Puerta> getPuertas() {
        return puertaService.getPuertas();
    }

    @Override
    public Set<Registro> getRegistros(TipoRegistro tipoRegistro, Calendar fechaInicio, Calendar fechaFinal) throws FechaInicialMayorAFechaFinalException, FechaFinalMenorAFechaInicialException, FechaInicialIgualAFechaFinalException {
        return registroService.get(tipoRegistro, fechaInicio, fechaFinal);
    }

    @Override
    public Set<Registro> getRegistros(TipoRegistro tipoRegistro) {
        return registroService.get(tipoRegistro);
    }

    @Override
    public Set<Registro> getRegistros() {
        return registroService.get();
    }

    @Override
    public Set<Registro> getRegistros(Calendar fechaInicio, Calendar fechaFinal) throws FechaInicialMayorAFechaFinalException, FechaFinalMenorAFechaInicialException, FechaInicialIgualAFechaFinalException {
        return registroService.get(fechaInicio, fechaFinal);
    }

    @Override
    public Registro getRegistro(String idRegistro) throws RegistroNoExistenteException {
        return registroService.getRegistro(idRegistro);
    }

    @Override
    public void addUsuario(String cedula, String nombre, String apellido, String direccion, String telefono, String tipoUsuario) throws UsuarioYaExistenteException, CedulaNoValidaException, TelefonoNoValidoException, PersonaYaRegistradoComoPorteroException, UsuarioNoExistenteException {
        usuarioService.add(cedula, nombre, apellido, direccion, telefono, tipoUsuario);
    }

    @Override
    public void delUsuario(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException, IllegalArgumentException, CampusNoExistenteException {
        usuarioService.del(cedula);
    }

    @Override
    public void modUsuario(String cedula, String nombre, String apellido, String direccion, String telefono, boolean estado) throws CedulaNoValidaException, UsuarioNoExistenteException {
        usuarioService.mod(cedula, nombre, apellido, direccion, telefono, estado);
    }

    @Override
    public Usuario getUsuario(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException {
        return usuarioService.get(cedula);
    }

    @Override
    public Set<Usuario> getUsuarios() {
        return usuarioService.getLista();
    }

    @Override
    public Set<Usuario> getUsuarios(TipoUsuario tipoUsuario) {
        return usuarioService.getLista(tipoUsuario);
    }

    @Override
    public Set<Parqueadero> getParqueaderosUsuario(String cedula) throws CedulaNoValidaException, UsuarioNoExistenteException {
        return usuarioService.getParqueaderosUsuario(cedula);
    }

    @Override
    public void autenticarUsuario(String nombreCampus, String idPuerta, String cedula) throws CedulaNoValidaException, UsuarioNoExistenteException, CodigoNoValidoException, ParqueaderoNoExistenteException, AccesoNoAutorizadoException, CampusNoExistenteException {
        usuarioService.autenticarUsuario(nombreCampus, idPuerta, cedula);
    }

    @Override
    public File exportarOrdenPago(String cedula) throws UsuarioNoRegistradoEnUnParqueaderoException, DocumentException, FileNotFoundException, UsuarioNoExistenteException, CedulaNoValidaException, ContratoNoEstablecidoException, FueraDelDiaDePagoException, BadElementException, IOException {
        return impresionOrdenPagosrv.impresion(cedula);
    }
}
