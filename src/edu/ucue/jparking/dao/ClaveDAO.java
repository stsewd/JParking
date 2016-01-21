/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucue.jparking.dao;

import edu.ucue.jparking.dao.interfaces.ClaveDAOInterface;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import javax.crypto.spec.SecretKeySpec;
//import sun.rmi.server.MarshalInputStream;

/**
 *
 * @author Franklin
 */
public class ClaveDAO implements ClaveDAOInterface{
    
    private static ClaveDAO instancia;

    private ClaveDAO() {
        
    }
    
    public static ClaveDAO getInstancia(){
        if (instancia==null)
            instancia = new ClaveDAO();
        return instancia;
    }

    @Override
    public void saveClave(String fileName, SecretKeySpec clave) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)));
        oos.writeObject(clave);
        oos.close();
    }
    
    @Override
    public SecretKeySpec recuperarClave(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)));
        SecretKeySpec clave = (SecretKeySpec) ois.readObject();
        return clave;
    }
           
 
    @Override
    public void guardarContrasenia(String fileName, byte[] clave) throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream(new File(fileName));
        fos.write(clave, 0, 16);
        fos.close();
    }
    
    @Override
    public byte[] recuperarContrasenia(String filename) throws IOException{
        File file = new File(filename);
        byte[] clave = Files.readAllBytes(file.toPath());
        return clave;
    }
}
