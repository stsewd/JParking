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
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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

    public void saveClave(String fileName, String clave) throws IOException{
        ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream(fileName));
        flujoSalida.writeObject(clave);
        flujoSalida.close();
    }
    
    public String recuperarClave(String fileName) throws IOException, ClassNotFoundException{
        ObjectInputStream flujoEntrada = new ObjectInputStream(new FileInputStream(fileName));
        String clave = (String) flujoEntrada.readObject();
        flujoEntrada.close();
        return clave;
    }
           
    public PublicKey loadPublicKey(String fileName) throws Exception {
      FileInputStream fis = new FileInputStream(fileName);
      int numBtyes = fis.available();
      byte[] bytes = new byte[numBtyes];
      fis.read(bytes);
      fis.close();
 
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      KeySpec keySpec = new X509EncodedKeySpec(bytes);
      PublicKey keyFromBytes = keyFactory.generatePublic(keySpec);
      return keyFromBytes;
   }
 
    public PrivateKey loadPrivateKey(String fileName) throws Exception {
      FileInputStream fis = new FileInputStream(fileName);
      int numBtyes = fis.available();
      byte[] bytes = new byte[numBtyes];
      fis.read(bytes);
      fis.close();
 
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      KeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
      PrivateKey keyFromBytes = keyFactory.generatePrivate(keySpec);
      return keyFromBytes;
   }
 
    public  void saveKey(Key key, String fileName) throws Exception {
      byte[] publicKeyBytes = key.getEncoded();
      FileOutputStream fos = new FileOutputStream(fileName);
      fos.write(publicKeyBytes);
      fos.close();
   }
    
}
