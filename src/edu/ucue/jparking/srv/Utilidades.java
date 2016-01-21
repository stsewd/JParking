/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.srv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Santos Gallegos
 */
public class Utilidades {
    private static ClaveService claveService = new ClaveService();
    
    public static void iniciarDefaultDirectorios()
            throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
            ClassNotFoundException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException
    {
        // Crear direcotorios si no existen
        File direc =  new  File("data"); 
        File backup = new File("backup");
        direc.mkdirs();
        backup.mkdirs();
        
        ObjectOutputStream salidaObjetostClave = null;
        try {
            salidaObjetostClave  = new ObjectOutputStream(new FileOutputStream(new File(direc,"celebrum.dat")));
            //guardar clave encriptada
            claveService.GenerarClave();
            //guarda la clave por defecto
            claveService.cifrar("admin");
        } finally {
                salidaObjetostClave.close();
        }
    }
}
