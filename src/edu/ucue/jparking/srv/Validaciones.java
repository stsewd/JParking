/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import edu.ucue.jparking.srv.excepciones.PuertaInactivaException;
import edu.ucue.jparking.srv.excepciones.UsuarioInactivoException;
import edu.ucue.jparking.srv.excepciones.FechaFinalMenorAFechaInicialException;
import edu.ucue.jparking.srv.excepciones.FechaInicialIgualAFechaFinalException;
import edu.ucue.jparking.srv.excepciones.FechaInicialMayorAFechaFinalException;
import edu.ucue.jparking.srv.excepciones.TelefonoNoValidoException;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.dao.excepciones.ParqueaderoNoExistenteException;
import edu.ucue.jparking.dao.excepciones.PuertaNoExistenteException;
import edu.ucue.jparking.dao.excepciones.UsuarioNoExistenteException;
import edu.ucue.jparking.srv.excepciones.CampusInactivoException;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;
import edu.ucue.jparking.srv.excepciones.ParquaderoInactivoException;
import edu.ucue.jparking.srv.objetos.Campus;
import edu.ucue.jparking.srv.objetos.Parqueadero;
import edu.ucue.jparking.srv.objetos.Puerta;
import edu.ucue.jparking.srv.objetos.Usuario;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * @author Franklin Lara
 */
public class Validaciones {

