/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

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
    

public void ValidarDatos(String cedula,String nombre, String apellidos)
    {
        if(cedula==null || cedula.trim().length()==0)
            throw new IllegalArgumentException("El campo cedula no puede estar vacio");
        if(nombre==null || nombre.trim().length()==0)
            throw new IllegalArgumentException("El argumento nombre no puede estar vacio");
        if(apellidos==null || apellidos.trim().length()==0)
            throw new IllegalArgumentException("El argumento apellidos no puede estar vacio");
        
    }

}
