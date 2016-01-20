/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.dao.OrdenesPagoDAO;
import edu.ucue.jparking.dao.UsuariosDAO;
import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.interfaces.OrdenPagoNoExistenteException;
import edu.ucue.jparking.dao.interfaces.OrdenesPagoDAOInterface;
import edu.ucue.jparking.dao.interfaces.UsuariosDAOInterface;
import edu.ucue.jparking.srv.enums.TipoTramite;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.ContratoNoEstablecidoException;
import edu.ucue.jparking.srv.excepciones.FechaFinalMenorAFechaInicialException;
import edu.ucue.jparking.srv.excepciones.FechaInicialIgualAFechaFinalException;
import edu.ucue.jparking.srv.excepciones.FechaInicialMayorAFechaFinalException;
import edu.ucue.jparking.srv.excepciones.PagoYaRealizadoException;
import edu.ucue.jparking.srv.excepciones.FueraDelDiaDePagoException;
import edu.ucue.jparking.srv.objetos.OrdenPago;
import edu.ucue.jparking.srv.excepciones.UsuarioNoRegistradoEnUnParqueaderoException;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * @author Franklin Lara
 */
class OrdenPagoService {
    
    private final static RegistroService registroService = new RegistroService();
    
    /**
     * 
     */
    OrdenesPagoDAOInterface ordenesPagoDAO = null;
    
    Calendar fechaActual= Calendar.getInstance();
    Validaciones validaciones = new Validaciones();

    
    
    public OrdenPago getOrdenPago(String cedula) throws CedulaNoValidaException, UsuarioNoExistenteException, ContratoNoEstablecidoException, FueraDelDiaDePagoException, UsuarioNoRegistradoEnUnParqueaderoException, IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException{
        ordenesPagoDAO = OrdenesPagoDAO.getInstance();
        UsuariosDAOInterface usuariosDAO = UsuariosDAO.getInstance();
        validaciones.validarCedula(cedula);
        OrdenPago ordenPago = usuariosDAO.getUsuario(cedula).generarOrdenPago();
        //Registro
        registroService.add(usuariosDAO.getUsuario(cedula).getRegistro(TipoTramite.EMISION));
        //Fin registro
        return ordenPago;
    }
    
    public void pagarOrdenPago(String cedula)
            throws CedulaNoValidaException, UsuarioNoExistenteException,
            PagoYaRealizadoException, ContratoNoEstablecidoException,
            FueraDelDiaDePagoException, UsuarioNoRegistradoEnUnParqueaderoException, IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException
    {
        UsuariosDAOInterface usuariosDAO = UsuariosDAO.getInstance();
        validaciones.validarCedula(cedula);
        Usuario u = usuariosDAO.getUsuario(cedula);
        addOrdenPago(cedula, u.getValorParqueadero());
        u.cancelarPago();
        // Actualizar
        usuariosDAO.update(cedula, u);
        
        //Registro
        registroService.add(usuariosDAO.getUsuario(cedula).getRegistro(TipoTramite.COBRO));
    }
    
    public void addOrdenPago(String cedula, Double costo) throws CedulaNoValidaException, UsuarioNoExistenteException, ContratoNoEstablecidoException, FueraDelDiaDePagoException, UsuarioNoRegistradoEnUnParqueaderoException, IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException{
        ordenesPagoDAO = OrdenesPagoDAO.getInstance();
        validaciones.validarCedula(cedula);
        UsuariosDAOInterface usuariosDAO = UsuariosDAO.getInstance();
        if(costo==null | costo<0)
            throw new IllegalArgumentException("No puede tener un costo menos a 0");
        OrdenPago op = usuariosDAO.getUsuario(cedula).generarOrdenPago();
        ordenesPagoDAO.addOrdenPago(op);
    }
    
    public OrdenPago getOrdenPago(int numOrden) throws OrdenPagoNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
        ordenesPagoDAO = OrdenesPagoDAO.getInstance();
        if(numOrden < 0)
            throw new IllegalArgumentException("El numero de orden no puede ser nulo");
        return ordenesPagoDAO.getOrdenPago(numOrden);
    }
    
    public Set<OrdenPago> getOrdenPago() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
        ordenesPagoDAO = OrdenesPagoDAO.getInstance();
        return ordenesPagoDAO.getOrdenesPago();
    }
    
    public Set<OrdenPago> getOrdenPago(Calendar fechaInicial, Calendar fechaFinal)
            throws FechaInicialMayorAFechaFinalException, 
            FechaFinalMenorAFechaInicialException, FechaInicialIgualAFechaFinalException,
             IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        
        ordenesPagoDAO = OrdenesPagoDAO.getInstance();
        Validaciones.validarFecha(fechaInicial, fechaFinal);
        return ordenesPagoDAO.getOrdenesPago(fechaInicial, fechaFinal);
    }
    
    public double getFondos() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
        ordenesPagoDAO = OrdenesPagoDAO.getInstance();
        return ordenesPagoDAO.getFondos();
    }
    
    public double getFondos(Calendar fechaInicial, Calendar fechaFinal)
            throws FechaInicialMayorAFechaFinalException, FechaFinalMenorAFechaInicialException,
            FechaInicialIgualAFechaFinalException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        ordenesPagoDAO = OrdenesPagoDAO.getInstance();
        Validaciones.validarFecha(fechaInicial, fechaFinal);
        return ordenesPagoDAO.getFondos(fechaInicial, fechaFinal);
    }
}
