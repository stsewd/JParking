/**
 * 
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
import java.nio.file.Path;
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
public class JP implements JPInterface {
    private static JP instance;
    
    private static CampusService campusService ;
    private static UsuarioService usuarioService;
    private static ExportOrdenPagoService impresionOrdenPagosrv;
    private static OrdenPagoService ordenPagoService;
    private static ParqueaderoService parqueaderoService;
    private static PorterosService porterosService;
    private static PuertaService puertaService;
    private static RegistroService registroService;
    private static BackupService backupService;
    private static ClaveService claveService;
    
    private JP() 
            throws IOException, FileNotFoundException, ClassNotFoundException,
            ObjectSizeException
    {
        campusService = new CampusService();
        usuarioService = new UsuarioService();
        impresionOrdenPagosrv = new ExportOrdenPagoService();
        ordenPagoService = new OrdenPagoService();
        parqueaderoService = new ParqueaderoService();
        porterosService = new PorterosService();
        puertaService = new PuertaService();
        registroService = new RegistroService();
        backupService = new BackupService();
        claveService = new ClaveService();
    }
    
    public static JP getInstance(){
        if(instance == null){
            try{
                instance = new JP();
            }catch (Exception ex){
                try{
                    Utilidades.iniciarDefaultDirectorios();
                    instance = new JP();
                }catch (Exception e){
                }
            }
        }
        return instance;
    }

    @Override
    public void addCampus(String nombre, String direccion) throws CampusExistenteExeption, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        campusService.addCampus(nombre, direccion);
    }

    @Override
    public Campus getCampus(String nombre) throws CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException {
        return campusService.getCampus(nombre);
    }

    @Override
    public void modCampus(String nombre, String direccion, boolean estado) throws CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        campusService.modCampus(nombre, direccion, estado);
    }

    @Override
    public void delCampus(String nombre)
            throws CampusNoExistenteException, ParqueaderoNoExistenteException,
            UsuarioNoExistenteException, UsuarioNoAgregadoException , IOException, 
            ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        campusService.delCampus(nombre);
    }

    @Override
    public Set<Campus> getCampus() throws IOException, FileNotFoundException, ClassNotFoundException {
        return campusService.getCampus();
    }

    @Override
    public OrdenPago getOrdenPago(String cedula) 
            throws CedulaNoValidaException, UsuarioNoExistenteException,
            ContratoNoEstablecidoException, FueraDelDiaDePagoException,
            UsuarioNoRegistradoEnUnParqueaderoException, IOException, 
            ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        return ordenPagoService.getOrdenPago(cedula);
    }

    @Override
    public void pagarOrdenPago(String cedula)
            throws CedulaNoValidaException, UsuarioNoExistenteException, 
            PagoYaRealizadoException, ContratoNoEstablecidoException,
            FueraDelDiaDePagoException, UsuarioNoRegistradoEnUnParqueaderoException,
            IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        ordenPagoService.pagarOrdenPago(cedula);
    }

    @Override
    public void addParqueadero(String ubicacion, int numeroLugares, String id, String nombreCampus)
            throws ParqueaderoYaExistenteException, CampusNoExistenteException, CodigoNoValidoException,
            CampusInactivoException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        parqueaderoService.addParqueadero(ubicacion, numeroLugares, id, nombreCampus);
    }

    @Override
    public void delParqueadero(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, CampusNoExistenteException,
            CodigoNoValidoException, UsuarioNoExistenteException, UsuarioNoAgregadoException,
            UsuarioNoAgregadoException, CampusInactivoException,
            IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        parqueaderoService.delParqueadero(nombreCampus, idParqueadero);
    }

    @Override
    public Parqueadero getParqueadero(String nombreCampus, String idParqueadero)
            throws ParqueaderoNoExistenteException, CodigoNoValidoException, CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        return parqueaderoService.getParqueadero(nombreCampus, idParqueadero);
    }

    @Override
    public Set<Parqueadero> getParqueaderos() throws CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        return parqueaderoService.getParqueaderos();
    }

    @Override
    public Set<Parqueadero> getParqueaderos(String idCampus) throws CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        return parqueaderoService.getParqueaderos(idCampus);
    }

    @Override
    public void modParqueadero(String nombreCampus, String idParqueadero, String ubicacion, int numLugares, boolean estado)
            throws ParqueaderoNoExistenteException, CodigoNoValidoException, LugaresDeParqueoOCupadosException,
            NumeroLugaresDeParqueoInsuficientesException, CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException 
    {
        parqueaderoService.modParqueadero(nombreCampus, idParqueadero, ubicacion, numLugares, estado);
    }

    @Override
    public void addPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta) 
            throws ParqueaderoNoExistenteException, PuertaNoExistenteException,
            PuertaYaAgregadaException, CodigoNoValidoException, ParquaderoInactivoException,
            PuertaYaExistenteException, CampusNoExistenteException, PuertaInactivaException,
            CampusInactivoException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        parqueaderoService.addPuertaEntrada(nombreCampus, idParqueadero, idPuerta);
    }

    @Override
    public void addPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta)
            throws ParqueaderoNoExistenteException, PuertaNoExistenteException, PuertaYaAgregadaException,
            CodigoNoValidoException, ParquaderoInactivoException, CampusNoExistenteException,
            PuertaInactivaException, CampusInactivoException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException 
    {
        parqueaderoService.addPuertaSalida(nombreCampus, idParqueadero, idPuerta);
    }

    @Override
    public void delPuertaEntrada(String nombreCampus, String idParqueadero, String idPuerta)
            throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CodigoNoValidoException,
            CampusNoExistenteException, PuertaNoAgregadaException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        parqueaderoService.delPuertaEntrada(nombreCampus, idParqueadero, idPuerta);
    }

    @Override
    public void delPuertaSalida(String nombreCampus, String idParqueadero, String idPuerta)
            throws PuertaNoExistenteException, ParqueaderoNoExistenteException, CodigoNoValidoException,
            CampusNoExistenteException, PuertaNoAgregadaException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException 
    {
        parqueaderoService.delPuertaSalida(nombreCampus, idParqueadero, idPuerta);
    }

    @Override
    public void addUsuario(String nombreCampus, String idParqueadero, String cedula) 
            throws CedulaNoValidaException, CodigoNoValidoException, ParqueaderoNoExistenteException,
            UsuarioYaAgregadoException, UsuarioNoExistenteException, ParquaderoInactivoException, 
            NumeroParqueaderosNoDisponiblesException, UsuarioInactivoException, CampusNoExistenteException, 
            CampusInactivoException,IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        parqueaderoService.addUsuario(nombreCampus, idParqueadero, cedula);
    }

    @Override
    public void delUsuario(String nombreCampus, String idParqueadero, String cedula) 
            throws CedulaNoValidaException, CodigoNoValidoException, ParqueaderoNoExistenteException,
            UsuarioNoExistenteException, UsuarioNoAgregadoException, CampusNoExistenteException, 
            CampusInactivoException, ParquaderoInactivoException, IOException, ClassNotFoundException,
            FileNotFoundException, ObjectSizeException
    {
        parqueaderoService.delUsuario(nombreCampus, idParqueadero, cedula);
    }

    @Override
    public Set<Puerta> getPuertasEntrada(String nombreCampus, String idParqueadero)
            throws CodigoNoValidoException, ParqueaderoNoExistenteException, CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        return parqueaderoService.getPuertasEntrada(nombreCampus, idParqueadero);
    }

    @Override
    public Set<Puerta> getPuertasSalida(String nombreCampus, String idParqueadero)
            throws CodigoNoValidoException, ParqueaderoNoExistenteException, CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        return parqueaderoService.getPuertasSalida(nombreCampus, idParqueadero);
    }

    @Override
    public Set<Usuario> getUsuariosParqueadero(String nombreCampus, String idParqueadero)
            throws CodigoNoValidoException, ParqueaderoNoExistenteException, UsuarioNoExistenteException,
            CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        return parqueaderoService.getUsuarios(nombreCampus, idParqueadero);
    }

    @Override
    public void addPortero(String nombreCampus, String cedula, String nombre, String apellido, String direccion, String telefono) 
            throws CedulaNoValidaException, CampusNoExistenteException, PorteroYaExistenteException, 
            TelefonoNoValidoException, PersonaYaRegistradaComoUsuarioException,
            IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        porterosService.addPortero(nombreCampus, cedula, nombre, apellido, direccion, telefono);
    }

    @Override
    public void modPortero(String cedula, String nombre, String apellido, String direccion, String telefono, boolean estado)
            throws CedulaNoValidaException, PorteroNoExistenteException, TelefonoNoValidoException,
            IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException, CampusNoExistenteException
    {
        porterosService.modPortero(cedula, nombre, apellido, direccion, telefono, estado);
    }

    @Override
    public void delPortero(String cedula)
            throws  CedulaNoValidaException, PorteroNoExistenteException, CampusNoExistenteException,
            IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        porterosService.delPortero(cedula);
    }

    @Override
    public Portero getPortero(String cedula) throws CedulaNoValidaException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        return porterosService.getPortero(cedula);
    }

    @Override
    public Set<Portero> getPorteros() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        return porterosService.getPorteros();
    }

    @Override
    public Set<Portero> getPorteros(String nombreCampus) throws CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        return porterosService.getPorteros(nombreCampus);
    }

    @Override
    public void addpuerta(String ubicacion, String id, String idCampus)
            throws CodigoNoValidoException, PuertaYaExistenteException, CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        puertaService.addpuerta(ubicacion, id, idCampus);
    }

    @Override
    public void delpuerta(String nombreCampus, String id)
            throws CodigoNoValidoException, PuertaNoExistenteException, CampusNoExistenteException,
            ParqueaderoNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException 
    {
        puertaService.delpuerta(nombreCampus, id);
    }

    @Override
    public Puerta getPuerta(String nombreCampus, String id)
            throws CodigoNoValidoException, PuertaNoExistenteException, CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        return puertaService.getPuerta(nombreCampus, id);
    }

    @Override
    public void modPuerta(String nombreCampus, String id, String ubicacion, boolean activo)
            throws CodigoNoValidoException, PuertaNoExistenteException, CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        puertaService.modPuerta(nombreCampus, id, ubicacion, activo);
    }

    @Override
    public Set<Puerta> getPuertas(String nombreCampus) throws CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        return puertaService.getPuertas(nombreCampus);
    }

    @Override
    public Set<Puerta> getPuertas() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        return puertaService.getPuertas();
    }

    @Override
    public Set<Registro> getRegistros(TipoRegistro tipoRegistro, Calendar fechaInicio, Calendar fechaFinal)
            throws FechaInicialMayorAFechaFinalException, FechaFinalMenorAFechaInicialException,
            FechaInicialIgualAFechaFinalException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException
    {
        return registroService.get(tipoRegistro, fechaInicio, fechaFinal);
    }

    @Override
    public Set<Registro> getRegistros(TipoRegistro tipoRegistro)
            throws IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        return registroService.get(tipoRegistro);
    }

    @Override
    public Set<Registro> getRegistros()
            throws IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        return registroService.get();
    }

    @Override
    public Set<Registro> getRegistros(Calendar fechaInicio, Calendar fechaFinal)
            throws FechaInicialMayorAFechaFinalException, FechaFinalMenorAFechaInicialException,
            FechaInicialIgualAFechaFinalException, IOException, FileNotFoundException,
            ClassNotFoundException, ObjectSizeException 
    {
        return registroService.get(fechaInicio, fechaFinal);
    }

    @Override
    public Registro getRegistro(String idRegistro)
            throws RegistroNoExistenteException,IOException, ClassNotFoundException,
            FileNotFoundException, ObjectSizeException
    {
        return registroService.getRegistro(idRegistro);
    }

    @Override
    public void addUsuario(String cedula, String nombre, String apellido, String direccion, String telefono, String tipoUsuario)
            throws UsuarioYaExistenteException, CedulaNoValidaException, TelefonoNoValidoException,
            PersonaYaRegistradoComoPorteroException, UsuarioNoExistenteException, IOException,
            ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        usuarioService.add(cedula, nombre, apellido, direccion, telefono, tipoUsuario);
    }

    @Override
    public void delUsuario(String cedula)
            throws UsuarioNoExistenteException, CedulaNoValidaException, IllegalArgumentException,
            CampusNoExistenteException, IOException, ClassNotFoundException, FileNotFoundException,
            ObjectSizeException
    {
        usuarioService.del(cedula);
    }

    @Override
    public void modUsuario(String cedula, String nombre, String apellido, String direccion, String telefono, boolean estado) 
            throws CedulaNoValidaException, UsuarioNoExistenteException, TelefonoNoValidoException,
            IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        usuarioService.mod(cedula, nombre, apellido, direccion, telefono, estado);
    }

    @Override
    public Usuario getUsuario(String cedula)
            throws UsuarioNoExistenteException, CedulaNoValidaException, IOException,
            ClassNotFoundException, FileNotFoundException, ObjectSizeException 
    {
        return usuarioService.get(cedula);
    }

    @Override
    public Set<Usuario> getUsuarios()
            throws IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        return usuarioService.getUsuarios();
    }

    @Override
    public Set<Usuario> getUsuarios(TipoUsuario tipoUsuario)
            throws IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        return usuarioService.getUsuarios(tipoUsuario);
    }

    @Override
    public Set<Parqueadero> getParqueaderosUsuario(String cedula) 
            throws CedulaNoValidaException, UsuarioNoExistenteException, IOException,
            ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        return usuarioService.getParqueaderosUsuario(cedula);
    }

    @Override
    public void autenticarUsuario(String nombreCampus, String idPuerta, String cedula) 
            throws CedulaNoValidaException, UsuarioNoExistenteException, CodigoNoValidoException,
            ParqueaderoNoExistenteException, AccesoNoAutorizadoException, CampusNoExistenteException,
            PagoNoCanceladoException, PorteroInactivoException, UsuarioInactivoException,
            IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        usuarioService.autenticarUsuario(nombreCampus, idPuerta, cedula);
    }

    @Override
    public File exportarOrdenPago(String cedula)
            throws UsuarioNoRegistradoEnUnParqueaderoException, DocumentException,
            FileNotFoundException, UsuarioNoExistenteException, CedulaNoValidaException,
            ContratoNoEstablecidoException, FueraDelDiaDePagoException, BadElementException,
            IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        return impresionOrdenPagosrv.impresion(cedula);
    }

    @Override
    public OrdenPago getOrdenPago(int numeroOrdenPago) 
            throws OrdenPagoNoExistenteException ,IOException, ClassNotFoundException,
            FileNotFoundException, ObjectSizeException
    {
        return ordenPagoService.getOrdenPago(numeroOrdenPago);
    }

    @Override
    public Set<OrdenPago> getOrdenesPago()
            throws IOException, ClassNotFoundException, FileNotFoundException,
            ObjectSizeException
    {
        return ordenPagoService.getOrdenPago();
    }

    @Override
    public Set<OrdenPago> getOrdenesPago(Calendar fechaInicial, Calendar fechaFinal)
            throws FechaInicialMayorAFechaFinalException, IOException, ClassNotFoundException,
            FileNotFoundException, ObjectSizeException,FechaFinalMenorAFechaInicialException,
            FechaInicialIgualAFechaFinalException
    {
        return ordenPagoService.getOrdenPago(fechaInicial, fechaFinal);
    }

    @Override
    public double getFondos()
            throws IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        return ordenPagoService.getFondos();
    }

    @Override
    public double getFondos(Calendar fechaInicial, Calendar fechaFinal)
            throws FechaInicialMayorAFechaFinalException, FechaFinalMenorAFechaInicialException,
            FechaInicialIgualAFechaFinalException, IOException, ClassNotFoundException,
            FileNotFoundException, ObjectSizeException
    {
        
        return ordenPagoService.getFondos(fechaInicial, fechaFinal);
    }

    @Override
    public void makeZip(File clavePath) throws IOException, FileNotFoundException, Exception {
        backupService.generarZip(clavePath);
    }

    @Override
    public void cifrar(String clave)
            throws NoSuchAlgorithmException, NoSuchPaddingException, IOException,
            ClassNotFoundException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException
    {
        claveService.cifrar(clave);
    }

    @Override
    public boolean validarClave(String usuario, String clave)
            throws ClaveNoValidaException, NoSuchAlgorithmException, NoSuchPaddingException,
            IOException, ClassNotFoundException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException
    {
        return claveService.validarClave(usuario, clave);
    }

    @Override
    public void unZipFiles(File zipfile, File clavePath) throws IOException, Exception {
        backupService.unZipFiles(zipfile, new File("data"), clavePath);
    }

    @Override
    public void cambiarClave(String usuario, String claveActual, String nuevaClave)
            throws NoSuchAlgorithmException, NoSuchPaddingException, IOException,
            ClassNotFoundException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, ClaveNoValidaException
    {
        claveService.cambiarClave(usuario, claveActual, nuevaClave);
    }

    @Override
    public void generarClavesRSA(Path path) throws Exception {
        claveService.generarClavesRSA(path);
    }
}
