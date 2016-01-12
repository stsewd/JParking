/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.interfaces.ClaveDAOInterface;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;

/**
 *
 * @author Franklin
 */
public class ClaveDAO implements ClaveDAOInterface{
    
    private static ClaveDAO instancia;

    private ClaveDAO() {
        
    }
    
    public static ClaveDAO getInstancia(){
    
        if (instancia==null){
            instancia = new ClaveDAO();
        }
        return instancia;
    }

    @Override
    public boolean validarClave(String usuario, String clave) {
        
        HashMap tabla;
        SecretKey key;
        
        try {
            FileInputStream flujoentrada = new FileInputStream("src/edu/ucuen/jparking/dao/usuarios.dat");
            ObjectInputStream objetoentrada = new ObjectInputStream(flujoentrada);
            
            FileInputStream flujoentradaClave = new FileInputStream("src/edu/ucuen/jparking/dao/claves.dat");
            ObjectInputStream  objetoentradaClave = new ObjectInputStream(flujoentradaClave);
            
            tabla = (HashMap)objetoentrada.readObject();
            key = (SecretKey)objetoentradaClave.readObject();
            
            DesEncrypter encrypter = new DesEncrypter(key);
            String encriptado = encrypter.encrypt(clave);
            
            Iterator it = tabla.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry e = (Map.Entry)it.next();
                if(usuario.equals(e.getKey()) && encriptado.equals(e.getValue())){
                return true;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontró el archivo.");
        } catch (IOException ex) {
            System.out.println("Hubo un error al leer el archivo.");
        } catch (ClassNotFoundException ex) {
            System.out.println("No se encontró la clase buscada.");
        } catch (IllegalBlockSizeException ex) {
            System.out.println("Error con la clave.");
        }
        
        return false;
    }

    
    
    
    
}