    static void validarFecha(Calendar fechaInicio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void validarFecha(Calendar fechaInicio, Calendar fechaFinal) throws FechaInicialMayorAFechaFinalException, FechaFinalMenorAFechaInicialException, FechaInicialIgualAFechaFinalException {
        if(fechaInicio == null)
            throw new IllegalArgumentException("Fecha inicial no valida.");
        if(fechaFinal == null)
            throw new IllegalArgumentException("Fecha final no valida.");
        
        //Si estás leyendo esto, pasar dos fechas iguales
        //esta dando problemas...
        //bueno, te toca implementar un método que compare
        //mes/dia/año porque el equals y == no sirven :)
        //
        if(fechaFinal == fechaInicio)
            throw new FechaInicialIgualAFechaFinalException(fechaInicio);
        
        if(fechaInicio.after(fechaFinal))
            throw new FechaInicialMayorAFechaFinalException(fechaInicio, fechaFinal);
        
        if(fechaFinal.before(fechaInicio))
            throw new FechaFinalMenorAFechaInicialException(fechaInicio, fechaFinal);
    }

public void ComprobarParqueadero(String nombreCampus, String idParqueadero) throws ParqueaderoNoExistenteException, CodigoNoValidoException, ParquaderoInactivoException, CampusNoExistenteException, CampusInactivoException{
    ParqueaderoService parqueaderoService = new ParqueaderoService();
    Parqueadero parqueadero = parqueaderoService.getParqueadero(nombreCampus, idParqueadero);
    if(parqueadero==null)
        throw new ParqueaderoNoExistenteException(idParqueadero);
    if(parqueadero.isActivo()==false)
        throw new ParquaderoInactivoException(parqueadero.getUbicacion());
}

public void ComprobarPuerta(String nombreCampus, String idParqueadero, String idPuerta) throws ParqueaderoNoExistenteException, CodigoNoValidoException, PuertaNoExistenteException, CampusNoExistenteException, PuertaInactivaException, CampusInactivoException, ParquaderoInactivoException{
    PuertaService service = new PuertaService();
    ParqueaderoService parqueaderoService = new ParqueaderoService();
    Parqueadero parqueadero = parqueaderoService.getParqueadero(nombreCampus, idParqueadero);
    Puerta puerta = service.getPuerta(parqueadero.getNombreCampus(), idPuerta);
    if(puerta==null)
        throw new PuertaNoExistenteException(idPuerta);
    if(puerta.estaActiva()==false)
        throw new PuertaInactivaException(idPuerta);
}

public void ComprobarUsuario(String cedula) throws UsuarioNoExistenteException, CedulaNoValidaException, UsuarioInactivoException{
    UsuarioService service = new UsuarioService();
    Usuario usuario = service.get(cedula);
    if(usuario==null)
        throw new UsuarioNoExistenteException(cedula);
    if(usuario.isActivo()==false)
        throw new UsuarioInactivoException();
}

public void ComprobarCampus(String idCampus) throws CampusNoExistenteException, CampusInactivoException{
    CampusService campusService = new CampusService();
    Campus campus = campusService.getCampus(idCampus);
    if(campus==null)
        throw new CampusNoExistenteException(idCampus);
    if(campus.isActivo()==false)
        throw new  CampusInactivoException(idCampus);
}

public boolean ComprobarUsuarioAsignadoParqueadero(String cedula) throws CampusNoExistenteException, CodigoNoValidoException, ParqueaderoNoExistenteException, UsuarioNoExistenteException, CedulaNoValidaException{
    UsuarioService usuarioService = new UsuarioService();
   
    return usuarioService.getParqueaderos(cedula).size() > 0;
}

public boolean validarCedula(String cedula) throws CedulaNoValidaException {
    boolean cedulaCorrecta = false;
    try {
        if (cedula.length() == 10)
        {
            int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
            if (tercerDigito < 6) {
                // Coeficientes de validación cédula
                // El decimo digito se lo considera dígito verificador
                int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
                int verificador = Integer.parseInt(cedula.substring(9,10));
                int suma = 0;
                int digito = 0;
                for (int i = 0; i < (cedula.length() - 1); i++) {
                 digito = Integer.parseInt(cedula.substring(i, i + 1))* coefValCedula[i];
                 suma += ((digito % 10) + (digito / 10));
                }
                if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                    cedulaCorrecta = true;
                }
                else if ((10 - (suma % 10)) == verificador) {
                    cedulaCorrecta = true;
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } else {
            cedulaCorrecta = false;
        }
    } catch (NumberFormatException nfe) {
        cedulaCorrecta = false;
    } catch (Exception err) {
        System.out.println("Una excepcion ocurrio en el proceso de validadcion");
        cedulaCorrecta = false;
    }
    if (!cedulaCorrecta) {
        throw new CedulaNoValidaException(cedula);
    }
    return cedulaCorrecta;
}
    
public void ValidarCampus(String nombre,String direccion){
        if(nombre==null || nombre.trim().length()==0)
            throw new IllegalArgumentException("El nombre del campus no puede estra vacio");
        if (direccion==null || direccion.trim().length()==0)
            throw new IllegalArgumentException("La direccion del campus no puede estar vacia");
}

public void ValidarDatos(String cedula, String nombre, String apellidos, String direccion, String telefono) throws TelefonoNoValidoException
    {
        if(cedula==null || cedula.trim().length()==0)
            throw new IllegalArgumentException("El campo cedula no puede estar vacio");
        if(nombre==null || nombre.trim().length()==0)
            throw new IllegalArgumentException("El argumento nombre no puede estar vacio");
        if(apellidos==null || apellidos.trim().length()==0)
            throw new IllegalArgumentException("El argumento apellidos no puede estar vacio");
        if(direccion==null || direccion.trim().length()==0)
            throw new IllegalArgumentException("El argumento direccion no puede estar vacio");
        if(telefono==null || telefono.trim().length()==0)
            throw new IllegalArgumentException("El argumento telefono no puede estar vacio");
        ValidarTelefono(telefono);
    }
private void ValidarTelefono(String telefono) throws TelefonoNoValidoException{
    if(telefono.trim().length()!=10)
        throw new TelefonoNoValidoException();
    telefono = telefono.trim();
    for(int i = 0; i < telefono.length(); i++){
        if(!Character.isDigit(telefono.charAt(i)))
            throw new TelefonoNoValidoException();
    }
}
public void ValidarPuerta(String ubicacion,String id,String idCampus){
    if(ubicacion==null||ubicacion.trim().length()==0)
            throw new IllegalArgumentException("El campo ubicaion no puede ser nulo");
    if(id==null || id.trim().length()==0)
        throw new IllegalArgumentException("El campo del id no puede estar vacio");
    if(idCampus==null || idCampus.trim().length()==0)
        throw new IllegalArgumentException("El campo del id del campus no puede estar vacio");
}

    public  void validarCodigo(String codigo) throws CodigoNoValidoException{
        if(codigo==null || codigo.trim().length()==0)
            throw new IllegalArgumentException("El codigo no puede estar vacio");
        codigo = codigo.trim();
        if(codigo.length() != 3)
            throw new CodigoNoValidoException(codigo);
        try{
            Integer.parseInt(codigo.subSequence(1, 3).toString());
        }catch(Exception e){
            throw new CodigoNoValidoException(codigo);
        }
        if(!Character.isAlphabetic(codigo.charAt(0)))
            throw new CodigoNoValidoException(codigo);        
    }  
    
public void ValidarParqueadero(String ubicacion, int numeroLugares, String id, String nombreCampus){
    if(id==null || id.trim().length()==0)
        throw new IllegalArgumentException("EL argumento id no puede estar vacio");
    if(ubicacion==null || ubicacion.trim().length()==0)
        throw new IllegalArgumentException("El argumento ubicacion no puede estar vacio");
    if(numeroLugares<=0)
        throw new IllegalArgumentException("Los numero de lugare no pueden ser negativos");
    if(nombreCampus==null || nombreCampus.trim().length()==0)
        throw new IllegalArgumentException("El argumento nombre no puede estra vacio");
}
}
