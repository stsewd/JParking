/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;
import edu.ucue.jparking.srv.excepciones.CodigoNoValidoException;
import edu.ucue.jparking.dao.CampusDAO;
import edu.ucue.jparking.dao.excepciones.CampusNoExistenteException;
import edu.ucue.jparking.srv.excepciones.CedulaNoValidaException;

/**
 *
 * @author Franklin Lara
 */
public class Validaciones {
    //private String aux;

public boolean validarCedula(String cedula) throws CedulaNoValidaException {
    boolean cedulaCorrecta = false;

    try {

        if (cedula.length() == 10) // ConstantesApp.LongitudCedula
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

public void ValidarDatos(String cedula, String nombre, String apellidos, String direccion, String telefono)
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
    if(numeroLugares<0)
        throw new IllegalArgumentException("Los numero de lugare no pueden ser negativos");
    if(nombreCampus==null || nombreCampus.trim().length()==0)
        throw new IllegalArgumentException("El argumento nombre no puede estra vacio");
}
}

