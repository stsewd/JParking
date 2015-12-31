/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.srv.excepciones.AccesoNoAutorizadoException;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioYaExistenteException;
import edu.ucue.jparking.dao.UsuariosDAO;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PersonaYaRegistradoComoPorteroException;
import edu.ucue.jparking.dao.interfaces.UsuariosDAOInterface;
import edu.ucue.jparking.srv.enums.TipoAcceso;
import edu.ucue.jparking.srv.enums.TipoModificacion;
import edu.ucue.jparking.srv.enums.TipoUsuario;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.srv.excepciones.TelefonoNoValidoException;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Portero;
import edu.ucue.jparking.srv.objetos.Puerta;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.util.Set;

/**
 *
 * @author Franklin Lara
 */
public class UsuarioService {
    UsuariosDAOInterface usuariosDAO = UsuariosDAO.getInstance();
    Validaciones validaciones = new Validaciones();
    private static final RegistroService registroService = new RegistroService();
    
    public void add(String cedula, String nombre, String apellido,String direccion,String telefono, String tipoUsuario) throws UsuarioYaExistenteException, CedulaNoValidaException, TelefonoNoValidoException, PersonaYaRegistradoComoPorteroException, UsuarioNoExistenteException{
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
    
    public void del(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException, IllegalArgumentException, CampusNoExistenteException{
        if(cedula == null || cedula.trim().length() == 0)
            throw new IllegalArgumentException("El argumendo cedula no puede ser vacio.");
        validaciones.validarCedula(cedula);
        
        //Registro
        registroService.add(get(cedula).getRegistro(TipoModificacion.ELIMINACION));
        //Fin Registro
        
        usuariosDAO.delUsuario(cedula);        
    }
    
    public void mod(String cedula, String nombre, String apellido,String direccion,String telefono,boolean estado) throws CedulaNoValidaException, UsuarioNoExistenteException {
        validaciones.validarCedula(cedula);
        usuariosDAO.modUsuario(cedula, nombre, apellido, direccion, telefono, estado);
        //Registro
        registroService.add(get(cedula).getRegistro(TipoModificacion.MODIFICACION));
    }
    
    public Usuario get(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException{
        validaciones.validarCedula(cedula);
        Usuario u = usuariosDAO.getUsuario(cedula); 
        if (u == null)        
            throw new UsuarioNoExistenteException(cedula);
        return u;
    }
       
    public Set<Usuario> getLista(){
        return UsuariosDAO.getInstance().getUsuarios();
    }
    
    public Set<Usuario> getLista(TipoUsuario tipoUsuario){
        return UsuariosDAO.getInstance().getUsuarios(tipoUsuario);
    }
    
    public Set<Parqueadero> getParqueaderos(String cedula) throws CedulaNoValidaException, UsuarioNoExistenteException{
        validaciones.validarCedula(cedula);
        return usuariosDAO.getParqueaderos(cedula);
    }
    
    public void autenticarUsuario(String nombreCampus, String idPuerta, String cedula) throws CedulaNoValidaException, UsuarioNoExistenteException, CodigoNoValidoException, ParqueaderoNoExistenteException, AccesoNoAutorizadoException, CampusNoExistenteException{
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
            if(portero.getCampus().compareToIgnoreCase(nombreCampus) != 0)
                throw new AccesoNoAutorizadoException(cedula, portero.getTipoUsuarioString(), nombreCampus, idPuerta);
            //Registro
            registroService.add(portero.getRegistro(TipoAcceso.ACCESO));
            //Fin registro
            return;
        }
        
        Usuario u = get(cedula);
        boolean encontrado = false;
        
        for(Parqueadero p : getParqueaderos(cedula)){
            if(p.getNombreCampus().compareToIgnoreCase(nombreCampus) != 0)
                continue;
            
            for(Puerta pu : parqueaderoService.getPuertasEntrada(nombreCampus, p.getId())){
                if(idPuerta.compareToIgnoreCase(pu.getId()) == 0){
                    encontrado = true;
                    break;
                }
            }
            
            if(encontrado)
                break;
            
            for(Puerta pu : parqueaderoService.getPuertasSalida(nombreCampus, p.getId())){
                if(idPuerta.compareToIgnoreCase(pu.getId()) == 0){
                    encontrado = true;
                    break;
                }
            }
            
            if(encontrado)
                break;
        }
        
        if(!encontrado)
            throw new AccesoNoAutorizadoException(cedula, u.getTipoUsuarioString(), nombreCampus, idPuerta);
        //Registro
        registroService.add(u.getRegistro(TipoAcceso.ACCESO));
        
        /*************************************
         * Implementar luego si entra o sale
         *************************************/
    }
  
}
