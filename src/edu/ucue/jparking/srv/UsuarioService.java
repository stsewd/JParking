/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.srv.excepciones.PorteroInactivoException;
import edu.ucue.jparking.srv.excepciones.PagoNoCanceladoException;
import edu.ucue.jparking.srv.excepciones.AccesoNoAutorizadoException;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.dao.UsuariosDAO;
import edu.ucue.jparking.dao.bptree.ObjectSizeException;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PersonaYaRegistradoComoPorteroException;
import edu.ucue.jparking.dao.interfaces.UsuariosDAOInterface;
import edu.ucue.jparking.srv.enums.TipoAcceso;
import edu.ucue.jparking.srv.enums.TipoModificacion;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.excepciones.TelefonoNoValidoException;
import edu.ucue.jparking.srv.excepciones.UsuarioInactivoException;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Portero;
import edu.ucue.jparking.srv.objetos.Puerta;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

/**
 *
 * @author Franklin Lara
 */
class UsuarioService {
    Validaciones validaciones = new Validaciones();
    private static final RegistroService registroService = new RegistroService();
    private final UsuariosDAOInterface usuariosDAO;

    UsuarioService() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        this.usuariosDAO = UsuariosDAO.getInstance();
    }
    
    public void add(String cedula, String nombre, String apellido, String direccion, String telefono, String tipoUsuario) 
            throws UsuarioYaExistenteException, CedulaNoValidaException,
            TelefonoNoValidoException, PersonaYaRegistradoComoPorteroException,
            UsuarioNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        if(tipoUsuario.equalsIgnoreCase("ESTUDIANTE")){
            EstudianteService estudianteService = new EstudianteService();
            estudianteService.add(cedula, nombre, apellido, direccion, telefono);
        }else if(tipoUsuario.equalsIgnoreCase("DOCENTE")){
            DocenteService docenteService = new DocenteService();
            docenteService.add(cedula, nombre, apellido,direccion,telefono);
        }else if(tipoUsuario.equalsIgnoreCase("EMPLEADO")){
            EmpleadoService empleadoService = new EmpleadoService();
            empleadoService.add(cedula, nombre, apellido,direccion,telefono);
        }else{
            throw new IllegalArgumentException("El argumento tipo usuario no puede estar vacio");
        }
        
        //Registro
        registroService.add(get(cedula).getRegistro(TipoModificacion.CREACION));
    }
    
    public void del(String cedula) 
            throws UsuarioNoExistenteException, CedulaNoValidaException, 
            IllegalArgumentException, CampusNoExistenteException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        if(cedula == null || cedula.trim().length() == 0)
            throw new IllegalArgumentException("El argumendo cedula no puede ser vacio.");
        validaciones.validarCedula(cedula);
        
        //Registro
        registroService.add(get(cedula).getRegistro(TipoModificacion.ELIMINACION));
        //Fin Registro
        
        usuariosDAO.delUsuario(cedula);        
    }
    
    public void mod(String cedula, String nombre, String apellido, String direccion, String telefono, boolean estado)
            throws CedulaNoValidaException, UsuarioNoExistenteException, TelefonoNoValidoException, IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        validaciones.validarCedula(cedula);
        validaciones.ValidarDatos(cedula, nombre, apellido, direccion, telefono);
        
        usuariosDAO.modUsuario(cedula, nombre, apellido, direccion, telefono, estado);
        //Registro
        registroService.add(get(cedula).getRegistro(TipoModificacion.MODIFICACION));
    }
    
    public Usuario get(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException, IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException{
        validaciones.validarCedula(cedula);
        Usuario u = usuariosDAO.getUsuario(cedula); 
        if (u == null)        
            throw new UsuarioNoExistenteException(cedula);
        return u;
    }
       
    public Set<Usuario> getUsuarios() throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
        return UsuariosDAO.getInstance().getUsuarios();
    }
    
    public Set<Usuario> getUsuarios(TipoUsuario tipoUsuario) throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
        return UsuariosDAO.getInstance().getUsuarios(tipoUsuario);
    }
    
    public Set<Parqueadero> getParqueaderosUsuario(String cedula) 
            throws CedulaNoValidaException, UsuarioNoExistenteException, IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException{
        validaciones.validarCedula(cedula);
        return usuariosDAO.getParqueaderos(cedula);
    }
    
    public void autenticarUsuario(String nombreCampus, String idPuerta, String cedula) 
            throws CedulaNoValidaException, UsuarioNoExistenteException, 
            CodigoNoValidoException, ParqueaderoNoExistenteException, 
            AccesoNoAutorizadoException, CampusNoExistenteException, PagoNoCanceladoException, PorteroInactivoException, UsuarioInactivoException, IOException, ClassNotFoundException, FileNotFoundException, ObjectSizeException 
    {
        validaciones.validarCedula(cedula);
        if(nombreCampus == null || nombreCampus.trim().length() == 0)
            throw new IllegalArgumentException("El argumento campus no puede ser nulo.");
        if(idPuerta == null || idPuerta.trim().length() == 0)
            throw new IllegalArgumentException("El argumento idPuerta no puede ser nulo.");
        
        PorterosService porterosService = new PorterosService();
        ParqueaderoService parqueaderoService = new ParqueaderoService();
        
        Portero portero = porterosService.getPortero(cedula);
        if(portero != null){
            //Permitir acceso
            if(portero.getCampus().getNombre().compareToIgnoreCase(nombreCampus) != 0)
                throw new AccesoNoAutorizadoException(cedula, portero.getTipoUsuarioString(), nombreCampus, idPuerta);
            if(!portero.isActivo())
                throw new PorteroInactivoException(portero.getCedula());
            //Registro
            registroService.add(portero.getRegistro(TipoAcceso.ACCESO));
            //Fin registro
            return;
        }
        
        Usuario u = get(cedula);
        boolean encontrado = false;
        
        for(Parqueadero p : getParqueaderosUsuario(cedula)){
            if(p.getCampus().getNombre().compareToIgnoreCase(nombreCampus) != 0)
                continue;
            
            if(!u.isIn()){
                for(Puerta pu : parqueaderoService.getPuertasEntrada(nombreCampus, p.getId())){
                    if(idPuerta.compareToIgnoreCase(pu.getId()) == 0){
                        encontrado = true;
                        break;
                    }
                }
                if(encontrado)
                    break;
            }else{
                for(Puerta pu : parqueaderoService.getPuertasSalida(nombreCampus, p.getId())){
                    if(idPuerta.compareToIgnoreCase(pu.getId()) == 0){
                        encontrado = true;
                        break;
                    }
                }
            
                if(encontrado)
                    break;
            }
        }
        
        if(!encontrado)
            throw new AccesoNoAutorizadoException(cedula, u.getTipoUsuarioString(), nombreCampus, idPuerta);
        if(!u.isActivo())
            throw new UsuarioInactivoException();
        if(u.estaDebiendo() || u.getFechaContrato() == null)
            throw new PagoNoCanceladoException(cedula);
        
        //Registro
        if(u.isIn()){
            usuariosDAO.setIn(cedula, false);
            registroService.add(u.getRegistro(TipoAcceso.SALIDA));
        }else {
            usuariosDAO.setIn(cedula, true);
            registroService.add(u.getRegistro(TipoAcceso.ENTRADA));
        }
    }
  
}
